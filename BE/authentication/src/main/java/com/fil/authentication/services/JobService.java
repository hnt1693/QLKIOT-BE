package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.Job;

public interface JobService {

    public ResponseAPI get() throws Exception;

    public Job create(Job postData) throws Exception;

    public Job put(Job putData) throws Exception;

    public Job findById(Long id) throws Exception;


}
