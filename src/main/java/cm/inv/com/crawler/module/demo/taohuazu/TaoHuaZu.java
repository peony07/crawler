package cm.inv.com.crawler.module.demo.taohuazu;

import cm.inv.com.crawler.module.demo.mztu.SaveImageListPipeline;
import cm.inv.com.crawler.module.demo.util.BaseImageBean;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */

public class TaoHuaZu implements PageProcessor,AfterExtractor  {

    private List<BaseImageBean> baseImageBeanList;

    private Site site;

    @ExtractBy(value = "//td[@class='t_f']//img[@class='zoom']/@src")
    private List<String> imageUrl;

    @ExtractBy(value = "//h1[@class='ts']/span/text()")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public void afterProcess(Page page) {

    }

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("http://taohuabbs.com/thread-\\d+-1-1.html").all());
        List<String> all = page.getHtml().xpath("//td[@class='t_f']//img[@class='zoom']").all();
        String title=page.getHtml().xpath("//h1[@class='ts']/span/text()").toString();
        baseImageBeanList =new ArrayList<BaseImageBean>();
        for(String content:all){
            BaseImageBean baseImageBean=new BaseImageBean();
            baseImageBean.setTitle(title);
            baseImageBean.setImageUrl(new Html(content).xpath("//img/@file").toString().trim());

            //if(baseImageBean.getImageUrl().endsWith(".gif")){
            //    continue;
            //}
            baseImageBean.setImageId(new Html(content).xpath("//img/@id").toString().trim());

            baseImageBeanList.add(baseImageBean);
        }
        page.putField("imageList", baseImageBeanList);

        page.addTargetRequests(page.getHtml().links().regex("http://taohuahd.com/forum-\\d+-\\d+.html").all());
    }

    @Override
    public Site getSite() {
        if (site == null) {
            site=Site.me()
                    .setDomain("taohuabbs.com")
                    .setCharset("UTF-8")
                    .setSleepTime(10000)
                    .setTimeOut(10000)
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
                    .setRetryTimes(3);
        }
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new TaoHuaZu())
                .addUrl("http://taohuabbs.com/forum-56-1.html")
                .addPipeline(new SaveImageListPipeline())
                .thread(1)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();

    }

}
