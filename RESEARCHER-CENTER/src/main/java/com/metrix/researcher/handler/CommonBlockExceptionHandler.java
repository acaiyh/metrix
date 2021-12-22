package com.metrix.researcher.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务熔断，降级 处理
 * @author stephen.cai
 * @date 2021/12/21
 **/
@Component
public class CommonBlockExceptionHandler implements BlockExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CommonBlockExceptionHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        logger.error("Access error {}", e.getMessage());
        if(e instanceof FlowException){
            logger.info("===服务限流===");
        }else if(e instanceof DegradeException){
            logger.info("===服务降级===");
        }else if(e instanceof ParamFlowException){
            logger.info("===热点参数限流===");
        }else if(e instanceof SystemBlockException){
            logger.info("===触发系统参数保护===");
        }else if(e instanceof AuthorityException){
            logger.info("===授权规则限制===");
        }
        // 返回给客户端信息
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"code\":500,\"msg\":\"access fail\"}");
    }
}
