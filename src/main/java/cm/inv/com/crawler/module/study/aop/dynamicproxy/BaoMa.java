package cm.inv.com.crawler.module.study.aop.dynamicproxy;

import org.apache.log4j.Logger;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class BaoMa implements Car {

    private Logger logger = Logger.getLogger(BaoMa.class);

    @Override
    public void makeCar() {
        logger.info(" make a baoma ");
    }
}
