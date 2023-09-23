package com.github.cundream.springbootbuilding.controller.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @className: com.github.cundream.springbootbuilding.controller.thread-> CompletableFutureComposeController
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-08-30 18:18
 */

@RestController

public class CompletableFutureComposeController {
    @Resource
    private ThreadPoolTaskExecutor customAsyncTaskExecutor;

    @Resource
    private ThreadPoolTaskExecutor asyncThreadPoolExecutor;

    @RequestMapping(value = "/thenRun")
    public void thenRun() {
        CompletableFuture.runAsync(() -> {
            System.out.println("thread name:" + Thread.currentThread().getName() + " first step...");
        }, customAsyncTaskExecutor).thenRun(() -> {
            System.out.println("thread name:" + Thread.currentThread().getName() + " second step...");
        }).thenRunAsync(() -> {
            System.out.println("thread name:" + Thread.currentThread().getName() + " third step...");
        });
    }

    @RequestMapping(value = "/thenCombine")
    public void thenCombine() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行future1开始...");
            return "Hello";
        }, asyncThreadPoolExecutor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行future2开始...");
            return "World";
        }, asyncThreadPoolExecutor);

        future1.thenCombine(future2, (result1, result2) -> {
            String result = result1 + " " + result2;
            System.out.println("获取到future1、future2聚合结果：" + result);
            return result;
        }).thenAccept(result -> System.out.println(result));
    }
}
