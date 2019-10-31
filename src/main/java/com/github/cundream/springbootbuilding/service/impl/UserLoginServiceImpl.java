package com.github.cundream.springbootbuilding.service.impl;

import com.github.cundream.springbootbuilding.entity.Permission;
import com.github.cundream.springbootbuilding.entity.Role;
import com.github.cundream.springbootbuilding.entity.User;
import com.github.cundream.springbootbuilding.repository.PermissionRepository;
import com.github.cundream.springbootbuilding.repository.RoleRepository;
import com.github.cundream.springbootbuilding.repository.UserRepository;
import com.github.cundream.springbootbuilding.service.UserLoginService;
import com.github.cundream.springbootbuilding.vo.UserPrincipal;
import com.github.cundream.springbootbuilding.vo.login.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
     * @author : Lison
     * @Date: 2019/10/28 16:24
     * @Description: 自定义UserDetails查询
     */
    @Service
    public class UserLoginServiceImpl implements UserLoginService {

        @Autowired
        private PermissionRepository permissionRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;



        @Override
        public UserPrincipal loadUserByUsername(LoginRequest loginRequest) throws UsernameNotFoundException {
            String s = loginRequest.getUsernameOrEmailOrPhone();
            User user = userRepository.findByUsernameOrEmailOrPhone(s, s, s)
                    .orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + s));
            List<Role> roles = roleRepository.selectByUserId(user.getId());
            List<Long> roleIds = roles.stream()
                    .map(Role::getId)
                    .collect(Collectors.toList());
            List<Permission> permissions = permissionRepository.selectByRoleIdList(roleIds);
            return UserPrincipal.create(user, roles, permissions,loginRequest.getRememberMe());
        }

        @Override
        public UserPrincipal loadUserByUsername(String s) throws UsernameNotFoundException {
            User user = userRepository.findByUsernameOrEmailOrPhone(s, s, s)
                    .orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + s));
            List<Role> roles = roleRepository.selectByUserId(user.getId());
            List<Long> roleIds = roles.stream()
                    .map(Role::getId)
                    .collect(Collectors.toList());
            List<Permission> permissions = permissionRepository.selectByRoleIdList(roleIds);
            return UserPrincipal.create(user, roles, permissions,false);
        }


    }
