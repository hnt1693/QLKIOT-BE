package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.constants.Messages;
import com.fil.authentication.models.ClientDetails;
import com.fil.authentication.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Object getAll(String searchData, String sortData, String fields, Integer page, Integer pageSize) throws Exception {
        Map<String, Object> mapData = new HashMap<>();
        List<ClientDetails> clientDetails = clientRepository.findAll();
        mapData.put("data", clientDetails);
        mapData.put("msg", Messages.getSuccess("client"));
        mapData.put("status", HttpStatus.OK.value());
        return mapData;
    }

    @Override
    public ResponseAPI create(ClientDetails postData) throws Exception, AccessDeniedException {
        postData.setClientSecret(passwordEncoder.encode(postData.getClientSecret()));
        postData = clientRepository.save(postData);
        return new ResponseAPI(Messages.createSuccess("client"), postData);
    }

    @Override
    public ResponseAPI put(ClientDetails putData) throws Exception, AccessDeniedException {
        clientRepository.findById(putData.getClientId()).orElseThrow(() -> new Exception("Không tồn tại client"));
        putData.setClientSecret(passwordEncoder.encode(putData.getClientSecret()));
        putData = clientRepository.save(putData);
        return new ResponseAPI(Messages.updateSuccess("client"), putData);
    }

    @Override
    public ResponseAPI findById(Long id) throws Exception, AccessDeniedException {
        return null;
    }

    @Override
    public ResponseAPI deletes(List<Long> ids) throws Exception, AccessDeniedException {
        return null;
    }


    @Override
    public ResponseAPI getAllClient() throws Exception {
        List<ClientDetails> clientDetails = clientRepository.findAllByShow(true);
        return new ResponseAPI(Messages.getSuccess("client"), clientDetails);
    }

    @Override
    public ResponseAPI findById(String id) throws Exception {
        ClientDetails client = clientRepository.findById(id).orElseThrow(() -> new Exception("Không tồn tại client"));
        return new ResponseAPI(Messages.getSuccess("client"), client);
    }

    @Override
    public ResponseAPI deleteByStringIds(List<String> ids) throws Exception {
        clientRepository.deletes(ids);
        return new ResponseAPI(Messages.deleteSuccess("client"), null);
    }


}
