package com.metrix.researcher.service.impl;

import com.metrix.researcher.service.RemoteService;
import com.metrix.researcher.service.ResearcherService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.springframework.stereotype.Service;

/**
 * @author stephen.cai
 * @date 2021/12/14
 **/
@Service
public class ResearcherServiceImpl implements ResearcherService {

    @Override
    public String getResearcher(Integer offset, Integer limit) {
        RemoteService remoteService = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 1))
                .target(RemoteService.class, "http://192.168.0.65:18081/janus/app/metrix/v2");
        return remoteService.callUrl("{'$table':'hcp'}", offset, limit, "id,name_cn,name_en");
    }

    @Override
    public String getPaperList() {
        RemoteService remoteService = Feign.builder()
                .options(new Request.Options(51000, 15000))
                .retryer(new Retryer.Default(15000, 15000, 1))
                .target(RemoteService.class, "http://localhost:8002/paper");
        return remoteService.callPaper();
    }


}
