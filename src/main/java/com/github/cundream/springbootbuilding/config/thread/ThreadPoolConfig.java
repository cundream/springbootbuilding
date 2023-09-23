package com.github.cundream.springbootbuilding.config.thread;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * @className: com.github.cundream.springbootbuilding.config.thread-> ThreadPoolConfig
 * @description: 线程池配置
 * @author: Lison 200900681
 * @createDate: 2023-08-30 17:59
 */

@Configuration
public class ThreadPoolConfig {

    /**
     * 常用线程池：用以执行触发频率高、耗时短的任务
     */
    @Bean("asyncThreadPoolExecutor")
    public ThreadPoolTaskExecutor commonTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程：核心线程一直存活
        executor.setCorePoolSize(30);
        // 队列容量：任务将队列塞满之后，扩展核心线程，线程总数最多不超过最大线程数
        executor.setQueueCapacity(60);
        // 最大线程
        executor.setMaxPoolSize(100);
        // 闲置线程存活时长
        executor.setKeepAliveSeconds(60);
        // 拒绝策略：任务超出线程池容量后，新任务交还主线程处理
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程名前缀
        executor.setThreadNamePrefix("common-task-worker-");
        // 初始化线程池
        executor.initialize();
        return executor;
    }

    /**
     * 高负载线程池：用以执行出发频率低、耗时长任务
     */
    @Bean("customAsyncTaskExecutor")
    public ThreadPoolTaskExecutor expensiveTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程：核心线程一直存活
        executor.setCorePoolSize(20);
        // 队列容量：任务将队列塞满之后，扩展核心线程，线程总数最多不超过最大线程数
        executor.setQueueCapacity(0);
        // 最大线程
        executor.setMaxPoolSize(100);
        // 闲置线程存活时长
        executor.setKeepAliveSeconds(60);
        // 拒绝策略：任务超出线程池容量后，新任务交还主线程处理
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程名前缀
        executor.setThreadNamePrefix("expensive-task-worker-");
        // 初始化线程池
        executor.initialize();
        return executor;
    }

}
