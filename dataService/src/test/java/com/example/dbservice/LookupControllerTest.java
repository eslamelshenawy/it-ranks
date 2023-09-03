package com.example.dbservice;

import com.example.dbservice.dto.LanguageDTO;
import com.example.dbservice.model.Employee;
import com.example.dbservice.model.Language;
import com.example.dbservice.model.ServicesMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.dbservice.constatnts.TestCommons.getHttpEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LookupControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void hello() throws Exception {
        ResponseEntity<List<Language>> res = template.exchange("/lookup/languages", HttpMethod.GET, null, new ParameterizedTypeReference<List<Language>>() {
        });

        List<Language> languages = res.getBody();
        assertEquals(2, languages.size());
        Language language1 = languages.get(0);
        assertEquals("AMERICAN", language1.getValue());
        assertEquals("American", language1.getId());
        Language language2 = languages.get(1);
        assertEquals("ARABIC", language2.getValue());
        assertEquals("Arabic", language2.getId());
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }

    @Test
    public void SetSessionTest() throws Exception {
        HttpEntity<?> request = getHttpEntity("null", "ARABIC");
        ResponseEntity<Void> res = template.exchange("/alter?p_user_id=1014804", POST, request, Void.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }

    @Test
    public void getServicesTest() throws Exception {
        SetSessionTest();
        HttpEntity<?> request = getHttpEntity("null", "ARABIC");
        Integer pUserId = 1014804;
        ResponseEntity<List<ServicesMenu>> res = template.exchange("/lookup/listServices/" + pUserId, GET, request, new ParameterizedTypeReference<List<ServicesMenu>>() {
        });
        List<ServicesMenu> servicesMenuList = res.getBody();

        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
        assertEquals(6, servicesMenuList.size());
    }

    @Test
    public void getServicesWithParentIdTest() throws Exception {
        SetSessionTest();
        HttpEntity<?> request = getHttpEntity("null", "ARABIC");
        Integer pUserId = 1014804;
        String pParentIdValue = "XXX_OT_PARENT";
        ResponseEntity<List<ServicesMenu>> res = template.exchange("/lookup/listServices/" + pUserId + "/" + pParentIdValue , GET, request, new ParameterizedTypeReference<List<ServicesMenu>>() {
        });
        List<ServicesMenu> servicesMenuList = res.getBody();

        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
        assertEquals(2, servicesMenuList.size());
    }

}
