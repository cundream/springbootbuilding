package com.github.cundream.springbootbuilding.repository;

import com.github.cundream.springbootbuilding.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * @author: Lison
 * @Date: 2019/10/28 14:03
 * @Description:角色 Repository
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @Query(value = "SELECT sec_role.* FROM sec_role,sec_user,sec_user_role WHERE sec_user.id = sec_user_role.user_id AND sec_role.id = sec_user_role.role_id AND sec_user.id = :userId", nativeQuery = true)
    List<Role> selectByUserId(@Param("userId") Long userId);
}
