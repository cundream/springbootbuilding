package com.github.cundream.springbootbuilding.controller;

import com.github.cundream.springbootbuilding.entity.User;
import com.github.cundream.springbootbuilding.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Lison
 * @Date: 2019/10/16 13:58
 * @Description:
 */

@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = "user", description = "User相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET",value = "获取用户列表")
    public List<User> GetUser(){
        return userService.getUserById();
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 10001, message = "secret_key与token不符合"),
            @ApiResponse(code = 10002, message = "获取用户信息错误", response = Exception.class)
    })
    @PostMapping("/getUserinfo")
    public String getUsesrInfo(@ApiParam(name = "secret_key", value = "秘钥", required = true) @RequestParam String secret_key,
                           @ApiParam(name = "token", value = "token", required = true) @RequestParam String token,
                           @ApiParam(name = "type", value = "用户类型", required = true) @RequestParam String type){
        return "{'type': " + type + ", 'url': 'rtmp://localhost/user', 'urlHD': 'rtmp://localhost/hd/user'}";
    }



    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "header", name = "id", value = "id标识", required = true),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "query", name = "userName", value = "登陆名", required = true),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "path", name = "passWord", value = "密码", required = true),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "body", name = "realName", value = "名字", required = true)
    })
    @PutMapping("/updateUserInfo")
    public String updateUser(@RequestHeader String id, @RequestParam String userName, @PathVariable String passWord, @RequestBody String realName){
        return "{'id': " + id + ", 'userName':" + userName + ", 'passWord':" + passWord + ", 'realName':" + realName +"}";
    }
    @PutMapping("/addUserInfo")
    public String addUserInfo(){
        userService.addUserInfo();
        return "";
    }






}
