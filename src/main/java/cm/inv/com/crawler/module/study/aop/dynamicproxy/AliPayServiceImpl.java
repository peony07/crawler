package cm.inv.com.crawler.module.study.aop.dynamicproxy;


import org.apache.log4j.Logger;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class AliPayServiceImpl implements Payservice {

    private static Logger logger = Logger.getLogger(AliPayServiceImpl.class);

    @Override
    public void pay() {
        logger.info(" do ali pay ");
    }

    @Override
    public void serverNotify() {
        logger.info(" do ali pay serverNotify ");
    }
}
