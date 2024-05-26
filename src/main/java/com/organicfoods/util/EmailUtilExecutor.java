package com.organicfoods.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EmailUtilExecutor {
	private static final ExecutorService emailExecutor = Executors.newFixedThreadPool(10);
	
	public static ExecutorService getEmailExecutor() {
        return emailExecutor;
    }
	
	public static void shutdown() {
        emailExecutor.shutdown();
        try {
            if (!emailExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                emailExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            emailExecutor.shutdownNow();
        }
    }
}
