package cm.inv.com.crawler.module.study.aop.staticproxy;


import org.apache.log4j.Logger;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class UserController implements BaseController {

    private static Logger logger = Logger.getLogger(UserController.class);

    public void getList() {
        logger.info(" do getList !");
    }
}
