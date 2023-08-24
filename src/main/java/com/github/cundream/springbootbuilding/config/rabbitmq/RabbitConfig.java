package com.github.cundream.springbootbuilding.config.rabbitmq;

import com.github.cundream.springbootbuilding.rabbitmq.consts.RabbitConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: com.github.cundream.springbootbuilding.common.rabbitmq-> RabbitConfig
 * @description:
 * @author: 李村
 * @createDate:
 */
@Configuration
@Slf4j
public class RabbitConfig {


    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true); // 发送消息回调,必须要设置
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    /**
     * 直接模式队列1
     */
    @Bean
    public Queue directOneQueue() {

        return new Queue("cundream");
    }
    /**
     * 延时队列交换机
     *
     * @return
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitConst.DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 延时队列
     *
     * @return
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(RabbitConst.DELAY_QUEUE, true);
    }

    /**
     * 给延时队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding delayBinding(Queue delayQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitConst.DELAY_KEY).noargs();
    }




    @Bean
    public Queue topicQueue() {
        return new Queue(RabbitConst.MSG_TOPIC_QUEUE,true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RabbitConst.TOPIC_WEB_SOCKET_EXCHANGE,true,false);
    }


    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(topicQueue()).to(exchange()).with(RabbitConst.MSG_TOPIC_KEY);
    }

}
