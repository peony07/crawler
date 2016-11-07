package cm.inv.com.crawler.module.study.aop.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class PayServiceFactory {

    public static Payservice getInstence(Payservice payserviceImpl){
        return (Payservice) Proxy.newProxyInstance(Payservice.class.getClassLoader(), new Class[]{Payservice.class}, new LogInvocationHandler(payserviceImpl));
    }
}
