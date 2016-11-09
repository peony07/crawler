package cm.inv.com.crawler.module.reptile.jiandan.service;

import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.module.reptile.jiandan.entity.JianDanImage;
import cm.inv.com.crawler.module.reptile.mztu.SaveImageListPipeline;
import org.springframework.stereotype.Service;
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
@Service
public class JianDanParse implements PageProcessor ,AfterExtractor {

    private List<JianDanImage> jianDanImageList;

    private Site site;

    @Override
    public void afterProcess(Page page) {

    }

    @Override
    public void process(Page page) {
        try {
            page.addTargetRequests(page.getHtml().links().regex("http://jandan.net/ooxx/page-\\d+#comments").all());
            List<String> all = page.getHtml().xpath("//div[@class='row']").all();
            jianDanImageList =new ArrayList<JianDanImage>();
            for(String content:all){
                if(StringUtils.isEmpty(content)) continue;
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
                    jidanDanImage.setSecondCategory("点赞20");
                }else if(new Integer(jidanDanImage.getSupport())<100){
                    jidanDanImage.setSecondCategory("点赞100");
                }else if(new Integer(jidanDanImage.getSupport())<200){
                    jidanDanImage.setSecondCategory("点赞200");
                }else if(new Integer(jidanDanImage.getSupport())<300){
                    jidanDanImage.setSecondCategory("点赞300");
                }else if(new Integer(jidanDanImage.getSupport())<500){
                    jidanDanImage.setSecondCategory("点赞500");
                }else{
                    jidanDanImage.setSecondCategory("点赞超过500");
                }
                jianDanImageList.add(jidanDanImage);
            }
            page.putField("imageList", jianDanImageList);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Site getSite() {
        //List<String[]> proxyPoolList =new ProxyFilePipeline().getProxyPool();
        if (site == null) {
            site=Site.me()
                    .setDomain("jandan.net")
                    .setCharset("UTF-8")
                    .setSleepTime(5000)
                    .setTimeOut(10000)
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                    .setRetryTimes(3)
                    //.setHttpProxyPool(proxyPoolList,false)
            ;
        }
        return site;
    }

    public static void start() {
        Spider.create(new JianDanParse())
                .addUrl("http://jandan.net/ooxx")
                .addPipeline(new SaveImageListPipeline())
                .thread(1)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();
    }

}
