package com.github.cundream.springbootbuilding.service.impl;

import com.github.cundream.springbootbuilding.entity.User;
import com.github.cundream.springbootbuilding.mapper.UserMapper;
import com.github.cundream.springbootbuilding.repository.UserRepository;
import com.github.cundream.springbootbuilding.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private UserRepository userRepository;



    @Override
    public List<User> getUserList() {
        userMapper.listUsers();
        return userRepository.findAll();

    }

    @Override
    public void addUserInfo() {
        User user = new User();
        user.setId(3L);
        user.setPassword("123456");
        user.setUsername("测试名字");
        user.setNickname("用户名");
        userMapper.addUserInfo(user);

    }

    @Override
    @Cacheable(cacheNames = "users", key = "#userId")
    public User getUserinfo(Long userId) {
        return  userMapper.selectUserById(userId);
    }

    @CacheEvict(value = "users",key = "#id")
    public void evictUser(Long id) {
        System.out.println("evict user:" + id);
    }

}
