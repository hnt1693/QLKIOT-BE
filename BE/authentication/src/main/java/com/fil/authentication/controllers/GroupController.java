package com.fil.authentication.controllers;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.Group;
import com.fil.authentication.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/groups")
@PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
public class GroupController {
    @Autowired
    private GroupService groupService;


    @GetMapping
    public Object getAll() throws Exception {
        return groupService.getAll(null, null,null, null, null);
    }

    @PostMapping
    public ResponseAPI create(@Valid @RequestBody Group group) throws Exception {
        return groupService.create(group);
    }

    @PutMapping
    public ResponseAPI put(@Valid @RequestBody Group group) throws Exception {
        return groupService.put(group);
    }

    @GetMapping("/{id}")
    public ResponseAPI findById(@PathVariable Long id) throws Exception {
        return groupService.findById(id);
    }
}
