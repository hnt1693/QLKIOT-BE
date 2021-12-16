package com.fil.authentication.security;

import com.fil.authentication.models.Account;
import com.fil.authentication.models.Customer;
import com.fil.authentication.repository.AccountRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.nio.file.AccessDeniedException;

@Service
public class PermissionCustomerService {
    Log log = LogFactory.getFactory().getInstance(PermissionCustomerService.class);
    @Autowired
    private AccountRepository accountRepository;

    public void checkCustomerPermsission(Customer company, Object object) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new Exception("Không tìm thấy user"));
        if (!account.getCustomer().getId().equals(company.getId())) {
            log.error("Permission required failed");
            throw new AccessDeniedException("Không có quyền để thưc hiện");
        }
        Field nameField = object.getClass()
                .getDeclaredField("customer");
        nameField.setAccessible(true);
        nameField.set(object, account.getCustomer());
    }

    public void setCustomerFor(Object object) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new Exception("Không tìm thấy user"));
        Field nameField = object.getClass()
                .getDeclaredField("customer");
        nameField.setAccessible(true);
        nameField.set(object, account.getCustomer());


    }

}
