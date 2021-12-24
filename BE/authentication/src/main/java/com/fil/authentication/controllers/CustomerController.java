package com.fil.authentication.controllers;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.Customer;
import com.fil.authentication.payload.request.CustomerPayload;
import com.fil.authentication.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth/customer")
public class CustomerController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("")
    public Object get() throws Exception {
        return customerService.getAll(null, null, null, null, null);
    }

    @PostMapping("")
    public ResponseAPI create(@Valid @RequestBody CustomerPayload customer) throws Exception {
        return customerService.create(customer);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("")
    public ResponseAPI update(@Valid @RequestBody Customer company) throws Exception {
        return customerService.put(company);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseAPI findById(@PathVariable Long id) throws Exception {
        return customerService.findById(id);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("")
    public ResponseAPI deletes(@RequestBody List<Long> ids) throws Exception {
        return customerService.deletes(ids);
    }



}
