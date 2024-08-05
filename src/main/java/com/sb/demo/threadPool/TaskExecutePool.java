package com.sb.demo.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TaskExecutePool {

    @Resource
    private TaskThreadPoolConfig taskThreadPoolConfig;

    @Bean
    public Executor taskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(taskThreadPoolConfig.getCorePoolSize());
        executor.setMaxPoolSize(taskThreadPoolConfig.getMaxPoolSize());
        executor.setKeepAliveSeconds(taskThreadPoolConfig.getKeepAliveSeconds());
        executor.setQueueCapacity(taskThreadPoolConfig.getQueueCapacity());
        executor.setThreadNamePrefix("taskAsyncPool--");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setAwaitTerminationSeconds(taskThreadPoolConfig.getAwaitTerminationSeconds());
        executor.setWaitForTasksToCompleteOnShutdown(taskThreadPoolConfig.getWaitForTasksToCompleteOnShutdown());
        executor.initialize();
        return executor;
    }
}
