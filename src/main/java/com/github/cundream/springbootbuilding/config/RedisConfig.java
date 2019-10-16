package com.github.cundream.springbootbuilding.config;

import com.github.cundream.springbootbuilding.utils.RedisConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author : Lison
 * @Date: 2019/10/16 15:53
 * @Description:
 */
@Configuration
public class RedisConfig {

    /**
     * @param redisConnectionFactory
     * @return 自定义redisTemplate，自带的bean没有序列化器
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置key的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置值的序列化器
        redisTemplate.setValueSerializer(new RedisConverter());
        return redisTemplate;
    }
}
