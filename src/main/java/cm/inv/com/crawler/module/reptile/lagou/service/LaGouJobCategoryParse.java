package cm.inv.com.crawler.module.reptile.lagou.service;

import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouJobCategory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LaGouJobCategoryParse implements PageProcessor ,AfterExtractor {

    private List<LaGouJobCategory> laGouJobCategoryList;

    @Autowired
    private LaGouJobCategoryService laGouJobCategoryService;

    private Site site;

    @Override
    public void afterProcess(Page page) {

    }

    @Override
    public void process(Page page) {
        try {
            laGouJobCategoryList=new ArrayList<LaGouJobCategory>();
            page.addTargetRequests(page.getHtml().links().regex("https://www.lagou.com/").all());
            List<String> largeTypeList = page.getHtml().xpath("//div[@class='mainNavs']//div[@class='menu_box']").all();
            for(String largeTypeContext:largeTypeList){
                if(StringUtils.isEmpty(largeTypeContext)) continue;
                String largeTypeName=new Html(largeTypeContext).xpath("//div[@class='menu_main'][@class='job_hopping']//h2/text()").toString().trim();
                List<String> middleTypeList = new Html(largeTypeContext).xpath("//div[@class='menu_sub'][@class='dn']//dl").all();
                for(String middleTypeContext:middleTypeList){
                    String middleTypeName=new Html(middleTypeContext).xpath("//dt//a/text()").toString().trim();
                    String middleTypeEnName=new Html(middleTypeContext).xpath("//dt//a/@href").toString().trim();
                    middleTypeEnName=middleTypeEnName.substring(0,middleTypeEnName.length()-1);
                    middleTypeEnName=middleTypeEnName.substring(middleTypeEnName.lastIndexOf("/")+1);
                    String middleTypeId=new Html(middleTypeContext).xpath("//dt//a/@data-lg-tj-no").toString().trim();

                    List<String> smallTypeNameList =new Html(middleTypeContext).xpath("//dd//a/text()").all();
                    List<String> smallTypeEnNameList =new Html(middleTypeContext).xpath("//dd//a/@href").all();
                    List<String> smallTypeIdList =new Html(middleTypeContext).xpath("//dd//a/@data-lg-tj-no").all();
                    for(int i=0;i<smallTypeNameList.size();i++ ){
                        String smallTypeName=smallTypeNameList.get(i).toString().trim();
                        String smallTypeId=smallTypeIdList.get(i).toString().trim();
                        String smallTypeEnName=smallTypeEnNameList.get(i).toString().trim();
                        String searchUrl=smallTypeEnName;
                        smallTypeEnName=smallTypeEnName.substring(0,smallTypeEnName.length()-1);
                        smallTypeEnName=smallTypeEnName.substring(smallTypeEnName.lastIndexOf("/")+1);

                        LaGouJobCategory laGouJobCategory=new LaGouJobCategory();
                        laGouJobCategory.setLargeTypeName(largeTypeName);
                        laGouJobCategory.setMiddleTypeId(middleTypeId);
                        laGouJobCategory.setMiddleTypeName(middleTypeName);
                        laGouJobCategory.setMiddleTypeEnName(middleTypeEnName);
                        laGouJobCategory.setSmallTypeId(smallTypeId);
                        laGouJobCategory.setSmallTypeName(smallTypeName);
                        laGouJobCategory.setSmallTypeEnName(smallTypeEnName);
                        laGouJobCategory.setSearchUrl(searchUrl);
                        laGouJobCategoryList.add(laGouJobCategory);
                    }

                }

            }
            page.putField("laGouJobCategoryList", laGouJobCategoryList);
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
        Spider.create(new LaGouJobCategoryParse())
                .addUrl("https://www.lagou.com/")
                .addPipeline(laGouJobCategoryService)
                .thread(1)
                //.scheduler(new RedisScheduler(Global.getConfig("redis.host")))
                .run();
    }

}
