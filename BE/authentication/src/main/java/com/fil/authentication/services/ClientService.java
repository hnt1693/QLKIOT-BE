package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.ClientDetails;

import java.util.List;

public interface ClientService extends InterfaceService<ClientDetails> {

    public ResponseAPI getAllClient() throws Exception;

    public ResponseAPI findById(String id) throws Exception;

    public ResponseAPI deleteByStringIds(List<String> ids) throws Exception;
}
