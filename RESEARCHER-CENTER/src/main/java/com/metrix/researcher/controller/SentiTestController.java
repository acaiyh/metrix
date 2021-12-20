package com.metrix.researcher.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author stephen.cai
 * @date 2021/12/16
 **/
@RestController
public class SentiTestController {

    private final Logger logger = LoggerFactory.getLogger(SentiTestController.class);

    private static final String RESOURCE_NAME = "list";

    @GetMapping("/senti/test")
    public String test(){
        Entry entry = null;
        try {
            entry = SphU.entry(RESOURCE_NAME);
            logger.info("senti/test {}", "访问");
        } catch (BlockException e) {
            logger.error("access block");
        } catch (Exception e){
            Tracer.traceEntry(e, entry);
        } finally {
            if(Objects.nonNull(entry)){
                entry.exit();
            }
        }
        return "fail";
    }

    /**
     * 定义流控规则
     * @author Stephen.cai
     * @date 2021/12/16
     */
    @PostConstruct
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        //设置保护资源
        flowRule.setResource(RESOURCE_NAME);
        //设置流控规则 QPS
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护资源阈值
        flowRule.setCount(1);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }


}
