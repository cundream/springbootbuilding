package com.github.cundream.springbootbuilding.mapper;

import com.github.cundream.springbootbuilding.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author: Lison
 * @Date: 2019/10/16 14:03
 * @Description:
 * 注意：这里的Mapper是tk.mybatis.mapper.common.Mapper包下的
 */

@Component
public interface UserMapper extends Mapper<User> , MySqlMapper<User> {
    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Select("select * from sec_user")
    List<User> listUsers();

    /**
     * 根据id查询用户
     *
     * @param id 主键id
     * @return 当前id的用户，不存在则是 {@code null}
     */
    @Select("SELECT * FROM sec_user WHERE id = #{id}")
    User selectUserById(@Param("id") Long id);



    /**
     * 保存用户
     *
     * @param user 用户
     * @return 成功 - {@code 1} 失败 - {@code 0}
     */
    int addUserInfo(User user);
}
