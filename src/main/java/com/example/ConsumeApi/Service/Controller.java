package com.example.ConsumeApi.Service;

import com.example.ConsumeApi.Objects.UserResponse;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    String url = "https://jsonplaceholder.typicode.com/users/1";
    HttpHeaders httpHeaders ;
    @RequestMapping("/Hello")
    public UserResponse callApi(HttpServletResponse response1){
        response1.setContentType("application/json");
        httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> Request = new HttpEntity<>(httpHeaders) ;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        //In the response you pass in the method response.exchange then pass in the url,http method and the httpEntity
        response = restTemplate.exchange(url,HttpMethod.GET,Request,String.class);
        UserResponse userResponse = new UserResponse();
//        When using jsonMapper, a try catch is a must
        JsonMapper jsonMapper = new JsonMapper();
        try{
            userResponse = jsonMapper.readValue(response.getBody(), UserResponse.class);
        }catch (Exception e){
            System.out.println(e);
        }
        userResponse.setName("Stella");

        return userResponse;
    }
}