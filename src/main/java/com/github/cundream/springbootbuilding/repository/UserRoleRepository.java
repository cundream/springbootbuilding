package com.github.cundream.springbootbuilding.repository;

import com.github.cundream.springbootbuilding.entity.UserRole;
import com.github.cundream.springbootbuilding.entity.unionkey.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author: Lison
 * @Date: 2019/10/28 14:03
 * @Description: 用户角色 Repository
 *
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey>, JpaSpecificationExecutor<UserRole> {

}
