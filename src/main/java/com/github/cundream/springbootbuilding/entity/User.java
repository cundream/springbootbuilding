package com.github.cundream.springbootbuilding.entity;

import lombok.Data;

/**
 * @author : Lison
 * @Date: 2019/10/16 13:58
 * @Description:
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;
}
