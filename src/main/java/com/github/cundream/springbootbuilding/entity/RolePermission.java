package com.github.cundream.springbootbuilding.entity;

import com.github.cundream.springbootbuilding.entity.unionkey.RolePermissionKey;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author : Lison
 * @Date: 2019/10/28 14:44
 * @Description: 角色-权限
 */
@Data
@Entity
@Table(name = "sec_role_permission")
public class RolePermission {
    /**
     * 主键
     */
    @EmbeddedId
    private RolePermissionKey id;
}
