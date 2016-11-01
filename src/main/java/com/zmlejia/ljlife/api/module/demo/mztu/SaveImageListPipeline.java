package com.zmlejia.ljlife.api.module.demo.mztu;

import com.zmlejia.ljlife.api.module.demo.util.BaseImageBean;
import com.zmlejia.ljlife.api.module.demo.util.CommonUtil;
import com.zmlejia.ljlife.api.module.demo.util.ThreadPictures;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */
public class SaveImageListPipeline implements Pipeline {

    //创建一个可重用固定线程数的线程池
   ExecutorService pool = Executors.newFixedThreadPool(100);

    /**
     * 抓取图片存放目录
     */
    private String imageRootDir = "D:/yunpan/pictures";

    /**
     * 链接超时
     */
    private int timeOut = 1000 * 20;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<BaseImageBean> imageList=resultItems.get("imageList");
        if(!CollectionUtils.isEmpty(imageList)){
            for(BaseImageBean imageBean:imageList){
                if(imageBean.isSaveFlag()){
                    String url = imageBean.getImageUrl();
                    String title = imageBean.getTitle();
                    String dir=imageRootDir + File.separator + task.getSite().getDomain()+ File.separator+ CommonUtil.getNewDirName(title);

                    if(!StringUtils.isEmpty(imageBean.getSecondCategory())){
                        dir=dir+File.separator+imageBean.getSecondCategory();
                    }

                    if(!StringUtils.isEmpty(imageBean.getThirdCategory())){
                        dir=dir+File.separator+imageBean.getThirdCategory();
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
    }
}
