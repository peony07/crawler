package cm.inv.com.crawler.module.log;

import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.common.test.SpringTestBase;
import cm.inv.com.crawler.module.log.entity.Log;
import cm.inv.com.crawler.module.log.service.LogService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangtao107@126.com on 2016/11/7.
 */
public class logTest extends SpringTestBase {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private LogService logService;

    @Test
    @Transactional
    public void addLogTest(){
        Log log=new Log();
        log.setCreateId("1");
        log.setAppId("appNameProperty");
        log.setTitle("单元测试");
        log.setType("1");
        logService.save(log);

        logger.info(log.getId());

        Assert.assertTrue(!StringUtils.isEmpty(log.getId()));
    }

}
