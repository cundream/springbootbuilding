package com.github.cundream.springbootbuilding.controller;

import com.github.cundream.springbootbuilding.entity.User;
import com.github.cundream.springbootbuilding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Lison
 * @Date: 2019/10/16 13:58
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getUserList")
    public List<User> GetUser(){
        return userService.getUserById();
    }
}
