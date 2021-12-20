package com.metrix.researcher.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author stephen.cai
 * @date 2021/12/14
 **/
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level logger(){
        return Logger.Level.FULL;
    }

}
