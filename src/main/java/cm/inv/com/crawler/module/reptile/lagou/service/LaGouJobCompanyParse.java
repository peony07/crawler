package cm.inv.com.crawler.module.reptile.lagou.service;

import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouJobCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */
@Service
public class LaGouJobCompanyParse implements PageProcessor ,AfterExtractor {

    private List<LaGouJobCompany> laGouJobCompanyList;

    @Autowired
    private LaGouJobCompanyService laGouJobCompanyService;

    private Site site;

    @Override
    public void afterProcess(Page page) {

    }

    @Override
    public void process(Page page) {
        try {
            laGouJobCompanyList=new ArrayList<LaGouJobCompany>();
            page.addTargetRequests(page.getHtml().links().regex("https://www.lagou.com/gongsi/\\d+.html").all());
            List<String> largeTypeList = page.getHtml().xpath("//div[@class='mainNavs']//div[@class='menu_box']").all();

            page.putField("laGouJobCompanyList", laGouJobCompanyList);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Site getSite() {
        //List<String[]> proxyPoolList =new ProxyFilePipeline().getProxyPool();
        if (site == null) {
            site=Site.me()
                    .setDomain("www.lagou.com")
                    .setCharset("UTF-8")
                    .setSleepTime(5000)
                    .setTimeOut(10000)
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                    .setRetryTimes(2)
                    //.setHttpProxyPool(proxyPoolList,false)
            ;
        }
        return site;
    }

    public void start() {
        Spider.create(new LaGouJobCompanyParse())
                .addUrl("https://www.lagou.com/")
                .addPipeline(laGouJobCompanyService)
                .thread(1)
                //.scheduler(new RedisScheduler(Global.getConfig("redis.host")))
                .run();
    }

}
