package cm.inv.com.crawler.module.reptile.mztu;

import cm.inv.com.crawler.common.config.Global;
import cm.inv.com.crawler.module.reptile.util.BaseImageBean;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */

@Service
@TargetUrl(value={"http://www.mzitu.com/\\d+","http://www.mzitu.com/\\d+/\\d+"})
public class MztuParse extends BaseImageBean implements AfterExtractor {

    @ExtractBy(value = "//div[@class='main-image']/p/a/img/@src")
    private String imageUrl;

    @ExtractBy(value = "//[@class='main-title']/text()")
    private String title;

    @ExtractBy(value = "div[class=pagenavi]>a:nth-last-child(2)>span", type = ExtractBy.Type.Css)
    private String totalNum;

    public static void start() {
        OOSpider.create(
                Site.me()
                        .setDomain("www.mzitu.com")
                        .setCharset("UTF-8")
                        .setSleepTime(3000)
                        .setTimeOut(30000)
                        .setUserAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)"),
                new SaveImagePipeline(),
                MztuParse.class)
                .addUrl("http://www.mzitu.com/all")
                .thread(10)
                .scheduler(new RedisScheduler(Global.getConfig("redis.host")))
                .run();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public void afterProcess(Page page) {
        if(!StringUtils.isEmpty(getTotalNum())){
            setTotalNum(Jsoup.parse(getTotalNum()).text());

            if(getTitle().lastIndexOf("（")>=0){
                setTitle(getTitle().substring(0, getTitle().lastIndexOf("（")) + "-[" + getTotalNum() + "]");
            }else{
                setTitle(getTitle()+ "-[" + getTotalNum() + "]");
            }
        }else{
            String num=getTitle().substring(getTitle().lastIndexOf("（")+1).replace("）","");
            setTotalNum(num);
            if(getTitle().lastIndexOf("（")>=0){
                setTitle(getTitle().substring(0, getTitle().lastIndexOf("（")) + "-[" + getTotalNum() + "]");
            }else{
               setSaveFlag(false);
            }
        }
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }
}
