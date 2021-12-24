package com.fil.authentication.services;

import com.fil.authentication.commons.Pagination;
import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.commons.Utils;
import com.fil.authentication.constants.Messages;
import com.fil.authentication.dao.AccountDao;
import com.fil.authentication.models.Account;
import com.fil.authentication.models.UserDetails;
import com.fil.authentication.payload.request.AccountPayload;
import com.fil.authentication.repository.AccountRepository;
import com.fil.authentication.repository.UserDetailsRepository;
import com.fil.authentication.security.PermissionCustomerService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PermissionCustomerService permissionCompanyService;

    @Override
    public ResponseAPI createDetails(UserDetails postData) throws Exception {
        return null;
    }

    @Override
    public ResponseAPI updateDetails(UserDetails putData) throws Exception {
        return null;
    }

    @Override
    public ResponseAPI changePasswordByAdmin(String password) throws Exception {
        return null;
    }

    @Override
    public ResponseAPI forgotPassword(String email, String password) throws Exception {
        return null;
    }

    @Override
    public Object getAll(String searchData, String sortData, String fields, Integer page, Integer pageSize) throws Exception {
        Map<String, Object> mapData = new HashMap<>();
        Pageable pageable = PageRequest.of(0, 20);
        if (null != page && null != pageSize) {
            pageable = PageRequest.of(page, pageSize);
            mapData.put("pagination", new Pagination(page, pageSize, accountDao.count(searchData, sortData)));
        }
        List<Account> accounts = accountDao.getAll(searchData, sortData, pageable);
        List<Map<String, Object>> list = null;
        if (fields != null) {
            list = accounts.stream().map(ob -> Utils.convertDTO(fields, ob)).collect(Collectors.toList());
        }
        mapData.put("msg", Messages.getSuccess("danh sách tài khoản"));
        mapData.put("data", list != null ? list : accounts);
        mapData.put("status", HttpStatus.SC_OK);
        return mapData;
    }

    @Override
    public ResponseAPI create(Account postData) throws Exception, AccessDeniedException {
        return null;
    }


    public ResponseAPI create(@Valid AccountPayload postData) throws Exception {
        Account account = new Account();
        account.setEmail(postData.getEmail());
        account.setPassword(passwordEncoder.encode(postData.getPassword()));
        account.setActived(true);
        account.setUserDetails(postData.getUserDetails());
        account.setUsername(postData.getUsername());
        permissionCompanyService.setCustomerFor(account);
        account = accountRepository.save(account);
        return new ResponseAPI(Messages.createSuccess("tài khoản"), account);
    }

    @Override
    public ResponseAPI createBySuperAdmin(@Valid Account postData) throws Exception {
        postData = accountRepository.save(postData);
        return new ResponseAPI(Messages.createSuccess("tài khoản"), postData);
    }


    @Override
    public ResponseAPI put(Account putData) throws Exception {
        permissionCompanyService.checkCustomerPermsission(putData.getCustomer(), putData);
        putData = accountRepository.save(putData);
        return new ResponseAPI(Messages.updateSuccess("tài khoản"), putData);
    }

    @Override
    public ResponseAPI findById(Long id) throws Exception {
        Account account = accountRepository.findById(id).orElseThrow(() -> new Exception(Messages.notFound("tài khoản")));
        return new ResponseAPI(Messages.updateSuccess("tài khoản"), account);
    }

    @Override
    public ResponseAPI deletes(List<Long> ids) throws Exception {
        try {
            accountRepository.deletes(ids);
            return new ResponseAPI(Messages.deleteSuccess("tài khoản"), null);
        } catch (Exception e) {
            return new ResponseAPI(Messages.deleteFailed("tài khoản"), null);
        }
    }

}
