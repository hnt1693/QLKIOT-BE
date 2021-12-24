package com.fil.authentication.controllers;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.Account;
import com.fil.authentication.payload.request.AccountPayload;
import com.fil.authentication.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasAuthority('CUSTOMER_READ')")
    @GetMapping("")
    public Object getAll(@RequestParam(required = false) String searchData,
                         @RequestParam(required = false) String sortData,
                         @RequestParam(required = false) String fields,
                         @RequestParam(required = false, defaultValue = "0") Integer page,
                         @RequestParam(required = false, defaultValue = "20") Integer pageSize) throws Exception {
        return accountService.getAll(searchData, sortData, fields, page, pageSize);
    }


    @PostMapping("")
    public ResponseAPI createAccount(@RequestBody AccountPayload account) throws Exception {
        return accountService.create(account);
    }


    @PutMapping("")
    public ResponseAPI updateAccount(@RequestBody Account account) throws Exception {
        return accountService.put(account);
    }


    @DeleteMapping("")
    public ResponseAPI deletes(@RequestBody List<Long> ids) throws Exception {
        return accountService.deletes(ids);

    }
}
