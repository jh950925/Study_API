package com.example.aloneDrinlk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AloneDrinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AloneDrinkApplication.class, args);
        String className = new Object(){}.getClass().getName().toString();
        log.info(className);
        log.info("=====================================");
    }

}
