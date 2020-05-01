package com.zhengjin.spring4.ch3.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

	// 自动注入ThreadPoolTaskExecutor作为TaskExecutor
	@Async
	public void executeAsyncTask(Integer i) {
		System.out.println(String.format("[%s] execute async task: %d", Thread.currentThread().getName(), i));
	}

	@Async
	public void executeAsyncTaskPlus(Integer i) {
		System.out
				.println(String.format("[%s] execute async task plus: %d", Thread.currentThread().getName(), (i + 1)));
	}

}
