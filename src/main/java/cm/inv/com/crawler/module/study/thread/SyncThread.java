package cm.inv.com.crawler.module.study.thread;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;

/**
 * Created by zhangtao107@126.com on 2016/11/2.
 */
public class SyncThread implements Runnable {

    /**
     * 日志
     */
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SyncThread.class);

    private Integer value;

    private static Integer NOWVALUE;

    static {
        BasicConfigurator.configure();
    }

    public SyncThread(int value) {
        this.value = value;
    }

    /**
     * 对这个类的实例化对象进行检查
     */
    private  void doOtherthing() {
        synchronized(SyncThread.class) {
            NOWVALUE = this.value;
            LOGGER.info("当前NOWVALUE的值：" + NOWVALUE);
        }
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        Long id = currentThread.getId();
        this.doOtherthing();
    }

    public static void main(String[] args) throws Exception {
        Thread syncThread1 = new Thread(new SyncThread(10));
        Thread syncThread2 = new Thread(new SyncThread(100));

        syncThread1.start();
        syncThread2.start();
    }
}
