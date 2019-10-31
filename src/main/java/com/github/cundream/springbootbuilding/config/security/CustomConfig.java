package com.github.cundream.springbootbuilding.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : Lison
 * @Date: 2019/10/28 16:16
 * @Description: 自定义配置
 */
@ConfigurationProperties(prefix = "custom.config")
@Data
public class CustomConfig {
    /**
     * 不需要拦截的地址
     */
    private IgnoreConfig ignores;
}
