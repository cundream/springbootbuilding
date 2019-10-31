package com.github.cundream.springbootbuilding.config.login;

import com.github.cundream.springbootbuilding.common.enums.ErrorCodeEnum;
import com.github.cundream.springbootbuilding.utils.JwtUtil;
import com.github.cundream.springbootbuilding.utils.ResponseUtil;
import com.github.cundream.springbootbuilding.vo.UserPrincipal;
import com.github.cundream.springbootbuilding.vo.payload.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Lison
 * @Date: 2019/10/30 15:04
 * @Description: 登录成功显示的信息
 */
@Component
@Slf4j
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Assert.notNull(authentication, "No authentication data provided");

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        //校验成功
        ResponseUtil.renderJson(httpServletResponse, ErrorCodeEnum.SUCCESS, new JwtResponse(jwtUtil.createJWT(user)));
    }
}
