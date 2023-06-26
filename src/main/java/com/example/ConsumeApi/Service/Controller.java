package com.example.ConsumeApi.Service;

import com.example.ConsumeApi.Objects.Address;
import com.example.ConsumeApi.Objects.Company;
import com.example.ConsumeApi.Objects.Geo;
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
        if(response.getStatusCode()!= HttpStatus.OK){
            return null;
        }
        UserResponse userResponse = new UserResponse();
//        When using jsonMapper, a try catch is a must
        JsonMapper jsonMapper = new JsonMapper();
        try{
            userResponse = jsonMapper.readValue(response.getBody(), UserResponse.class);
        }catch (Exception e){
            System.out.println(e);
        }
     userResponse.setName("Stella");
       userResponse.setUsername("Bella");
                userResponse.setEmail("mbithestella0@gmail.com");

        Address address = userResponse.getAddress();
        address.setCity("Nairobi");
        address.setStreet("Tom Mboya");
        address.setSuite("Apt.1234");
        address.setZipcode("00100");

        Geo geo = address.getGeo();
        geo.setLat("-40");
        geo.setLng("81");

        userResponse.setPhone("0790692833");
        userResponse.setWebsite("BossBella");

        Company company = userResponse.getCompany();
        company.setName("SteshM limited");
        company.setCatchPhrase("Multi-layered client-server neural-net");
        company.setBs("harness real-time e-markets");




        return userResponse;
    }
}