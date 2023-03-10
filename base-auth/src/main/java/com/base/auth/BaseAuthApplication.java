package com.base.auth;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class BaseAuthApplication {

//    private SysLoginService

    public static void main(String[] args) {
        SpringApplication.run(BaseAuthApplication.class, args);
    }

}
