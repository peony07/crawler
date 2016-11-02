package cm.inv.com.crawler.common;

import cm.inv.com.crawler.common.utils.DateUtils;
import cm.inv.com.crawler.common.utils.JedisUtils;
import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.common.utils.redis.JedisClusterHelper;
import cm.inv.com.crawler.common.web.BaseController;
import cm.inv.com.crawler.module.base.entity.RetApp;
import cm.inv.com.crawler.module.demo.chouti.CrtChouTi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
@RequestMapping(value = "${commonPath}/")
public class AppCommonController extends BaseController {

    @Autowired
    private CrtChouTi crtChouTi;

    @Autowired
    private JedisClusterHelper jedisHelper;

    @RequestMapping(value = "/test/notify", produces = {"application/json"}, method = RequestMethod.POST)
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("****************** alipay callback notify *******************");
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
            logger.error("**********alipay callback notify error!**********", e);
            retApp.setStatus(FAIL);
            retApp.setMessage("失败！");
            renderString(response,retApp);
        }
    }

    @RequestMapping(value = "/test/chouti", produces = {"application/json"}, method = RequestMethod.POST)
    public void chouti(HttpServletRequest request, HttpServletResponse response) {
        RetApp retApp = new RetApp();
        try {
            crtChouTi.start();
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
