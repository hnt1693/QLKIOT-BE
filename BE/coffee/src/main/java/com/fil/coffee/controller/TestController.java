package com.fil.coffee.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        System.out.println(1123);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(System.out::println);

        return "hello";
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN_SS')")
    @GetMapping("/123")
    public String test2() {
        System.out.println(124);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        authentication.getAuthorities().forEach(ob -> {
            System.out.println(ob.getAuthority());
        });
        return "AUTHEN";
    }

}
