package com.github.cundream.springbootbuilding.config.login.username;

import com.github.cundream.springbootbuilding.common.enums.ErrorCodeEnum;
import com.github.cundream.springbootbuilding.common.exception.BusinessException;
import com.github.cundream.springbootbuilding.config.security.LoginAuthenticationToken;
import com.github.cundream.springbootbuilding.service.UserLoginService;
import com.github.cundream.springbootbuilding.vo.UserPrincipal;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * @author : Lison
 * @Date: 2019/10/30 17:15
 * @Description:
 */
@Setter
@Slf4j
public class UsernameLoginAuthenticationProvider implements AuthenticationProvider {

    private UserLoginService userLoginService;

    private BCryptPasswordEncoder encoder;




    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Assert.notNull(authentication, "No authentication data provided");

        log.debug("Enter UsernameLoginAuthenticationProvider...");
        UserPrincipal req = (UserPrincipal) authentication.getPrincipal();

        UserPrincipal user = userLoginService.loadUserByUsername(req.getUsername());
        //校验密码
        if(!encoder.matches(req.getPassword(),user.getPassword())){
            throw new BadCredentialsException(ErrorCodeEnum.USERNAME_PASSWORD_ERROR.msg());
        }


        return new LoginAuthenticationToken(user,user.getAuthorities());
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return (LoginAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
