package com.metrix.paper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stephen.cai
 * @date 2021/12/16
 **/
@RestController
@RequestMapping("/paper")
public class PaperController {

    @GetMapping("/list")
    public String list(){
        /*try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "success";
    }

}
