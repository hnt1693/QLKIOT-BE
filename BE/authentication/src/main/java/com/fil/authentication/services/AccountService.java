package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.Account;
import com.fil.authentication.models.UserDetails;
import com.fil.authentication.payload.dto.AccountPayload;

public interface AccountService extends InterfaceService<Account> {

    ResponseAPI createDetails(UserDetails postData) throws Exception;

    ResponseAPI createBySuperAdmin(Account postData) throws Exception;

    ResponseAPI updateDetails(UserDetails putData) throws Exception;

    ResponseAPI changePasswordByAdmin(String password) throws Exception;

    ResponseAPI forgotPassword(String email, String password) throws Exception;

    ResponseAPI create(AccountPayload accountPayload) throws Exception;
}
