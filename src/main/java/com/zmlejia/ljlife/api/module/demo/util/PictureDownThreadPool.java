package com.zmlejia.ljlife.api.module.demo.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PictureDownThreadPool {

	private ExecutorService pool = Executors.newCachedThreadPool();
	private int num;
	private String threadName;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
}
