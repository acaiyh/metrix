package com.metrix.researcher.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.metrix.researcher.handler.AccessFailExceptionHandler;
import com.metrix.researcher.service.PaperService;
import com.metrix.researcher.service.ResearcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stephen.cai
 * @date 2021/12/14
 **/
@RestController
@RequestMapping("/researcher")
public class ResearcherController {

    private final Logger logger = LoggerFactory.getLogger(ResearcherController.class);

    @Autowired
    ResearcherService researcherService;

    @Autowired
    PaperService paperService;

    @GetMapping("/test")
    public String test(){
        return "ok";
    }

    /**
     * SentinelResource sentinel 服务熔断，降级配置
     * fallback：服务出错处理
     * blockHandler： 服务降级处理
     * @author Stephen.cai
     * @date 2021/12/22
     */
    @SentinelResource(value = "list",
            fallback = "fallBack",
            fallbackClass = AccessFailExceptionHandler.class,
            blockHandler = "accessFailHandler",
            blockHandlerClass = AccessFailExceptionHandler.class)
    @GetMapping("/list")
    public String getResearchers(Integer offset, Integer limit){
        return researcherService.getResearcher(offset, limit);
    }

    @SentinelResource(value = "paperList",
            fallback = "paperListAccessHandler",
            fallbackClass = AccessFailExceptionHandler.class,
            blockHandler = "paperListAccessHandler",
            blockHandlerClass = AccessFailExceptionHandler.class)
    @GetMapping("/paperList")
    public String getPaperList(){
        logger.info("paper list access");
        return researcherService.getPaperList();
    }

    @GetMapping("/papers")
    public String papers(){
        logger.info("papers access");
        return paperService.paperList();
    }

}
