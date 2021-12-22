package com.metrix.researcher.service;

import com.metrix.researcher.handler.FeignFallbackHandler;
import com.metrix.researcher.service.impl.PaperServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * sentinel 和 openFeign 整合使用
 * fallback: 服务降级，需要实现此接口类，然后实现方法，不过方法中是调用接口失败的处理问题，或者限流
 * fallbackFactory: 服务降级，统一处理接口调用失败问题，或者限流
 * @author Stephen.cai
 * @date 2021/12/22
 */
//@FeignClient(value = "paper-center", path = "/paper", fallbackFactory = FeignFallbackHandler.class)
@FeignClient(value = "paper-center", path = "/paper", fallback = PaperServiceFallback.class)
public interface PaperService {

    @RequestMapping("/list")
    String paperList();

}
