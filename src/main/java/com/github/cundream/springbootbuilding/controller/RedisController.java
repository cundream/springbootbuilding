package com.github.cundream.springbootbuilding.controller;

import com.github.cundream.springbootbuilding.entity.User;
import com.github.cundream.springbootbuilding.service.UserService;
import com.github.cundream.springbootbuilding.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Lison
 * @Date: 2019/10/16 16:21
 * @Description:
 */
@RestController
@RequestMapping("/redis")
@Api(value = "RedisController", tags = "redis", description = "Redis测试接口")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "getRedis", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET",value = "获取用户列表")
    public String getRedis(){
        return redisUtil.get("redis_key");
    }


    @RequestMapping(value = "setRedis", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET",value = "获取用户列表")
    public String setRedis(){
        redisUtil.set("redis_key","666666666");
        return "true";
    }
}
