package com.xiaowudr.myuploader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class ATongMuServerApplication {

    private static final Logger log = LoggerFactory.getLogger(ATongMuServerApplication.class);

    public static void main(String[] args) {
        if (log.isDebugEnabled()) {
            log.debug("FileUploadServerApplication started");
        }
        SpringApplication.run(ATongMuServerApplication.class, args);
    }

}