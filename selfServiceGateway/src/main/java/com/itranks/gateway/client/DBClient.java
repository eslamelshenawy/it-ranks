package com.itranks.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.itranks.gateway.dto.LoginRequest;

import java.util.Map;

@FeignClient(name = "selfservice")
public interface DBClient {
    @GetMapping("/Login")
    Map<String, Object> login(@RequestBody LoginRequest loginRequest, @RequestHeader("Accept-Language") String lang);
}
