package com.niule.znxj.core.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoming
 * @create 2017-06-28 17:04
 **/
public class ThreadPoolExecutorFactory {

    private static ThreadPoolExecutorFactory threadPoolExecutorFactory;

    private static int corePoolSize = 10;//3

    private static int maximumPoolSize = 20;//10

    private static int keepAliveTime = 60;//20

    private static int blockQueenSize = 10;//10

    private static ThreadPoolExecutor threadPoolExecutor;

    private static boolean isInit = false;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.SECONDS, new ArrayBlockingQueue(blockQueenSize),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPoolExecutorFactory = new ThreadPoolExecutorFactory();

    }

    public static void initConfig(int corePoolSize, int maximumPoolSize, int keepAliveTime, int blockQueenSize) {
        ThreadPoolExecutorFactory.corePoolSize = corePoolSize;
        ThreadPoolExecutorFactory.maximumPoolSize = maximumPoolSize;
        ThreadPoolExecutorFactory.keepAliveTime = keepAliveTime;
        ThreadPoolExecutorFactory.blockQueenSize = blockQueenSize;
        isInit = true;
    }

    public static ThreadPoolExecutorFactory getInstance() {
        try {
            if (!isInit) {
                throw new RuntimeException("线程池没有初始化参数");
            }
        } finally {
            return threadPoolExecutorFactory;
        }
    }

    public void run(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }


    private ThreadPoolExecutorFactory() {
    }

}
