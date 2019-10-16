package com.github.cundream.springbootbuilding.service.impl;

import com.github.cundream.springbootbuilding.entity.User;
import com.github.cundream.springbootbuilding.mapper.UserMapper;
import com.github.cundream.springbootbuilding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Lison
 * @Date: 2019/10/16 14:00
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserById() {
        return userMapper.listUsers();
    }

}
