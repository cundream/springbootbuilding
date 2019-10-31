package com.github.cundream.springbootbuilding.vo.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : Lison
 * @Date: 2019/10/28 15:47
 * @Description: 登录请求参数
 */
@Data
public class LoginRequest {
    /**
     * 用户名或邮箱或手机号
     */
    @NotBlank(message = "用户名不能为空")
    private String usernameOrEmailOrPhone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;

    public LoginRequest(@NotBlank(message = "用户名不能为空") String usernameOrEmailOrPhone, @NotBlank(message = "密码不能为空") String password, Boolean rememberMe) {
        this.usernameOrEmailOrPhone = usernameOrEmailOrPhone;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public LoginRequest() {
    }
}
