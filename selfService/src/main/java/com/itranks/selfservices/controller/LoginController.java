package com.itranks.selfservices.controller;

import com.itranks.selfservices.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itranks.selfservices.client.DBClient;

import java.util.Map;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private DBClient dbClient;

    @PostMapping("/Login")
    public ResponseEntity signIn(@RequestBody LoginRequest loginRequest, @RequestHeader("Accept-Language") String lang) {
        Map<String, Object> loginData = dbClient.login(loginRequest, lang);
        return ResponseEntity.ok(loginData);
    }
}
