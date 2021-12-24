package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.dao.MenuCaseDao;
import com.fil.authentication.models.ClientDetails;
import com.fil.authentication.models.Group;
import com.fil.authentication.models.MenuCase;
import com.fil.authentication.models.Role;
import com.fil.authentication.repository.GroupRepository;
import com.fil.authentication.repository.MenuCaseRepository;
import com.fil.authentication.commons.Pagination;
import com.fil.authentication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.*;

import com.fil.authentication.repository.ClientRepository;
import com.fil.authentication.constants.Messages;

import javax.transaction.Transactional;

@Service
public class MenuCaseServiceImpl implements MenuCaseService {
    @Autowired
    private MenuCaseRepository menuCaseRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MenuCaseDao menuCaseDao;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Object getAll(String searchData, String sortData, String fields, Integer page, Integer pageSize) throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        Map<String, Object> mapData = new HashMap<>();
        if (null != page && null != pageSize) {
            mapData.put("pagination", new Pagination(page, pageSize, menuCaseDao.count(searchData)));
        }
        mapData.put("pagination", new Pagination(0, 20, menuCaseDao.count(searchData)));
        List<MenuCase> groups = menuCaseDao.getAll(searchData, sortData, pageable);
        mapData.put("data", groups);
        mapData.put("status", HttpStatus.OK.value());
        mapData.put("msg", Messages.getSuccess("danh sách nhóm"));
        return mapData;
    }

    @Override
    public ResponseAPI create(MenuCase postData) throws Exception, AccessDeniedException {
        return null;
    }


    @Override
    public ResponseAPI createWith(MenuCase postData, String clientId) throws Exception, AccessDeniedException {
        ClientDetails clientDetails = clientRepository.findById(clientId).orElseThrow(() -> new Exception("Không tìm thấy client"));
        postData.setClientDetails(clientDetails);
        postData = menuCaseRepository.save(postData);
        return new ResponseAPI(Messages.createSuccess("caseMenu"), postData);
    }

    @Override
    public ResponseAPI putWith(MenuCase putData, boolean modifiedChild) throws Exception {
        MenuCase menuCase = menuCaseRepository.findById(putData.getId()).orElseThrow(() -> new Exception("Không tìm thấy caseMenu"));
        if (!modifiedChild) {
            Set<MenuCase> menuCases = menuCase.getChildMenus();
            putData.setRoles(menuCase.getRoles());
            putData.setChildMenus(menuCases);
        }
        menuCaseRepository.save(putData);
        return new ResponseAPI(Messages.updateSuccess("caseMenu"), putData);
    }

    @Override
    public ResponseAPI getChildByParentId(Long id) throws Exception {
        List<MenuCase> list = menuCaseRepository.findAllChildByIds(Collections.singletonList(id));
        return new ResponseAPI(Messages.getSuccess("menu"), list);
    }

    @Override
    public ResponseAPI put(MenuCase putData) throws Exception, AccessDeniedException {
        return null;
    }


    @Override
    public ResponseAPI findById(Long id) throws Exception, AccessDeniedException {
        MenuCase menuCase = menuCaseRepository.findById(id).orElseThrow(() -> new Exception("Không tồn tại menuCase"));
        return new ResponseAPI(Messages.getSuccess("caseMenu"), menuCase);
    }

    @Transactional
    @Override
    public ResponseAPI deletes(List<Long> ids) throws Exception, AccessDeniedException {
        List<Role> roles = roleRepository.getAllByMenuCaseIdIn(ids);
        List<Group> groups = groupRepository.findAllByRoleIds(ids);
        for (Group group : groups) {
            group.getRoles().removeAll(roles);
        }
        groupRepository.saveAll(groups);
        List<MenuCase> list = menuCaseRepository.findAllByIds(ids);
        menuCaseRepository.deleteAll(list);
        return new ResponseAPI(Messages.deleteSuccess("caseMenu"), null);
    }
}
