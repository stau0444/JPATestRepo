package com.ugo.jpatest.controller;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("")
    public String hello() {

        return  "hello";
    }
}
