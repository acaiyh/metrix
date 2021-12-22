package com.metrix.researcher.service.impl;

import com.metrix.researcher.service.PaperService;
import org.springframework.stereotype.Component;

/**
 * 服务 降级，需要实现服务接口
 * @author stephen.cai
 * @date 2021/12/22
 **/
@Component
public class PaperServiceFallback implements PaperService {
    @Override
    public String paperList() {
        return "{\"code\":500,\"msg\":\"Response error\"}";
    }
}
