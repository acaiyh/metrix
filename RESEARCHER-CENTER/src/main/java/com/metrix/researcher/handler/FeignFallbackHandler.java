package com.metrix.researcher.handler;

import com.metrix.researcher.service.PaperService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 服务熔断，降级 统一处理
 * sentinel 和 openFeign 整合使用
 * @author stephen.cai
 * @date 2021/12/22
 **/
@Component
public class FeignFallbackHandler implements FallbackFactory<PaperService> {

    private final Logger logger = LoggerFactory.getLogger(FeignFallbackHandler.class);

    @Override
    public PaperService create(Throwable throwable) {
        logger.info("Feign服务降级");
        return new PaperService() {
            @Override
            public String paperList() {
                return "Access fail";
            }
        };
    }
}
