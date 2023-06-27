package com.example.ConsumeApi.Service;

import com.example.ConsumeApi.Objects.Address;
import com.example.ConsumeApi.Objects.Company;
import com.example.ConsumeApi.Objects.Geo;
import com.example.ConsumeApi.Objects.UserResponse;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    String url = "https://jsonplaceholder.typicode.com/users";
    HttpHeaders httpHeaders ;

    public ResponseEntity<String> callApi(HttpServletResponse response1, String url){
        response1.setContentType("application/json");
        httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> Request = new HttpEntity<>(httpHeaders) ;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        //In the response you pass in the method response.exchange then pass in the url,http method and the httpEntity
        response = restTemplate.exchange(url,HttpMethod.GET,Request,String.class);
//        if(response.getStatusCode()!= HttpStatus.OK){
//            return null;
//        }
//        UserResponse userResponse = new UserResponse();
////        When using jsonMapper, a try catch is a must
//        JsonMapper jsonMapper = new JsonMapper();
//        try{
//            userResponse = jsonMapper.readValue(response.getBody(), UserResponse.class);
//        }catch (Exception e){
//            System.out.println(e);
//        }


        return response;
    }
    @GetMapping("get1User/{id}")
    public ResponseEntity<String>Get1User( HttpServletResponse response,@PathVariable("id") long id){
        return this.callApi(response,url+"/"+id);
    }
    @GetMapping("getUsers")
    public ResponseEntity<String>GetUsers( HttpServletResponse response){
        return this.callApi(response,url);
    }

}