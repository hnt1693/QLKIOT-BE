package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.DanhMucDiaBan;

import java.util.List;

public interface DanhMucDiaBanService extends InterfaceService<DanhMucDiaBan> {

    void dongBoDanhMucDiaBan() throws Exception;

    void pauseJob(Long id) throws Exception;

    void resumeJob(Long id) throws Exception;

    ResponseAPI getByCode(String ma) throws Exception;

    ResponseAPI getByParentCode(String ma) throws Exception;

}
