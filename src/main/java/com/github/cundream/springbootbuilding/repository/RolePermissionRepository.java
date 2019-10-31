package com.github.cundream.springbootbuilding.repository;


import com.github.cundream.springbootbuilding.entity.RolePermission;
import com.github.cundream.springbootbuilding.entity.unionkey.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author: Lison
 * @Date: 2019/10/28 14:03
 * @Description: 角色-权限 Repository
 *
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionKey>, JpaSpecificationExecutor<RolePermission> {
}
