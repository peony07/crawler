package cm.inv.com.crawler.module.reptile.lagou.service;

import cm.inv.com.crawler.common.service.CrudService;
import cm.inv.com.crawler.common.utils.HttpUtil;
import cm.inv.com.crawler.common.utils.SpringContextHolder;
import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouJobCategoryDao;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouJobCompanyDao;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouJobInfoDao;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouTagsDao;
import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouJobCategory;
import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouJobInfo;
import cm.inv.com.crawler.module.reptile.lagou.entity.response.Response;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
@Service
public class LaGouJobInfoService extends CrudService<LaGouJobInfoDao, LaGouJobInfo> implements Pipeline {

    Logger logger= LoggerFactory.getLogger(LaGouJobInfoService.class);

    //创建一个可重用固定线程数的线程池
    ExecutorService pool = Executors.newSingleThreadExecutor();

    @Autowired
    private LaGouJobCategoryDao laGouJobCategoryDao;
    @Autowired
    private LaGouTagsDao laGouTagsDao;
    @Autowired
    private LaGouJobCompanyDao laGouJobCompanyDao;

    @Transactional(readOnly = false)
    @Override
    public void process(ResultItems resultItems, Task task) {

    }

    private int sleepTime=1000*10;

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Transactional(readOnly = false)
    public void getAllJob() throws InterruptedException {
        List<LaGouJobCategory> laGouJobCategories=laGouJobCategoryDao.findList(new LaGouJobCategory());
        if(!StringUtils.isEmpty(laGouJobCategories)){
            for(LaGouJobCategory laGouJobCategory:laGouJobCategories){
                getJob(laGouJobCategory,1);
            }
        }
    }


    @Transactional(readOnly = false)
    public void getJob(LaGouJobCategory laGouJobCategory, int pageNo) throws InterruptedException {
        String url="https://www.lagou.com/jobs/positionAjax.json?pn="+pageNo+"&kd="+laGouJobCategory.getSmallTypeName();
        Thread.sleep(getSleepTime());
        String result=HttpUtil.post(url);
        if(!StringUtils.isEmpty(result)){
            Response response= JSONObject.parseObject(result,Response.class);
            String categoryId=laGouJobCategory.getId();
            if(null!=response){

                ResonseService resonseService= SpringContextHolder.getBean("resonseService");
                logger.info(resonseService.toString());
                resonseService.setResponse(response);
                resonseService.setCategoryId(categoryId);
                pool.execute(resonseService);

                int resultSize=response.getContent().getResultSize();
                if(resultSize==15){
                    getJob(laGouJobCategory,pageNo+1);
                }

            }
        }
    }

}
