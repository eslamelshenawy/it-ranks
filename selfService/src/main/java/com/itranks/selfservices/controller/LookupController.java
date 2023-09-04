package com.itranks.selfservices.controller;

import com.itranks.selfservices.entity.Employee;
import com.itranks.selfservices.entity.Language;
import com.itranks.selfservices.entity.ServicesMenu;
import com.itranks.selfservices.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itranks.selfservices.service.LookupService;

import java.util.List;

@RestController
@RequestMapping(value = "/lookup")
public class LookupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupController.class);
    @Autowired
    LookupService lookupService;

    @GetMapping("/languages")
    public ResponseEntity<Response<List<Language>>>  getLanguages() {
        return lookupService.AllLanguage();
    }
    @GetMapping("/info/{pUserId}")
    public ResponseEntity<Response<Employee>>  getInfo(@RequestHeader("Accept-Language") String lang , @PathVariable Integer pUserId) {
        return lookupService.getInfo(lang,pUserId);
    }

    @GetMapping(value ="/listServices/{pUserId}")
    public ResponseEntity<Response<List<ServicesMenu>>> getServices(@RequestHeader("Accept-Language") String lang , @PathVariable Integer pUserId , @RequestParam(required = false) String pParentId){
        return lookupService.getServices(lang,pUserId ,pParentId);
    }
}
