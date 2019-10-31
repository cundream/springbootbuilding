package com.github.cundream.springbootbuilding.controller;

import com.github.cundream.springbootbuilding.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Lison
 * @Date: 2019/10/21 11:14
 * @Description:
 */
@Controller
@RequestMapping("/thymeleaf")
@Api(value = "ThymeleafController", tags = "thymeleaf", description = "Thymeleaf测试接口")
public class ThymeleafController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public String userList(Model model){

        model.addAttribute("users", userService.getUserList());
        model.addAttribute("thymeleafName", "thymeleafName ----6666666");

        return "userList";
    }


}
