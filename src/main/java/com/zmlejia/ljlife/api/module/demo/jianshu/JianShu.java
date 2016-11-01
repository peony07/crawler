package com.zmlejia.ljlife.api.module.demo.jianshu;

import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import static com.sun.deploy.util.SessionState.save;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */

@TargetUrl("http://www.jianshu.com/p/\\w+")
@HelpUrl("http://www.jianshu.com/\\w+/*")
public class JianShu implements AfterExtractor {

    @ExtractBy(value = "//a[@class='author-name']/span/text()", notNull = true)
    private String author;

    @ExtractBy(value = "//*[@id='flag']/div[2]/div[2]/div/div[1]/div[2]//span/allText()")
    private String authorInfo;

    @ExtractBy(value = "//div[@class='author-info']/span[starts-with(@data-original-title,'最后编辑于')]/text()|//*[@id='flag']/div[2]/div[2]/div/div[1]/span[2]/text()")
    private String createDate;

    @ExtractBy(value = "//div[@class='article']//[@class='title']/text()")
    private String title;

    @ExtractBy(value = "//script[@data-name='note']/html()")
    private String noteStr;

    private Note note;

    @ExtractBy(value = "//div[@class='image-package'][@class='imagebubble']/img/@src", notNull = true)
    private String indexImage;

    @ExtractBy(value = "div.show-content1", type = ExtractBy.Type.Css, notNull = true)
    private String showContent;

    public static void main(String[] args) {
        OOSpider.create(
                Site.me()
                        .setDomain("www.jianshu.com")
                        .setCharset("UTF-8")
                        .setSleepTime(1000)
                        .setTimeOut(3000)
                        .setUserAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)"),
                //new ConsolePageModelPipeline(),
                JianShu.class)
                .addUrl("http://www.jianshu.com/")
                .thread(10)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
    }


    @Override
    public void afterProcess(Page page) {
        //jfinal的属性其实是一个Map而不是字段，没关系，填充进去就是了
        this.note = JSONObject.parseObject(noteStr, Note.class);
        //保存
        save();
    }

    public String getNoteStr() {
        return noteStr;
    }

    public void setNoteStr(String noteStr) {
        this.noteStr = noteStr;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getIndexImage() {
        return indexImage;
    }

    public void setIndexImage(String indexImage) {
        this.indexImage = indexImage;
    }
}
