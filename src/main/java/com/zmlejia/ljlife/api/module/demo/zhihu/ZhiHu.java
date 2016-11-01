package com.zmlejia.ljlife.api.module.demo.zhihu;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import static com.sun.deploy.util.SessionState.save;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */

@TargetUrl("http://www.jianshu.com/p/\\w+")
@HelpUrl("http://www.jianshu.com/\\w+/*")
public class ZhiHu implements AfterExtractor {

    private Site site = Site.me()
            .setCycleRetryTimes(5)
            .setRetryTimes(5)
            .setSleepTime(500)
            .setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addCookie("d_c0", "\"AEBAqViJ4gmPTi692OegCYGw_jqahqu14GE=|1462601430\"")
            .addCookie("_za", "a235e458-a85c-49f8-af69-9a2529ff6b1c")
            .addCookie("_zap", "d259576c-b467-4ea9-b6e4-8568f5a5d3d6")
            //.addCookie("_xsrf",IndexPipeline.xsrf)
            .addCookie("s-q", "%E8%BD%AE%E5%AD%90%E5%93%A5")
            .addCookie("s-i", "1")
            .addCookie("sid", "6c3kfl28")
            .addCookie("s-t", "autocomplete")
            .addCookie("__utmt", "1")
            .addCookie("login", "\"YWU3YTdjYzMzMGJmNDUxZDhjYjRkMWQ1MWU5NzU4MmU=|1474423336|dea30fd83a071a8a16b73083ca30bbb3498ee227\"")
            .addCookie("q_c1", "8c8c812f2da145699355a780a2f79853|1474423367000|1474423367000")
            .addCookie("l_cap_id", "\"ZTgxYzM3OTMzNzMxNDI2MWEzYTBhNzJhZjU0ZDJlNGQ=|1474423367|ba5a0322dcb854390ba1d76732bd91b4e993dec0\"")
            .addCookie("cap_id", "\"MWIzOWMzOGUzOTI4NDE5NGFhMTI3OTlkYzNjODcxMzA=|1474423367|019d87c4c7b710bc2a6815fcbcb36c827300e794\"")
            .addCookie("__utma", "51854390.1893254563.1474420996.1474420996.1474420996.1")
            .addCookie("__utmb", "51854390.47.9.1474422206889")
            .addCookie("__utmc", "51854390")
            .addCookie("__utmz", "51854390.1474352966.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)")
            .addCookie("__utmv", "51854390.000--|2=registration_date=20160712=1^3=entry_date=20160921=1")
            .addCookie("n_c", "1")
            .addHeader("Host", "www.zhihu.com")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("Accept", "*/*")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("X-Xsrftoken", "2695fb95bf23cc3eefcc1ed19f28e3f0")
            .addHeader("Referer", "http://www.zhihu.com/?next=%2Fpeople%2Fexcited-vczh%2Ffollowers")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .setCharset("UTF-8");

    public static void main(String[] args) {

        /*OOSpider.create(
                site
                        .setDomain("www.jianshu.com")
                        .setCharset("UTF-8")
                        .setSleepTime(1000)
                        .setTimeOut(3000)
                        .setUserAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)"),
                //new ConsolePageModelPipeline(),
                ZhiHu.class)
                .addUrl("http://www.zhihu.com/login/phone_num?_xsrf=" + IndexPipeline.xsrf + "&password=1987107&remember_me=true&phone_num=18210225831")
                .thread(10)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();

        HttpPostRequest start = new HttpPostRequest("http://www.zhihu.com/login/phone_num?_xsrf=" + IndexPipeline.xsrf + "&password=1987107&remember_me=true&phone_num=18210225831");


        OOSpider.create(
                site
                        .setDomain("www.jianshu.com")
                        .setCharset("UTF-8")
                        .setSleepTime(1000)
                        .setTimeOut(3000)
                        .setUserAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)"),
                //new ConsolePageModelPipeline(),
                ZhiHu.class)
                .addUrl("http://www.zhihu.com/login/phone_num?_xsrf=" + IndexPipeline.xsrf + "&password=1987107&remember_me=true&phone_num=18210225831")
                .thread(10)
                //.scheduler(new RedisScheduler("127.0.0.1"))
                .run();*/
    }

    @Override
    public void afterProcess(Page page) {
        //jfinal的属性其实是一个Map而不是字段，没关系，填充进去就是了
        //保存
        save();
    }

}
