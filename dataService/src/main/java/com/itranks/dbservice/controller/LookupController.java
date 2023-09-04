package com.itranks.dbservice.controller;

import com.itranks.dbservice.model.Language;
import com.itranks.dbservice.model.ServicesMenu;
import com.itranks.dbservice.service.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lookup")
public class LookupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupController.class);
    @Autowired
    LookupService lookupService;

    @GetMapping("/languages")
    public List<Language> getLanguages() {
        return lookupService.AllLanguage();
    }

    @GetMapping(value = "/listServices/{pUserId}")
    public List<ServicesMenu> getServices(@RequestHeader("Accept-Language") String lang, @PathVariable Integer pUserId, @RequestParam(required = false) String pParentId) {
        return lookupService.getServices(lang, pUserId, pParentId);
    }

}
