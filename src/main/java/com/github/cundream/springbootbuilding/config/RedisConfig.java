package com.github.cundream.springbootbuilding.config;

import com.github.cundream.springbootbuilding.listener.redis.RedisKeyChangeListener;
import com.github.cundream.springbootbuilding.utils.RedisConverter;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
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


    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    // 创建基本的key监听器
    /*  */
    @Bean
    public RedisKeyChangeListener redisKeyChangeListener(RedisConnectionFactory connectionFactory) throws Exception {
        RedisKeyChangeListener listener = new RedisKeyChangeListener(redisMessageListenerContainer(connectionFactory),"");
        return listener;
    }


}
