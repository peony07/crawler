package cm.inv.com.crawler.module.sys.service;

import cm.inv.com.crawler.common.config.Global;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
public class SystemService {

    /**
     * 获取Key加载信息
     */
    public static boolean printKeyLoadMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用 "+ Global.getConfig("productName")+"\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }
}
