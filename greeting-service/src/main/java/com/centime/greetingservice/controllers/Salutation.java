package com.centime.greetingservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Salutation {
    private static final Logger logger = LoggerFactory.getLogger(Salutation.class);
    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        logger.debug("Greeting service Processing Hello");
        return ResponseEntity.ok("Hello");
    }
}
