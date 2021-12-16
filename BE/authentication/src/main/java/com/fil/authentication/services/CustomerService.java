package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.Customer;
import com.fil.authentication.payload.dto.CustomerPayload;

import java.nio.file.AccessDeniedException;

public interface CustomerService extends InterfaceService<Customer> {

    ResponseAPI create(CustomerPayload postData) throws Exception, AccessDeniedException;


}
