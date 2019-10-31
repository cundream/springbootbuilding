package com.github.cundream.springbootbuilding.repository;

import com.github.cundream.springbootbuilding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



/**
 * @author: Lison
 * @Date: 2019/10/28 14:03
 * @Description:用户 Repository
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param email    邮箱
     * @param phone    手机号
     * @return 用户信息
     */
    Optional<User> findByUsernameOrEmailOrPhone(String username, String email, String phone);

    /**
     * 根据用户名列表查询用户列表
     *
     * @param usernameList 用户名列表
     * @return 用户列表
     */
    List<User> findByUsernameIn(List<String> usernameList);
}
