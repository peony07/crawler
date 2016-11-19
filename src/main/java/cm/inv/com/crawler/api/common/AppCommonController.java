package cm.inv.com.crawler.api.common;

import cm.inv.com.crawler.common.utils.DateUtils;
import cm.inv.com.crawler.common.utils.HttpUtil;
import cm.inv.com.crawler.common.utils.JedisUtils;
import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.common.utils.redis.JedisClusterHelper;
import cm.inv.com.crawler.common.web.BaseController;
import cm.inv.com.crawler.module.base.entity.RetApp;
import cm.inv.com.crawler.module.reptile.Proxy.ProxyFilePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "${commonPath}/test/")
public class AppCommonController extends BaseController {

    private Logger logger= LoggerFactory.getLogger(AppCommonController.class);

    @Autowired
    private JedisClusterHelper jedisHelper;

    @RequestMapping(value = "redis", produces = {"application/json"}, method = RequestMethod.POST)
    public void redis(HttpServletRequest request, HttpServletResponse response) {
        RetApp retApp = new RetApp();
        try {
            String tradeNo = getParam(request, "tradeNo", "");
            String paidAmount = getParam(request, "paidAmount", "");
            String tradeStatus = getParam(request, "tradeStatus", "");
            String sign = getParam(request, "sign", "");

            //redis单机测试
            String redisClustervalue=JedisUtils.get("redisClusterKey_1");
            if(StringUtils.isEmpty(redisClustervalue)){
                JedisUtils.set("redisClusterKey_1","redisClustervalue_"+ DateUtils.getDate("yyyyMMddHHmmss"),0);
            }else{
                logger.info(redisClustervalue);
            }

            //redis 集群测试
            redisClustervalue=jedisHelper.get("redisClusterKey_1");
            if(StringUtils.isEmpty(redisClustervalue)){
                jedisHelper.saveOrUpdate("redisClusterKey_1","redisClustervalue_"+ DateUtils.getDate("yyyyMMddHHmmss"));
            }else{
                logger.info(redisClustervalue);
            }

            retApp.setStatus(OK);
            retApp.setMessage("成功！");
            renderString(response,retApp);
        } catch (Exception e) {
            retApp.setStatus(FAIL);
            retApp.setMessage("失败！");
            renderString(response,retApp);
        }
    }


    @RequestMapping(value = "httpProxy", produces = {"application/json"}, method = RequestMethod.POST)
    public void httpProxy(HttpServletRequest request, HttpServletResponse response) {
        RetApp retApp = new RetApp();
        try {

            List<String[]> proxyPoolList =new ProxyFilePipeline().getProxyPool();
            if(!StringUtils.isEmpty(proxyPoolList)){
                for(String[] str:proxyPoolList) {
                    HttpUtil.post("https://www.lagou.com/jobs/positionAjax.json?pn=7&kd=税务15489898221",null,null,str[2],new Integer(str[3]));
                }
            }
            retApp.setStatus(OK);
            retApp.setMessage("成功！");
            renderString(response,retApp);
        } catch (Exception e) {
            retApp.setStatus(FAIL);
            retApp.setMessage("失败！");
            renderString(response,retApp);
        }
    }

}
