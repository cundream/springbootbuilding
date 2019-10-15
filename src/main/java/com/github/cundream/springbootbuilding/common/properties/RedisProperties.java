package com.github.cundream.springbootbuilding.common.properties;

import com.github.cundream.springbootbuilding.config.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Lison
 * @Date: 2019/10/15 11:33
 * @Description: 读取自定义配置文件
 */
@EnableConfigurationProperties({Redis.class})
@Component
public class RedisProperties {

    @Autowired
    private Redis redis;

    public String read(){
        return redis.getIp();
    }
}
