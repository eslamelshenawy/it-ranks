package com.example.dbservice;

import com.example.dbservice.model.Employee;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.dbservice.constatnts.TestCommons.getHttpEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void loginSuccess() throws Exception {
        JSONObject requestJson = createNewDummyLoginRequestSuccess();
        HttpEntity<?> request = getHttpEntity(requestJson.toString(), "NOT FOUND", "AMERICAN");
        ResponseEntity<String> res = template.exchange("/Login", POST, request, String.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
        assertEquals("{\"result\":\"1\",\"p_user_id\":1014804,\"employeeData\":{\"id\":18655,\"disp_NAME\":\"Suzan Rajab\",\"d_TITLE\":\"Mrs.\",\"job_NAME\":\"Manager\",\"d_SEX\":\"Female\",\"image\":\"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\"}}", res.getBody());
    }

    @Test
    public void loginFailedValidate() throws Exception {
        JSONObject requestJson = createNewDummyLoginRequestFailed();
        HttpEntity<?> request = getHttpEntity(requestJson.toString(), "NOT FOUND", "ARABIC");
        ResponseEntity<String> res = template.exchange("/Login", POST, request, String.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
        assertEquals("{\"result\":\"فشل تسجيل الدخول. رجاء التأكد من معلومات الدخول أو الاتصال بمسئول النظام\",\"p_user_id\":0}", res.getBody());
    }

    @Test
    public void SetSessionTest() throws Exception {
        HttpEntity<?> request = getHttpEntity("null", "ARABIC");
        ResponseEntity<Void> res = template.exchange("/alter?p_user_id=1014804", POST, request, Void.class);
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }

    @Test
    public void getInfoTest() throws Exception {
        SetSessionTest();
        HttpEntity<?> request = getHttpEntity("null", "ARABIC");
        Integer pUserId = 1014804;
        ResponseEntity<Employee> res = template.exchange("/info/" + pUserId, GET, request, new ParameterizedTypeReference<Employee>() {
        });
        assertEquals(HttpStatus.valueOf(200), res.getStatusCode());
    }

    private JSONObject createNewDummyLoginRequestSuccess() throws JSONException {
        JSONObject loginRequest = new JSONObject();
        loginRequest.put("userName", "A.RADWAN");
        loginRequest.put("password", "R123456");
        return loginRequest;

    }

    private JSONObject createNewDummyLoginRequestFailed() throws JSONException {
        JSONObject loginRequest = new JSONObject();
        loginRequest.put("userName", "A.RADWAN789");
        loginRequest.put("password", "R123456");
        return loginRequest;

    }

}
