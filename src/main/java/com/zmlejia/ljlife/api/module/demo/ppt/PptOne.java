package com.zmlejia.ljlife.api.module.demo.ppt;

import com.zmlejia.ljlife.api.module.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */

@TargetUrl(value={"http://www.1ppt.com/article/\\d+.html"})
@HelpUrl("http://www.1ppt.com/moban/[a-zA-Z]+/ppt_[a-zA-Z]+_\\d+.html")
public class PptOne implements AfterExtractor {

    @ExtractBy(value = "//div[@class='info_left']/ul/li[1]/a/text()")
    private String category;

    @ExtractBy(value = "//div[@class='ppt_info][@class='clearfix']/h1/text()")
    private String pptName;

    @ExtractBy(value = "//ul[@class='downurllist']/li/a/@href")
    private String downloadUrl;

    @ExtractBy(value = "//div[@class='info_left']/ul/li[2]/text()")
    private String updateTime;

    @ExtractBy(value = "//div[@class='info_left']/ul/li[5]/text()")
    private String downTimes;

    @ExtractBy(value = "//div[@class='info_left']/ul/li[4]/text()")
    private String fileSize;
    @ExtractByUrl(value = "http://www.1ppt.com/article/\\d+.html",notNull = true)
    private String id;

    public PptOne() {
    }

    public static void main(String[] args) {
        OOSpider.create(
                Site.me()
                        .setDomain("www.1ppt.com")
                        .setCharset("GBK")
                        .setSleepTime(1000)
                        .setTimeOut(30000)
                        .setUserAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)"),
                new PptFilePipeline(),
                PptOne.class)
                .addUrl("http://www.1ppt.com/")
                .thread(100)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();
    }



    @Override
    public void afterProcess(Page page) {

        if(!StringUtils.isEmpty(updateTime)&&updateTime.indexOf("：")>=0){
            setUpdateTime(updateTime.split("：")[1].trim());
        }

        if(!StringUtils.isEmpty(downTimes)){
            setUpdateTime(updateTime.replaceAll("\"","").trim());
        }

/*        if(!StringUtils.isEmpty(fileSize)&&fileSize.indexOf("：")>=0){
            setFileSize(fileSize.split("文件大小：")[1].replaceAll("KB","").trim());
        }

       String url= "http://www.1ppt.com/plus/count.php?view=yes&aid="+id+"&mid=1";
        */

        if(!StringUtils.isEmpty(category)){
            setCategory(CommonUtil.getNewDirName(category));
        }

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPptName() {
        return pptName;
    }

    public void setPptName(String pptName) {
        this.pptName = pptName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDownTimes() {
        return downTimes;
    }

    public void setDownTimes(String downTimes) {
        this.downTimes = downTimes;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
