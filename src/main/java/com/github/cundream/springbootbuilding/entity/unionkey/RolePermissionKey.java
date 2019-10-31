package com.github.cundream.springbootbuilding.entity.unionkey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author : Lison
 * @Date: 2019/10/28 14:44
 * @Description: 角色-权限联合主键
 */
@Data
@Embeddable
public class RolePermissionKey implements Serializable {

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @Column(name = "permission_id")
    private Long permissionId;

}
