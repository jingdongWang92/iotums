package com.jcnetwork.iotums;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzTest implements Job{
	public void execute() {
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("++++++++++++++++++++++++++++");
	}
}
