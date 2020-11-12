package com.springdemo.buryingpoint.controller;

import com.alibaba.fastjson.JSON;
import com.springdemo.buryingpoint.model.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/")
@Slf4j
public class DemoController {

    @PostMapping("/dosomething")
    public String dosomething() throws UnknownHostException {
        String ip =  InetAddress.getLocalHost().getHostAddress();
        log.info("DemoController####ip=>{}",ip);

        log.info(".............");
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("200");
        resultVO.setDate("123");
        resultVO.setMsg("jurongbing");
        return JSON.toJSONString(resultVO);
    }

    @PostMapping("/doother")
    public String doother() {
        log.info(".............");
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("200");
        resultVO.setDate("123");
        resultVO.setMsg("jurongbing");
        return JSON.toJSONString(resultVO);
    }

}
