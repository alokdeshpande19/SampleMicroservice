package com.centime.clientfacingservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.centime.clientfacingservice.constants.GlobalConstants.UP;
import static com.centime.clientfacingservice.constants.GlobalConstants.WHITE_SPACE;

@RestController
public class ClientController {

    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @GetMapping("/status")
    public ResponseEntity<String> checkStatus(){
        return ResponseEntity.ok(UP);
    }

    @PostMapping("/greetName")
    ResponseEntity<String> greetName(@RequestBody Map<String,String> nameMap) {
        String greetResponse=null;
        logger.debug("Before Calling Greeting Service");
        ResponseEntity<String> response=restTemplate.getForEntity("http://GREETING-SERVICE/hello",String.class);
        logger.debug("After Calling Greeting Service");
        if(response.getStatusCode()== HttpStatus.OK) {
            String greeting = response.getBody();
            logger.debug("Before Calling Name Extractor Service");
            ResponseEntity<String> nameExtractorServiceResponse = restTemplate.postForEntity("http://NAME-EXTRACTOR-SERVICE/fullName", nameMap, String.class);
            logger.debug("After Calling Name Extractor Service");
            if (nameExtractorServiceResponse.getStatusCode() == HttpStatus.OK) {
                greetResponse = greeting + WHITE_SPACE + nameExtractorServiceResponse.getBody();
                return ResponseEntity.ok(greetResponse);
            }
            if(nameExtractorServiceResponse.getStatusCode()==HttpStatus.BAD_REQUEST){
                return nameExtractorServiceResponse;
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
