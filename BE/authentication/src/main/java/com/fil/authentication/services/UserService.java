package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.payload.request.AccountResetPWPayload;

import java.util.Map;

public interface UserService {

    public ResponseAPI getDetails(String name) throws Exception;

    public ResponseAPI login(Map request) throws Exception;

    public ResponseAPI revokeToken(String token) throws Exception;

    public ResponseAPI forgotPassword(AccountResetPWPayload accountPayload) throws Exception;

    public ResponseAPI confirmChangeForgotPassword(String username, String key) throws Exception;

}
