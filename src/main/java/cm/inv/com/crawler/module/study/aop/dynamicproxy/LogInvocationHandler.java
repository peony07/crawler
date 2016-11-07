package cm.inv.com.crawler.module.study.aop.dynamicproxy;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class LogInvocationHandler implements InvocationHandler {

    private Logger logger=Logger.getLogger(LogInvocationHandler.class);

    private Object target;

    LogInvocationHandler(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        preRequest();
        Object rev=method.invoke(target,args);
        complaintRequest();
        return rev;
    }

    private void preRequest(){
        logger.info(" check befor post ");
    }

    private void complaintRequest(){
        logger.info(" insert log after post ");
    }
}
