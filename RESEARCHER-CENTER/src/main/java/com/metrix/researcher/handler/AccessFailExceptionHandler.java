package com.metrix.researcher.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stephen.cai
 * @date 2021/12/16
 **/
public class AccessFailExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AccessFailExceptionHandler.class);

    /**
     * 如果接口执行报异常，被调用此处
     * 此方法参数必须和接口的参数保持一致，不然不起作用，后续再验证
     * @author Stephen.cai
     * @date 2021/12/17
     */
    public static String fallBack(Integer offset, Integer limit,Throwable e){
        logger.info("服务容错 offset={}, limit={}, {}", offset, limit, e.getMessage());
        return "<script>alert('服务器忙，请稍后再试')</script>";
    }

    /**
     * 如果请求量超过 sentinel 的阈值，调用此处
     * @author Stephen.cai
     * @date 2021/12/17
     */
    public static String accessFailHandler(BlockException e){
        logger.info("服务被限流，异步处理");
        return "<script>alert('服务器忙，请稍后再试')</script>";
    }

    public static String paperListFallback(Throwable e){
        logger.error("请求过多 {}", e.getMessage());
        return "<script>alert('服务器忙，请稍后再试')</script>";
    }

    public static String paperListAccessHandler(Throwable e){
        return "<script>alert('服务器忙，请稍后再试')</script>";
    }

}
