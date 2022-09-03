package com.zzwei.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello1(){
        return "hello1";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello2(){
        return "hello2";
    }
}
