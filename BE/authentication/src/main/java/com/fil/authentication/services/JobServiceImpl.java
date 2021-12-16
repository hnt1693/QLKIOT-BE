package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.constants.Messages;
import com.fil.authentication.models.Job;
import com.fil.authentication.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Override
    public ResponseAPI get() throws Exception {
        return new ResponseAPI(Messages.getSuccess("job"), jobRepository.getAllByJobParentIsNull());
    }

    @Override
    public Job create(Job postData) {
        return jobRepository.save(postData);
    }

    @Override
    public Job put(Job putData) {
        return jobRepository.save(putData);
    }

    @Override
    public Job findById(Long id) throws Exception {
        return jobRepository.findById(id).orElseThrow(() -> new Exception(Messages.notFound("Job")));
    }
}
