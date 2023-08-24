package com.github.cundream.springbootbuilding.config.websocket;

import java.security.Principal;

/**
 * @className: com.github.cundream.springbootbuilding.config.websocket-> UserPrincipal
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-08-09 16:51
 */
public class UserPrincipal implements Principal {

    private final String name;

    public UserPrincipal(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}
