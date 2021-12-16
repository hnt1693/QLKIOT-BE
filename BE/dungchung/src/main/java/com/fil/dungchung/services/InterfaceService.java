package com.fil.dungchung.services;


import com.fil.dungchung.commons.ResponseAPI;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface InterfaceService<T> {
    Object getAll(String searchData, String sortData, Integer page, Integer pageSize) throws Exception;

    ResponseAPI create(T postData) throws Exception, AccessDeniedException;

    ResponseAPI put(T putData) throws Exception, AccessDeniedException;

    ResponseAPI findById(Long id) throws Exception, AccessDeniedException;

    ResponseAPI deletes(List<Long> ids) throws Exception, AccessDeniedException;
}
