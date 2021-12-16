package com.fil.authentication.services;

import com.fil.authentication.commons.Pagination;
import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.constants.Messages;
import com.fil.authentication.dao.GroupDao;
import com.fil.authentication.models.Group;
import com.fil.authentication.repository.GroupRepository;
import com.fil.authentication.security.PermissionCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PermissionCustomerService permisssCustomerService;
    @Autowired
    private GroupDao groupDao;

    @Override
    public Object getAll(String searchData, String sortData, String fields, Integer page, Integer pageSize) throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        Map<String, Object> mapData = new HashMap<>();
        if (null != page && null != pageSize) {
            mapData.put("pagination", new Pagination(page, pageSize, groupDao.count(searchData, sortData)));
        }
        mapData.put("pagination", new Pagination(0, 20, groupDao.count(searchData, sortData)));
        List<Group> groups = groupDao.getGroup(null, null, null);
        mapData.put("data", groups);
        mapData.put("status", HttpStatus.OK.value());
        mapData.put("msg", Messages.getSuccess("danh sách nhóm"));
        return mapData;
    }

    @Transactional
    @Override
    public ResponseAPI create(Group postData) throws Exception, AccessDeniedException {
        permisssCustomerService.setCustomerFor(postData);
        postData = groupRepository.save(postData);
        return new ResponseAPI(Messages.createSuccess("nhóm"), postData);
    }


    @Transactional
    @Override
    public ResponseAPI put(Group putData) throws Exception, AccessDeniedException {
        Group group = groupRepository.findById(putData.getId()).orElse(null);
        if (group == null) throw new Exception(Messages.notFound("nhóm"));
        permisssCustomerService.checkCustomerPermsission(group.getCustomer(), putData);
        putData.getAccounts().forEach(ob -> System.out.println(ob.getUsername()));
        putData = groupRepository.save(putData);
        return new ResponseAPI(Messages.updateSuccess("nhóm"), putData);
    }

    @Override
    public ResponseAPI findById(Long id) throws Exception, AccessDeniedException {
        return new ResponseAPI(Messages.getSuccess("nhóm"), groupDao.findById(id));

    }

    @Override
    public ResponseAPI deletes(List<Long> ids) throws Exception, AccessDeniedException {
        return null;
    }

    @Override
    public ResponseAPI createGroupBySuperAdmin(Group postData) {
        return null;
    }
}
