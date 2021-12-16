package com.fil.authentication.services;


import com.fil.authentication.client.DungChungClient;
import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.constants.Messages;
import com.fil.authentication.dao.DanhMucDiaBanDao;
import com.fil.authentication.dao.JobDao;
import com.fil.authentication.jobs.DongBoDiaBanJob;
import com.fil.authentication.models.DanhMucDiaBan;
import com.fil.authentication.repository.DanhMucDiaBanRepository;
import com.fil.authentication.repository.JobRepository;
import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DanhMucDiaBanServiceImpl implements DanhMucDiaBanService {

    @Autowired
    private DanhMucDiaBanRepository danhMucDiaBanRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DungChungClient dungChungClient;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private DanhMucDiaBanDao danhMucDiaBanDao;

    @Override
    public Object getAll(String searchData, String sortData, String fields, Integer page, Integer pageSize) throws Exception {
        Map<String, Object> mapData = new HashMap<>();
//        Pageable pageable = PageRequest.of(0, 20);
//        if (null != page && null != pageSize) {
//            mapData.put("pagination", new Pagination(page, pageSize, danhMucDiaBanDao.count()));
//        }
//        List<DanhMucDiaBan> danhMucDiaBans = danhMucDiaBanDao.getAll(pageable);
//        mapData.put("msg", Messages.getSuccess("danh sách tài khoản"));
//        mapData.put("data", danhMucDiaBans);
//        mapData.put("status", HttpStatus.SC_OK);
        return mapData;
    }

    @Override
    public ResponseAPI create(DanhMucDiaBan postData) throws Exception, AccessDeniedException {
        return null;
    }

    @Override
    public ResponseAPI put(DanhMucDiaBan putData) throws Exception, AccessDeniedException {
        return null;
    }

    @Override
    public ResponseAPI findById(Long id) throws Exception, AccessDeniedException {
        return null;
    }

    @Override
    public ResponseAPI deletes(List<Long> ids) throws Exception, AccessDeniedException {
        return null;
    }

    @Override
    public void dongBoDanhMucDiaBan() throws Exception {
        DongBoDiaBanJob dongBoDiaBanJob = new DongBoDiaBanJob(jobDao, danhMucDiaBanRepository, dungChungClient, jobRepository);
        dongBoDiaBanJob.start();
    }

    @Override
    public void pauseJob(Long id) throws Exception {
        DongBoDiaBanJob dongBoDiaBanJob = new DongBoDiaBanJob(jobDao, danhMucDiaBanRepository, dungChungClient, jobRepository);
        dongBoDiaBanJob.pauseJob(id);
    }

    @Override
    public void resumeJob(Long id) throws Exception {
        DongBoDiaBanJob dongBoDiaBanJob = new DongBoDiaBanJob(jobDao, danhMucDiaBanRepository, dungChungClient, jobRepository);
        dongBoDiaBanJob.resumeJob(id);
    }

    @Override
    public ResponseAPI getByCode(String ma) throws Exception {
        DanhMucDiaBan danhMucDiaBan = danhMucDiaBanRepository.findByCode(ma);
        if (danhMucDiaBan == null) throw new Exception(Messages.notFound("địa bàn"));
        return new ResponseAPI(Messages.getSuccess("địa bàn"), danhMucDiaBan);
    }

    @Override
    public ResponseAPI getByParentCode(String ma) throws Exception {
        List<DanhMucDiaBan> list = danhMucDiaBanRepository.findAllByParentCode(ma);
        return new ResponseAPI(Messages.getSuccess("địa bàn"), list);
    }


}
