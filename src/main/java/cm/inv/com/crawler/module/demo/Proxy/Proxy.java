package cm.inv.com.crawler.module.demo.Proxy;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/25 0025.
 */

public class Proxy implements PageProcessor,AfterExtractor {

    private Site site;

    @Override
    public void afterProcess(Page page) {

    }

    @Override
    public void process(Page page) {
        List<String[]> httpProxyList=new ArrayList<String[]>();
        page.addTargetRequests(page.getHtml().links().regex("http://www.66ip.cn/areaindex_\\d+/\\d+.html").all());
        List<Selectable> allNotes = page.getHtml().xpath("//div[@class='containerbox'][@class='boxindex']//table/tbody/tr").nodes();
        for(Selectable note:allNotes){
            String ip=note.css("tr>td:nth-of-type(1)","text").toString();
            String port=note.xpath("//tr/td[2]/text()").toString();
            if(StringUtils.isEmpty(ip)||StringUtils.isEmpty(port)||"ip".equals(ip)){
                continue;
            }
            httpProxyList.add(new String[]{ip,port});
        }
        page.putField("httpProxyList",httpProxyList);
    }

    @Override
    public Site getSite() {
        List<String[]> proxyPoolList =new ProxyFilePipeline().getProxyPool();
        if (site == null) {
            site=Site.me()
                    .setDomain("www.66ip.cn")
                    .setCharset("GBK")
                    .setSleepTime(1000)
                    .setTimeOut(10000)
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
                    //.setHttpProxyPool(proxyPoolList,false)
                    .setRetryTimes(3);
        }
        return site;
    }

    public static void main(String[] args) {
                Spider.create(new Proxy())
                .addUrl("http://www.66ip.cn/areaindex_1/1.html")
                .addPipeline(new ProxyFilePipeline())
                .thread(100)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();
    }

}
