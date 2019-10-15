package com.github.cundream.springbootbuilding.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Lison
 * @Date: 2019/10/15 10:25
 * @Description: 测试web
 */
@RestController
public class TestController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return  "hello Spring boot";
    }
}
