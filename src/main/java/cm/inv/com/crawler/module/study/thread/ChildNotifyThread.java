package cm.inv.com.crawler.module.study.thread;

/**
 * Created by zhangtao107@126.com on 2016/11/2.
 */

import org.slf4j.Logger;

/**
 * 用来在启动后，等待唤醒
 * @author yinwenjie
 */
public class ChildNotifyThread implements Runnable {

    /**
     * 日志
     */
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ChildNotifyThread.class);

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        long id = currentThread.getId();
        ChildNotifyThread.LOGGER.info("线程" + id + "启动成功，准备进入等待状态");
        synchronized (ParentNotifyThread.WAIT_CHILEOBJECT) {
            try {
                ParentNotifyThread.WAIT_CHILEOBJECT.wait();
            } catch (InterruptedException e) {
                ChildNotifyThread.LOGGER.error(e.getMessage() , e);
            }
        }

        //执行到这里，说明线程被唤醒了
        ChildNotifyThread.LOGGER.info("线程" + id + "被唤醒！");
    }
}
