package com.github.cundream.springbootbuilding.service;

import com.github.cundream.springbootbuilding.vo.UserPrincipal;
import com.github.cundream.springbootbuilding.vo.login.LoginRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author: Lison
 * @Date: 2019/10/30 18:09
 * @Description:
 */
public interface UserLoginService {


    UserPrincipal loadUserByUsername(LoginRequest req);

    UserPrincipal loadUserByUsername(String req);





}
