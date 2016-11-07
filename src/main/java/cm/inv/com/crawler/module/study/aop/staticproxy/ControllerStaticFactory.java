package cm.inv.com.crawler.module.study.aop.staticproxy;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class ControllerStaticFactory {

    public static BaseController getInstence() {
        return new ControllerProxy(new UserController());
    }
}
