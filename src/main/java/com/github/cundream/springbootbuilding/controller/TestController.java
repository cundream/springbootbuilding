package com.github.cundream.springbootbuilding.controller;

import com.github.cundream.springbootbuilding.common.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Lison
 * @Date: 2019/10/15 10:25
 * @Description: 测试web
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private RedisProperties redisProperties;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return  "hello Spring boot";
    }

    @RequestMapping(value = "/read",method = RequestMethod.GET)
    public String read(){

        log.info("info 日志");
        log.error("error 日志");
        log.warn("warn 日志");

        return redisProperties.read();
    }

}
