package com.zmlejia.ljlife.api.module.demo.chouti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */

@Service
@TargetUrl("http://dig.chouti.com/all/hot/recent/\\d+")
public class CrtChouTi implements AfterExtractor {

    @ExtractBy(value = "//div[@class='content-list']/div[@class='item']/div[@class='news-content']/div[@class='part1']/a[@class='show-content']/@href", notNull = true)
    private List<String> extUrl;
    @ExtractBy(value = "//div[@class='content-list']/div[@class='item']/div[@class='news-content']/div[@class='part2']/a[@class='user-a']/b/text()", notNull = true)
    private List<String> from;
    @ExtractBy(value = "//div[@class='content-list']/div[@class='item']/div[@class='news-content']/div[@class='part2']/a[@class='digg-a']/b/text()")
    private List<String> digg;
    @ExtractBy(value = "//div[@class='content-list']/div[@class='item']/div[@class='news-content']/div[@class='part2']/a[@class='discus-a']/b/text()")
    private List<String> discus;
    @ExtractBy(value = "//div[@class='content-list']/div[@class='item']/div[@class='news-content']/div[@class='part2']/span[@class='left'][@class='time-into']/a[@class='time-a']/b/text()")
    private List<String> publicTime;
    @ExtractBy(value = "//div[@class='content-list']/div[@class='item']/div[@class='news-content']/div[@class='part2']/@share-title")
    private List<String> showContent;

    @Autowired
    private CrtChouTiPageModelPipeline crtChouTiPageModelPipeline;

    public List<String> getExtUrl() {
        return extUrl;
    }

    public void setExtUrl(List<String> extUrl) {
        this.extUrl = extUrl;
    }

    public List<String> getFrom() {
        return from;
    }

    public void setFrom(List<String> from) {
        this.from = from;
    }

    public List<String> getDigg() {
        return digg;
    }

    public void setDigg(List<String> digg) {
        this.digg = digg;
    }

    public List<String> getDiscus() {
        return discus;
    }

    public void setDiscus(List<String> discus) {
        this.discus = discus;
    }

    public List<String> getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(List<String> publicTime) {
        this.publicTime = publicTime;
    }

    public List<String> getShowContent() {
        return showContent;
    }

    public void setShowContent(List<String> showContent) {
        this.showContent = showContent;
    }

    public void start() {
        OOSpider.create(
                Site.me()
                        .setDomain("http://dig.chouti.com/")
                        .setCharset("UTF-8")
                        .setSleepTime(5000)
                        .setTimeOut(3000)
                        .setUserAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)"),
                crtChouTiPageModelPipeline,
                CrtChouTi.class)
                .addUrl("http://dig.chouti.com/all/hot/recent/1")
                .thread(1)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();
    }

    @Override
    public void afterProcess(Page page) {


    }

}
