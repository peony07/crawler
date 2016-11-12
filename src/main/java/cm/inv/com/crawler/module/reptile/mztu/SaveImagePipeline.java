package cm.inv.com.crawler.module.reptile.mztu;

import cm.inv.com.crawler.module.reptile.util.BaseImageBean;
import cm.inv.com.crawler.module.reptile.util.ThreadPictures;
import cm.inv.com.crawler.module.reptile.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */
public class SaveImagePipeline<T extends BaseImageBean> implements PageModelPipeline<T> {

    //创建一个可重用固定线程数的线程池
   ExecutorService pool = Executors.newFixedThreadPool(1000);

    /**
     * 抓取图片存放目录
     */
    private String imageRootDir = "D:/yunpan/pictures";

    /**
     * 链接超时
     */
    private int timeOut = 1000 * 200;


    @Override
    public void process(T t, Task task) {
        if(t.isSaveFlag()){
            String url = t.getImageUrl();
            String title = t.getTitle();
            String dir=imageRootDir + File.separator + task.getSite().getDomain()+ File.separator+ CommonUtil.getNewDirName(title);

            if(!StringUtils.isEmpty(t.getSecondCategory())){
                dir=dir+File.separator+t.getSecondCategory();
            }

            if(!StringUtils.isEmpty(t.getThirdCategory())){
                dir=dir+File.separator+t.getThirdCategory();
            }

            ThreadPictures threadPictures = new ThreadPictures();
            threadPictures.setPIC_DIR(dir);
            threadPictures.setPicURL(url);
            threadPictures.setTIME_OUT(timeOut);
            threadPictures.setDomainName(task.getSite().getDomain());
            pool.execute(threadPictures);
        }
    }


}
