package org.example.firstapi.components;

import org.example.firstapi.dtos.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthUtils {
    private final RestTemplate restTemplate;

    @Autowired
    public AuthUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(String tokenValue) {
        String body = "{\"token\":\"" + tokenValue + "\"}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(body,headers);
        try{
            Token token = restTemplate.postForObject("http://localhost:8080/user/validate-token", request, Token.class);
            System.out.println("Token" + token);
            return token != null;
        }catch(Exception e){
            return false;
        }
    }

}
