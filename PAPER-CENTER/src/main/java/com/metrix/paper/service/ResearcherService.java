package com.metrix.paper.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "researcher-center", path = "/researcher")
public interface ResearcherService {

    @RequestMapping("/list")
    String researcherList();

}
