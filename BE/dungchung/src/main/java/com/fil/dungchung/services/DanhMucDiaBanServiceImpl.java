package com.fil.dungchung.services;

import com.fil.dungchung.commons.Pagination;
import com.fil.dungchung.commons.ResponseAPI;
import com.fil.dungchung.constants.Messages;
import com.fil.dungchung.daos.DanhMucDiaBanDao;
import com.fil.dungchung.models.DanhMucDiaBan;
import com.fil.dungchung.repository.DanhMucDiaBanRepository;
import org.apache.http.HttpStatus;
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
    private DanhMucDiaBanDao danhMucDiaBanDao;

    @Override
    public Object getAll(String searchData, String sortData, Integer page, Integer pageSize) throws Exception {
        Map<String, Object> mapData = new HashMap<>();
        Pageable pageable = PageRequest.of(0, 20);
        if (null != page && null != pageSize) {
            pageable = PageRequest.of(page,pageSize);
            mapData.put("pagination", new Pagination(page, pageSize, danhMucDiaBanDao.count()));
        }
        List<DanhMucDiaBan> danhMucDiaBans = danhMucDiaBanDao.getAll(pageable);
        mapData.put("msg", Messages.getSuccess("danh sách tài khoản"));
        mapData.put("data", danhMucDiaBans);
        mapData.put("status", HttpStatus.SC_OK);
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


}
