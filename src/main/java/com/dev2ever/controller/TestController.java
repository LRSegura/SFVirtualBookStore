package com.dev2ever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TestController {

//    @RequestMapping("/hello")
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Framework!";
    }
}


