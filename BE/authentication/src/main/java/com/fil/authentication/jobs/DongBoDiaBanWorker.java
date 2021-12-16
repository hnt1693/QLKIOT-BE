package com.fil.authentication.jobs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.authentication.client.DungChungClient;
import com.fil.authentication.commons.DBUtils;
import com.fil.authentication.commons.Utils;
import com.fil.authentication.dao.JobDao;
import com.fil.authentication.enums.JOB_STATUS;
import com.fil.authentication.models.DanhMucDiaBan;
import com.fil.authentication.models.Job;
import com.fil.authentication.repository.DanhMucDiaBanRepository;
import com.fil.authentication.repository.JobRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DongBoDiaBanWorker implements Runnable {

    Log logger = LogFactory.getFactory().getInstance(DongBoDiaBanWorker.class);

    private JobRepository jobRepository;

    private Job job;

    private DungChungClient dungChungClient;

    private DanhMucDiaBanRepository danhMucDiaBanRepository;

    private int pageSize;

    private JobDao jobDao;

    private Connection connection;

    private Job parentJob;

    private String threadName;

    public DongBoDiaBanWorker(String threadName, JobDao jobDao, JobRepository jobRepository, Job job, DungChungClient dungChungClient, DanhMucDiaBanRepository danhMucDiaBanRepository, int pageSize, Job parentJob) {
        this.threadName = threadName;
        this.jobRepository = jobRepository;
        this.job = job;
        this.dungChungClient = dungChungClient;
        this.danhMucDiaBanRepository = danhMucDiaBanRepository;
        this.pageSize = pageSize;
        this.jobDao = jobDao;
        this.connection = DBUtils.getConnection();
        this.parentJob = parentJob;
    }

    @Override
    public void run() {
        ObjectMapper objectMapper = Utils.getObjectMapper();
        Map<String, Object> map = new HashMap<>();
        int start = job.getProcess();
        int total = job.getTotal();
        if(!job.getStatus().equals(JOB_STATUS.RUNNING)){
            job.setStatus(JOB_STATUS.RUNNING);
            jobDao.update(connection,job);
            logger.info(threadName + " " + "RESUMING JOB");
        }
        for (int i = start; i <= total + 1; i++) {
            if (performRun()) {
                try {
                    map = dungChungClient.dongBoDiaBan(i, pageSize);
                    if ((int) map.get("status") != 200) {
                        throw new Exception("Error get with page : " + i);
                    }
                    List<DanhMucDiaBan> danhMucDiaBanList = objectMapper.readValue(objectMapper.writeValueAsString(map.get("data")), new TypeReference<List<DanhMucDiaBan>>() {
                    });
                    danhMucDiaBanRepository.saveAll(danhMucDiaBanList);
                    job.setProcess(i);
                    jobDao.update(connection, job);
                    logger.info(threadName + " : " + i + "/" + total + " With pageSize: " + pageSize);
                } catch (Exception e) {
                    e.printStackTrace();
                    job.setStatus(JOB_STATUS.PAUSE);
                    jobRepository.save(job);
                    break;
                }

            } else {
                break;
            }

        }
        logger.info(threadName + " " + job.getStatus());

        try {
            DBUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public boolean performRun() {
        try {
            jobDao.addOneProcess(connection, parentJob);
            if (job.getProcess() >= job.getTotal()) {
                job.setStatus(JOB_STATUS.FINISH);
                jobDao.update(connection, job);
                return false;
            }
            parentJob = jobDao.getById(connection, parentJob.getId());
            if (parentJob.getStatus().equals(JOB_STATUS.PAUSE)) {
                job.setStatus(JOB_STATUS.PAUSE);
                jobDao.update(connection, job);
                return false;
            }
            job = jobDao.getById(connection, job.getId());
            if (job.getStatus().equals(JOB_STATUS.PAUSE)) {
                return false;
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            job.setStatus(JOB_STATUS.PAUSE);
            jobDao.save(job);
            return false;
        }


    }
}
