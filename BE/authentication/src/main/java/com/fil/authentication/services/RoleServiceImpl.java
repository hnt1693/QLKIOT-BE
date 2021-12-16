package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.constants.Messages;
import com.fil.authentication.models.Role;
import com.fil.authentication.repository.RoleRepository;
import com.fil.authentication.security.PermissionCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionCustomerService permissionCompanyService;

    @Override
    public ResponseAPI getAll(String searchData, String sortData, String fields,Integer page, Integer pageSize) throws Exception {
        return new ResponseAPI(Messages.getSuccess("quyền"), roleRepository.findAll());

    }

    @Override
    public ResponseAPI create(Role postData) throws Exception, AccessDeniedException {
//        permissionCompanyService.setCompanyPermission(postData);
        roleRepository.save(postData);
        return new ResponseAPI(Messages.createSuccess("role"), postData);

    }

    @Override
    public ResponseAPI put(Role putData) throws Exception, AccessDeniedException {
        roleRepository.save(putData);
        return new ResponseAPI(Messages.updateSuccess("role"), putData);
    }

    @Override
    public ResponseAPI findById(Long id) throws Exception, AccessDeniedException {
        return null;
    }

    @Override
    public ResponseAPI deletes(List<Long> ids) throws Exception, AccessDeniedException {
        return null;
    }
}
