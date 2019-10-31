package com.github.cundream.springbootbuilding.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author : Lison
 * @Date: 2019/10/16 13:58
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sec_user")
@Entity
public class User implements Serializable {

    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 性别，男-1，女-2
     */
    private Integer sex;

    /**
     * 状态，启用-1，禁用-0
     */
    private Integer status;

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
