package com.github.cundream.springbootbuilding.vo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author : Lison
 * @Date: 2019/10/28 17:05
 * @Description: JWT 响应返回
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }
}
