package com.itranks.gateway.controller;

import com.itranks.gateway.client.DBClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.itranks.gateway.config.auth.JwtAuthenticationResponse;
import com.itranks.gateway.config.auth.UserPrincipal;
import com.itranks.gateway.dto.LoginRequest;
import com.itranks.gateway.entity.User;
import com.itranks.gateway.exceptions.RuntimeBusinessException;
import com.itranks.gateway.repository.UserRepository;
import com.itranks.gateway.service.JWTTokenProvider;
import com.itranks.gateway.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private DBClient dbClient;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/Login")
    public ResponseEntity signIn(@RequestBody LoginRequest loginRequest, @RequestHeader("Accept-Language") String lang) {

        Map<String, Object> loginData = dbClient.login(loginRequest, lang);

        Object employeeDataObject = loginData.get("employeeData");

        Object result = loginData.get("result");

        log.info("employeeDataObject Created {}", employeeDataObject);

        Object user_id = loginData.get("p_user_id");

        Long user_id_long = Long.parseLong(user_id.toString());

        log.info("user_id Created {}", user_id);

        if (user_id_long == 0) {
            log.info("RuntimeBusinessException {}", result);
            throw new RuntimeBusinessException(String.valueOf(result));
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(loginRequest.getPassword());
        Optional<User> existingUser = userRepository.findByUserName(loginRequest.getUserName());
        if (!existingUser.isPresent()) {
            User u1 = new User();
            u1.setId(user_id_long);
            u1.setPassword(encodedPassword);
            log.info("loginRequest.getUserName() before {}", loginRequest.getUserName());
            u1.setUserName(loginRequest.getUserName());
            log.info("loginRequest.getUserName() after {}", u1.getUserName());

            userRepository.save(u1);
        }
        User u1 = existingUser.get();
        log.info("existingUser {}", u1);
        UserPrincipal user = new UserPrincipal(u1);
        user.setUserName(loginRequest.getUserName());
        user.setPassword(encodedPassword);

        log.info("UserPrincipal {}", user);
        String token = jwtTokenProvider.generateToken(user);
        log.info("Token Created {}", token);
        Map<String, Object> employeeDataMap = new HashMap<>();
        employeeDataMap.put("employee", employeeDataObject);
        employeeDataMap.put("token", new JwtAuthenticationResponse(token));
        log.info("employee {}", employeeDataMap);
        return ResponseEntity.ok(employeeDataMap);
    }
}
