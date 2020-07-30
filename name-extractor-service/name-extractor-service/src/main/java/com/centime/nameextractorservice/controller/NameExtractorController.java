package com.centime.nameextractorservice.controller;

import com.centime.nameextractorservice.bo.NameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.util.Map;
import java.util.Objects;

@RestController
public class NameExtractorController {
    @Autowired
    private NameService nameService;

    private static final Logger logger = LoggerFactory.getLogger(NameExtractorController.class);


    @PostMapping("/fullName")
    ResponseEntity<String> getFullName(@RequestBody Map<String,String> nameMap) {

        if(Objects.isNull(nameMap) || !(nameMap.containsKey("firstName") && nameMap.containsKey("lastName"))) {
            String errMsg="Invalid input passed";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        logger.debug("Name passed is " +nameMap.toString());
        String firstName=nameMap.get("firstName");
        String lastName=nameMap.get("lastName");
        return ResponseEntity.ok(nameService.getFullName(firstName,lastName));
    }



}
