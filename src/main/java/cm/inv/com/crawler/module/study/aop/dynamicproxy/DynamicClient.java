package cm.inv.com.crawler.module.study.aop.dynamicproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * Created by zhangtao107@126.com on 2016/9/27.
 */
public class DynamicClient {
    public static void main(String[] args) {
        Payservice payserviceProxy= (Payservice) Proxy.newProxyInstance(Payservice.class.getClassLoader(),new Class[]{Payservice.class},new LogInvocationHandler(new AliPayServiceImpl()));
        payserviceProxy.pay();

        Car carProxy= (Car) Proxy.newProxyInstance(Car.class.getClassLoader(),new Class[]{Car.class},new LogInvocationHandler(new BaoMa()));
        carProxy.makeCar();
    }


    public static void createProxyClassFile() {
        String name = "payserviceProxy";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Payservice.class});
        try {
            FileOutputStream out = new FileOutputStream(name + ".class");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
