//package com.fil.authentication.controllers;
//
//import com.fil.authentication.commons.ResponseAPI;
//import com.fil.authentication.models.Role;
//import com.fil.authentication.services.RoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/auth/role")
//public class RoleController {
//
//    @Autowired
//    private RoleService roleService;
//
//    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
//    @GetMapping
//    public Object getAll() throws Exception {
//        return roleService.getAll(null, null, null, null);
//    }
//
//    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
//    @PostMapping
//    public ResponseAPI create(@Valid @RequestBody Role role) throws Exception {
//        return roleService.create(role);
//    }
//
////    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
//    @PreAuthorize("hasRole('ROLE_THUKHO1') and hasPermission('ROLE_THUKHO1','READ')")
//    @PutMapping
//    public ResponseAPI put(@Valid @RequestBody Role role) throws Exception {
//        return roleService.put(role);
//    }
//}
//
//
