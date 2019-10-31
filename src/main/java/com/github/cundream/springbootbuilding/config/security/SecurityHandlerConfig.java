package com.github.cundream.springbootbuilding.config.security;

import com.github.cundream.springbootbuilding.common.enums.ErrorCodeEnum;
import com.github.cundream.springbootbuilding.utils.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author : Lison
 * @Date: 2019/10/28 17:05
 * @Description: 结果处理配置
 */
@Configuration
public class SecurityHandlerConfig {
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> ResponseUtil.renderJson(response, ErrorCodeEnum.ACCESS_DENIED, null);
    }
}
