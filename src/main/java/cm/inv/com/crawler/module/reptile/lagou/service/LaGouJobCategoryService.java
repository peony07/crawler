package cm.inv.com.crawler.module.reptile.lagou.service;

import cm.inv.com.crawler.common.service.CrudService;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouJobCategoryDao;
import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouJobCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
@Service
public class LaGouJobCategoryService extends CrudService<LaGouJobCategoryDao, LaGouJobCategory> implements Pipeline {

    @Transactional(readOnly = false)
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<LaGouJobCategory> laGouJobCategoryList=resultItems.get("laGouJobCategoryList");
        for(LaGouJobCategory laGouJobCategory:laGouJobCategoryList){
            laGouJobCategory.preInsert();
            dao.insert(laGouJobCategory);
        }
    }

    /**
     * 删除所有的分类信息
     */
    @Transactional(readOnly = false)
    public void deleteAll(){
        dao.deleteAll();
    }


}
