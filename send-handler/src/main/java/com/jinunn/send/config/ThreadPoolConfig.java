package com.jinunn.send.config;

import cn.hutool.core.thread.ExecutorBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author : JinDun
 * @date : 2022/6/21 17:50
 */
public class ThreadPoolConfig {

    /**
     * 阻塞队列满了，也不丢弃任务  CallerRunsPolicy 策略
     *
     * @param coreSize  核心线程
     * @param maxSize   最大线程
     * @param queueSize 队列大小
     * @return 线程池
     */
    public static ExecutorService getThreadPool(Integer coreSize, Integer maxSize, Integer queueSize) {
        return ExecutorBuilder.create()
                .setCorePoolSize(coreSize)
                .setMaxPoolSize(maxSize)
                .setKeepAliveTime(60, TimeUnit.SECONDS)
                .setWorkQueue(new LinkedBlockingQueue<>(queueSize))
                .setHandler(new ThreadPoolExecutor.CallerRunsPolicy())
                .build();
    }
}
