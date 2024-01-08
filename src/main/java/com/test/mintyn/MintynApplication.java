package com.test.mintyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MintynApplication {

    public static void main(String[] args) {
        SpringApplication.run(MintynApplication.class, args);
    }

}
