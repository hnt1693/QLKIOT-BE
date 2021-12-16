package com.fil.authentication.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HelloController {

    @PreAuthorize("hasRole('ROLE_QUANLY_BAOVE')")
    @GetMapping("/hello")
    public String get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        authentication.getAuthorities().forEach(System.out::println);
        return "HELLo";
    }


}
