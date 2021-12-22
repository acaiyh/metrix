package com.metrix.paper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author stephen.cai
 * @date 2021/12/16
 **/
@SpringBootApplication
@EnableFeignClients
public class PaperApp {

    public static void main(String[] args) {
        SpringApplication.run(PaperApp.class, args);
    }

}
