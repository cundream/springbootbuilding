package com.github.cundream.springbootbuilding.config.security;

import com.github.cundream.springbootbuilding.vo.UserPrincipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author : Lison
 * @Date: 2019/10/30 17:20
 * @Description:
 */
public class LoginAuthenticationToken extends AbstractAuthenticationToken {

    private UserPrincipal userPrincipal;

    public LoginAuthenticationToken(UserPrincipal userPrincipal) {
        super(null);
        this.userPrincipal = userPrincipal;
        setAuthenticated(false);
    }

    public LoginAuthenticationToken(UserPrincipal userPrincipal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userPrincipal = userPrincipal;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return userPrincipal.getUsername();
    }

    @Override
    public Object getPrincipal() {
        return userPrincipal;
    }
}
