package com.github.cundream.springbootbuilding.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

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
@Table(name = "user")
public class User implements Serializable {

    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String userName;
    private String passWord;
    private String realName;
}
