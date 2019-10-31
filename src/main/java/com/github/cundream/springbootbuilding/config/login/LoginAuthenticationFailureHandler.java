package com.github.cundream.springbootbuilding.config.login;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.cundream.springbootbuilding.common.controller.ResponseWrapperMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Lison
 * @Date: 2019/10/30 15:21
 * @Description: 验证失败返回的信息
 */
@Component
@Slf4j
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter()
                .write(JSONUtil.toJsonStr(new JSONObject(ResponseWrapperMapper.wrap(e), false)));

    }
}
