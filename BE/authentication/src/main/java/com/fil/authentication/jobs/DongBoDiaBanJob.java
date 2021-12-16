package com.fil.authentication.jobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.authentication.client.DungChungClient;
import com.fil.authentication.commons.Pagination;
import com.fil.authentication.commons.Utils;
import com.fil.authentication.constants.Constants;
import com.fil.authentication.dao.JobDao;
import com.fil.authentication.enums.JOB_STATUS;
import com.fil.authentication.models.Job;
import com.fil.authentication.repository.DanhMucDiaBanRepository;
import com.fil.authentication.repository.JobRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class DongBoDiaBanJob {
    Log logger = LogFactory.getFactory().getInstance(DongBoDiaBanJob.class);
    private JobDao jobDao;
    private DanhMucDiaBanRepository danhMucDiaBanRepository;
    private DungChungClient dungChungClient;
    private JobRepository jobRepository;
    int pageSize = 100;

    public DongBoDiaBanJob(JobDao jobDao, DanhMucDiaBanRepository danhMucDiaBanRepository, DungChungClient dungChungClient, JobRepository jobRepository) {
        this.jobDao = jobDao;
        this.danhMucDiaBanRepository = danhMucDiaBanRepository;
        this.dungChungClient = dungChungClient;
        this.jobRepository = jobRepository;
    }

    public void start() throws Exception {
        try {
            Date date = new Date();
            int startPage = 0;
            int total = 0;
            ObjectMapper objectMapper = Utils.getObjectMapper();
            Map<String, Object> getInfo = dungChungClient.dongBoDiaBan(startPage, pageSize);
            if ((int) getInfo.get("status") != 200) {
                throw new Exception("Get data error");
            }
            Pagination pagination = objectMapper.readValue(objectMapper.writeValueAsString(getInfo.get("pagination")), Pagination.class);
            total = Math.floorDiv(pagination.getTotal(), pageSize);
            logger.info("START SYNC - DANH MUC ĐỊA BÀN");
            Job job = initJob(total, 2);
            List<Thread> threads = new ArrayList<>();
            for (Job child : job.getChilds()) {
                threads.add(new Thread(new DongBoDiaBanWorker(child.getJobCode(), jobDao, jobRepository, child, dungChungClient, danhMucDiaBanRepository, pageSize, job)));
            }
            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
            logger.info("END SYNC - DANH MUC ĐỊA BÀN WITH " + (new Date().getTime() - date.getTime()) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ERROR SYNC - DANH MUC ĐỊA BÀN");
            throw new Exception("Đồng bộ lỗi");
        }
    }

    private Job initJob(int total, int threads) throws Exception {
        try {
            List<Job> existJob = jobRepository.getAllByJobCodeLike(Constants.JOB_DONGBODIABAN);
            jobRepository.deleteAll(existJob);
            Job job = new Job(0L, "Đồng bộ địa bàn", Constants.JOB_DONGBODIABAN, JOB_STATUS.RUNNING, total, 0, total);
            List<Job> subs = new ArrayList<>();
            if (threads >= 2) {
                int start = 0;
                int end = -1;
                for (int i = 0; i < threads; i++) {
                    start = end + 1;
                    end = Math.min(start + Math.floorDiv(total, threads), total);
                    Job subJob = new Job(0L, "Đồng bộ địa bàn", Constants.JOB_DONGBODIABAN + "_" + (i + 1), JOB_STATUS.RUNNING, end, start, end - start + 1);
                    subs.add(subJob);
                }
                job.setChilds(subs);
            }
            job = jobRepository.save(job);
            return job;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Tạo job không thành công");
        }

    }

    public void pauseJob(Long id) throws Exception {
        try {
            Job job = jobRepository.findById(id).orElseThrow(() -> new Exception("Job not found"));
            job.setStatus(JOB_STATUS.PAUSE);
            jobRepository.save(job);
        } catch (Exception e) {
            throw new Exception("Dừng job không thành công");
        }

    }

    public void resumeJob(Long jobId) throws Exception {
        try {
            Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("Job not found"));
            if (!job.getStatus().equals(JOB_STATUS.PAUSE)) return;
            if (job.getChilds().size() >= 2) {
                List<Thread> threads = new ArrayList<>();
                for (Job child : job.getChilds()) {
                    threads.add(new Thread(new DongBoDiaBanWorker(child.getJobCode(), jobDao, jobRepository, child, dungChungClient, danhMucDiaBanRepository, pageSize, job)));
                }
                job.setStatus(JOB_STATUS.RUNNING);
                jobRepository.save(job);
                for (Thread thread : threads) {
                    thread.start();
                }

                for (Thread thread : threads) {
                    thread.join();
                }
            }

        } catch (Exception e) {
            throw new Exception("Khôi phục job không thành công");
        }
    }

}
