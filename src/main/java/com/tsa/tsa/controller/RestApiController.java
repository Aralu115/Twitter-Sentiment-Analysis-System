package com.tsa.tsa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @RequestMapping("/api/hi")
    public String hi() {
        // Developed using https://grokonez.com/frontend/angular/how-to-integrate-angular-6-springboot-2-0-restapi-springtoolsuite
        return "Hello world! This is TSA";
    }
}
