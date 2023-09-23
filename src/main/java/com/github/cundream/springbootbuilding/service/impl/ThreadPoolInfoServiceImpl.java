package com.github.cundream.springbootbuilding.service.impl;

import com.github.cundream.springbootbuilding.service.IThreadPoolInfoService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @className: com.github.cundream.springbootbuilding.service.impl-> ThreadPoolInfoServiceImpl
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-08-31 09:29
 */
@Service
public class ThreadPoolInfoServiceImpl implements IThreadPoolInfoService {

    @Resource
    private ThreadPoolTaskExecutor customAsyncTaskExecutor;
    @Override
    public String getThreadTest() {


        return null;
    }

}
