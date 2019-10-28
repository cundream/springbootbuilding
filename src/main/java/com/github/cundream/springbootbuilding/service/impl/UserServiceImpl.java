package com.github.cundream.springbootbuilding.service.impl;

import com.github.cundream.springbootbuilding.entity.User;
import com.github.cundream.springbootbuilding.mapper.UserMapper;
import com.github.cundream.springbootbuilding.service.UserService;
import com.github.pagehelper.PageInfo;
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
    private UserMapper userMapper;

    @Override
    public List<User> getUserById() {
        return userMapper.listUsers();
    }

    @Override
    public void addUserInfo() {
        User user = new User();
        user.setId(3L);
        user.setPassWord("123456");
        user.setRealName("测试名字");
        user.setUserName("用户名");
        userMapper.addUserInfo(user);

    }

}
