package com.learning.seccion6_interceptores_http.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class FooController {

    @GetMapping("/foo")
    public Map<String, String> foo(){
        return Collections.singletonMap("message", "handler FOO del controlador");
    }
    @GetMapping("/bar")
    public Map<String, String> bar(){
        return Collections.singletonMap("message", "handler BAR del controlador");
    }
    @GetMapping("/baz")
    public Map<String, String> baz(){
        return Collections.singletonMap("message", "handler BAZ del controlador");
    }
}
