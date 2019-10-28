package com.github.cundream.springbootbuilding.service;

import com.github.cundream.springbootbuilding.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: Lison
 * @Date: 2019/10/16 13:59
 * @Description:
 */
public interface UserService {
    List<User> getUserById();

    void addUserInfo();
}
