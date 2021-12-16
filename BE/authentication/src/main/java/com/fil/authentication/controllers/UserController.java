package com.fil.authentication.controllers;


import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.payload.request.AccountResetPWPayload;
import com.fil.authentication.services.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "getUser details")
    @GetMapping
    public ResponseAPI getUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getDetails(authentication.getName());
    }

    @PermitAll
    @PostMapping
    public ResponseAPI login(@RequestBody Map obj) throws Exception {
        return userService.login(obj);
    }

    @PermitAll
    @DeleteMapping("/revoke-token")
    public ResponseAPI logout(@RequestParam String token) throws Exception {
        return userService.revokeToken(token);
    }


    @PermitAll
    @GetMapping("/info")
    public java.security.Principal user(Principal user) {
        return user;
    }


    @PermitAll
    @PostMapping("/forgot-password")
    public ResponseAPI forgotPassword(@RequestBody AccountResetPWPayload payload) throws Exception {
        return userService.forgotPassword(payload);
    }

    @PermitAll
    @GetMapping("/confirm-forgot-password")
    public ResponseAPI confirmKeyForgotPassword(@RequestParam String username,
                                                @RequestParam String key) throws Exception {
        return userService.confirmChangeForgotPassword(username, key);
    }
}
