package com.metrix.researcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author stephen.cai
 * @date 2021/12/13
 **/
@SpringBootApplication
@EnableFeignClients
public class ResearcherApp {

    public static void main(String[] args) {
        SpringApplication.run(ResearcherApp.class, args);
    }

}
