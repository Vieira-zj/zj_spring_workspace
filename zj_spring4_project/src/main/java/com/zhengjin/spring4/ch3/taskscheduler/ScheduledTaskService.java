package com.zhengjin.spring4.ch3.taskscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

	private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		System.out.println("execute every 5 seconds: " + dataFormat.format(new Date()));
	}

	@Scheduled(cron = "0 28 11 ? * *")
	public void fixTimeExecution() {
		System.out.println("execute at fix time: " + dataFormat.format(new Date()));
	}

}
