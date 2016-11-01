package cm.inv.com.crawler.module.demo.jiandan;

import cm.inv.com.crawler.module.demo.mztu.SaveImageListPipeline;
import cm.inv.com.crawler.module.demo.Proxy.ProxyFilePipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */

public class JianDan implements PageProcessor ,AfterExtractor {

    private List<JianDanImage> jianDanImageList;

    private Site site;

    @Override
    public void afterProcess(Page page) {

    }

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("http://jandan.net/ooxx/page-\\d+#comments").all());
        List<String> all = page.getHtml().xpath("//div[@class='row']").all();
        jianDanImageList =new ArrayList<JianDanImage>();
        for(String content:all){
            JianDanImage jidanDanImage=new JianDanImage();
            jidanDanImage.setTitle("煎蛋-妹子图");
            jidanDanImage.setImageUrl(new Html(content).xpath("//div[@class='text']/p/a[@class='view_img_link']/@href").toString().trim());

            //if(jidanDanImage.getImageUrl().endsWith(".gif")){
            //    continue;
            //}
            jidanDanImage.setImageId(new Html(content).xpath("//div[@class='text']/span[@class='righttext']/a/text()").toString().trim());
            jidanDanImage.setSupport(new Html(content).xpath("//span[@id='cos_support-" + jidanDanImage.getImageId() + "']/text()").toString());
            jidanDanImage.setUnsupport(new Html(content).xpath("//span[@id='cos_unsupport-" + jidanDanImage.getImageId() + "']/text()").toString());
            //jidanDanImage.dsThreadCount = page.getHtml().xpath("//span[@class='time']/a/span[@class='ds-thread-count']/text()").toString().replaceAll("\\[", "").replaceAll("]", "").trim();
            jidanDanImage.setDsThreadCount("0");
            jidanDanImage.setTotalNum(new Integer(jidanDanImage.getSupport()) + new Integer(jidanDanImage.getUnsupport()) + new Integer(jidanDanImage.getDsThreadCount()));

            if(new Integer(jidanDanImage.getSupport())<20){
                jidanDanImage.setSecondCategory("渣渣");
            }else if(new Integer(jidanDanImage.getSupport())<100){
                jidanDanImage.setSecondCategory("清秀");
            }else if(new Integer(jidanDanImage.getSupport())<200){
                jidanDanImage.setSecondCategory("漂亮");
            }else if(new Integer(jidanDanImage.getSupport())<300){
                jidanDanImage.setSecondCategory("性感");
            }else if(new Integer(jidanDanImage.getSupport())<500){
                jidanDanImage.setSecondCategory("女神");
            }else{
                jidanDanImage.setSecondCategory("不可能");
            }
            jianDanImageList.add(jidanDanImage);
        }
        page.putField("imageList", jianDanImageList);
    }

    @Override
    public Site getSite() {
        List<String[]> proxyPoolList =new ProxyFilePipeline().getProxyPool();
        if (site == null) {
            site=Site.me()
                    .setDomain("jandan.net")
                    .setCharset("UTF-8")
                    .setSleepTime(10000)
                    .setTimeOut(10000)
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                    .setRetryTimes(3)
                    .setHttpProxyPool(proxyPoolList,false);
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JianDan())
                .addUrl("http://jandan.net/ooxx/page-900#comments")
                .addPipeline(new SaveImageListPipeline())
                .thread(100)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();
    }

}
