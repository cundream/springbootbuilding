package com.github.cundream.springbootbuilding.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author : Lison
 * @Date: 2019/10/15 11:26
 * @Description:
 */

@Configuration
@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix = "redis")
@Data
public class Redis {

    private  String ip;
    private  Integer port;
    private  String password;
    private  Integer maxActive;
    private  Integer maxIdle;
    private  Long maxWait;
    private  Boolean testOnBorrow;
    private  Boolean testOnReturn;
    private  Integer expire;
    private  Integer maxTotal;
    private  Integer timeBetweenEvictionRunsMillis;
    private  Integer minEvictableIdleTimeMillis;
    //省略getter,setter
}
