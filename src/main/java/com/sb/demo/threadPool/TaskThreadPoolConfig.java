package com.sb.demo.threadPool;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.thread.pool")
public class TaskThreadPoolConfig {

    /**
     * 核心线程大小
     */
    private int corePoolSize = 60;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 120;

    /**
     * 线程池维护线程所需的空闲时间
     */
    private int keepAliveSeconds = 360;

    /**
     * 队列最大长度，一般需要设置值>=notifyScheduledMainExecutor.maxNum
     */
    private int queueCapacity = 60;

    private int awaitTerminationSeconds = 60;

    private Boolean waitForTasksToCompleteOnShutdown = true;

}
