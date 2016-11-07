package cm.inv.com.crawler.module.study.aop.dynamicproxy;

import org.apache.log4j.Logger;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class WxPayServiceImpl implements Payservice {

    private static Logger logger = Logger.getLogger(WxPayServiceImpl.class);

    @Override
    public void pay() {
        logger.info(" do wx pay ");
    }

    @Override
    public void serverNotify() {
        logger.info(" do wx serverNotify ");
    }
}
