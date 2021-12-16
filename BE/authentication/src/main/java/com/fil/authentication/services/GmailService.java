package com.fil.authentication.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.Account;

import javax.mail.MessagingException;

public interface GmailService {

    void sendActiveMail(Account account) throws MessagingException;

    void sendResetPasswordMail(Account account) throws MessagingException, JsonProcessingException;
}
