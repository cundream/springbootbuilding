package com.github.cundream.springbootbuilding.mapper;

import com.github.cundream.springbootbuilding.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Lison
 * @Date: 2019/10/16 14:03
 * @Description:
 */
@Mapper
public interface UserMapper {
    List<User> listUsers();
}
