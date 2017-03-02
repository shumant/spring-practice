package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class TestController {

    @RequestMapping("/home")
    String home(){
        return "home";
    }

    @RequestMapping("/*")
    ResponseEntity<String> path(@PathVariable("key") String path) {
        return new ResponseEntity<>(path, HttpStatus.OK);
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/test_uri", params = "mall_id")
    public void testUri(@RequestParam(value = "mall_id", required = false) long mallId) {
        int k = 2;
    }

    @RequestMapping("/uri")
    public ResponseEntity<String> uri () {
        ResponseEntity<?> response = new RestTemplate().getForEntity("http://localhost:8084/test_uri", ResponseEntity.class, Collections.singletonMap("mall_id", 123));
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
}
