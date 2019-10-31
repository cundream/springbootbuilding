package com.github.cundream.springbootbuilding.config.login.username;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.cundream.springbootbuilding.config.security.LoginAuthenticationToken;
import com.github.cundream.springbootbuilding.vo.UserPrincipal;
import com.github.cundream.springbootbuilding.vo.login.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author : Lison
 * @Date: 2019/10/30 11:56
 * @Description:
 */
@Slf4j
public class UsernameLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    public UsernameLoginAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        LoginAuthenticationToken token = new LoginAuthenticationToken(getRequest(request));
        return this.getAuthenticationManager().authenticate(token);
    }


    private UserPrincipal getRequest(HttpServletRequest request) throws IOException{
        String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        String username = null, password = null;
        Boolean rememberMe = false;
        if(StringUtils.hasText(body)) {
            JSONObject jsonObj = JSON.parseObject(body);
            username = jsonObj.getString("username");
            password = jsonObj.getString("password");
            rememberMe = jsonObj.getBoolean("rememberMe");
        }
        if (username == null){
            username = "";
            throw new AuthenticationServiceException("账号或密码不能为空");
        }
        if (password == null){
            password = "";
        }

        if (rememberMe == null){
            rememberMe = false;
        }
        username = username.trim();
        UserPrincipal user = new UserPrincipal();
        user.setUsername(username);
        user.setPassword(password);
        user.setRememberMe(rememberMe);
        return user;
    }

}
