package com.github.cundream.springbootbuilding.entity;

import com.github.cundream.springbootbuilding.entity.unionkey.UserRoleKey;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



/**
 * @author : Lison
 * @Date: 2019/10/16 13:58
 * @Description: 用户角色关联
 */
@Data
@Entity
@Table(name = "sec_user_role")
public class UserRole {
    /**
     * 主键
     */
    @EmbeddedId
    private UserRoleKey id;
}
