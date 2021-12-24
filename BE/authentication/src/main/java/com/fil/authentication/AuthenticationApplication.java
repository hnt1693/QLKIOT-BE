package com.fil.authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableFeignClients
@EnableAuthorizationServer
@EnableEurekaClient
@SpringBootApplication
public class AuthenticationApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
