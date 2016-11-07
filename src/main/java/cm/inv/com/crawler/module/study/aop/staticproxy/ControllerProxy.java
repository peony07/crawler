package cm.inv.com.crawler.module.study.aop.staticproxy;

import org.apache.log4j.Logger;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class ControllerProxy implements BaseController {
    private static Logger logger = Logger.getLogger(ControllerProxy.class);

    private UserController userController;

    public ControllerProxy(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void getList() {
        preRequest();

        this.userController.getList();

        postRequest();
    }

    private void preRequest() {
        logger.info("Pre Request.");
    }

    private void postRequest() {
        logger.info("Post Request.");
    }
}
