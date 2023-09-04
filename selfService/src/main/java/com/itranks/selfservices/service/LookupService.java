package com.itranks.selfservices.service;

import com.itranks.selfservices.entity.Employee;
import com.itranks.selfservices.entity.Language;
import com.itranks.selfservices.entity.ServicesMenu;
import com.itranks.selfservices.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.itranks.selfservices.client.DBClient;

import java.util.List;

@Service
public class LookupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupService.class);
    @Autowired
    DBClient dbClient;

    public ResponseEntity<Response<List<Language>>> AllLanguage() {
        LOGGER.info("LookupService ", ".......getLanguages");
        List<Language> languages = dbClient.getLanguages();
        Response<List<Language>> response = Response.<List<Language>>builder().ResponseMessage("success").data(languages).ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<Employee>> getInfo(String lang, Integer pUserId) {
        LOGGER.info("LookupService ", ".......getInfo");
        Employee languages = dbClient.getInfo(lang, pUserId);
        Response<Employee> response = Response.<Employee>builder().ResponseMessage("success").data(languages).ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<List<ServicesMenu>>> getServices(String lang, Integer pUserId , String pParentId) {
        LOGGER.info("Services ", ".......getServices");
        List<ServicesMenu> services = dbClient.getServices(lang, pUserId ,pParentId);
        Response<List<ServicesMenu>> response = Response.<List<ServicesMenu>>builder().ResponseMessage("success").data(services).ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }
}
