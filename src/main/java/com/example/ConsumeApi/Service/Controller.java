package com.example.ConsumeApi.Service;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    String url = "https://emailvalidation.abstractapi.com/v1/?api_key=e86b841824ec4b7bb9a3ac1aa0ad2463&email=mbithestella0@gmail.com";
    HttpHeaders httpHeaders ;
    @RequestMapping("/Hello")
    public ResponseEntity<String> callApi(){

        httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> Request = new HttpEntity<>(httpHeaders) ;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        response = restTemplate.exchange(url,HttpMethod.GET,Request,String.class);
        return response;
    }
}