package com.github.cundream.springbootbuilding.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @author : Lison
 * @Date: 2019/10/28 14:54
 * @Description: 角色
 */
@Data
@Entity
@Table(name = "sec_role")
public class Role {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Long updateTime;
}
