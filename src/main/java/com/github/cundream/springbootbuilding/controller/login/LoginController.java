package com.github.cundream.springbootbuilding.controller.login;

import com.github.cundream.springbootbuilding.common.controller.BaseController;
import com.github.cundream.springbootbuilding.common.controller.ResponseWrapper;
import com.github.cundream.springbootbuilding.common.enums.ErrorCodeEnum;
import com.github.cundream.springbootbuilding.common.exception.SecurityException;
import com.github.cundream.springbootbuilding.utils.JwtUtil;
import com.github.cundream.springbootbuilding.vo.login.LoginRequest;
import com.github.cundream.springbootbuilding.vo.payload.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author : Lison
 * @Date: 2019/10/28 15:53
 * @Description:
 */
@Slf4j
//@RestController
@RequestMapping("/api/auth")
public class LoginController  extends BaseController {



    @Autowired
    private JwtUtil jwtUtil;

  /*  *//**
     * 登录
     *//*
    @PostMapping("/login")
    public ResponseWrapper login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication,loginRequest.getRememberMe());
        return handleResult(new JwtResponse(jwt));
    }*/




    @PostMapping("/logout")
    public ResponseWrapper logout(HttpServletRequest request) {
        try {
            // 设置JWT过期
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            throw new SecurityException(ErrorCodeEnum.UNAUTHORIZED);
        }
        return handleResult(ErrorCodeEnum.LOGOUT);
    }
}
