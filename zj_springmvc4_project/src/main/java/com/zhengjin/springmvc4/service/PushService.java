package com.zhengjin.springmvc4.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {

	private DeferredResult<String> deferredResult;

	public DeferredResult<String> getAsyncUpdate() {
		this.deferredResult = new DeferredResult<String>();
		return this.deferredResult;
	}

	// 定时更新DeferredResult
	@Scheduled(fixedDelay = 3000)
	public void refresh() {
		if (this.deferredResult != null) {
			this.deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
		}
	}

}
