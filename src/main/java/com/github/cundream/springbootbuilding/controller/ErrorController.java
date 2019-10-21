package com.github.cundream.springbootbuilding.controller;

import com.github.cundream.springbootbuilding.common.controller.BaseController;
import com.github.cundream.springbootbuilding.common.controller.ResponseWrapper;
import com.github.cundream.springbootbuilding.common.controller.ResponseWrapperMapper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Lison
 * @Date: 2019/10/17 13:56
 * @Description:
 */
@RestController
@RequestMapping("/error")
@Api(value = "ErrorController", tags = "error", description = "异常处理测试接口")
public class ErrorController extends BaseController {

    @RequestMapping(value = "/error")
    @ResponseBody
    public ResponseWrapper<String> error(HttpServletResponse resp, HttpServletRequest req) {
        // 错误处理逻辑
        return ResponseWrapperMapper.wrap(ResponseWrapper.NOT_FOUND_CODE);
    }

    @RequestMapping(value = "/errors")
    @ResponseBody
    public ResponseWrapper<String> errors(HttpServletResponse resp, HttpServletRequest req) {
        // 错误处理逻辑
        int i = 1/0;

        return ResponseWrapperMapper.wrap(ResponseWrapper.NOT_FOUND_CODE);
    }

}
