package com.itranks.dbservice.service;

import com.itranks.dbservice.model.Language;
import com.itranks.dbservice.model.ServicesMenu;
import com.itranks.dbservice.procedure.LoginProcedure;
import com.itranks.dbservice.repository.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupService.class);
    @Autowired
    private LanguageRepository repository;
    @Autowired
    private LoginProcedure loginProcedure;

    public List<Language> AllLanguage() {
        LOGGER.info("LanguageService ", ".......getLanguages");
        List<Language> languages = repository.getLanguages();
        return languages;
    }

    public List<ServicesMenu> getServices(String lang, Integer pUserId,String pParentId) {
        LOGGER.info("Services ", ".......getServices");
        List<ServicesMenu> services = loginProcedure.getServices(lang, pUserId,pParentId);
        return services;
    }


}
