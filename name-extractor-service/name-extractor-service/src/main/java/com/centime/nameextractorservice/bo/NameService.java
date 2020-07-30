package com.centime.nameextractorservice.bo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NameService {
    private final String WHITE_SPACE=" ";
    private static final Logger logger = LoggerFactory.getLogger(NameService.class);
    public String getFullName(String firstName,String surname){
        String fullName=firstName+WHITE_SPACE+ surname;
        logger.debug("Extracted name is "+fullName);
        return fullName;
    }


}
