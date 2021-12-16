package com.fil.authentication.controllers;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseAPI getAll() throws Exception {
        return jobService.get();
    }
}
