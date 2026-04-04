package com.weng.system.common.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 */
@Configuration
public class AsyncExecutorPoolConfigure {

	@Value("${async.executor.core.pool.size}")
	private int corePoolSize;
	
	@Bean("AsyncThreadPool")
	public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
		executor.setQueueCapacity(500);
		executor.setKeepAliveSeconds(30);
		executor.setThreadNamePrefix("TRS-AsyncThread");
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setAwaitTerminationSeconds(60);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}

}
