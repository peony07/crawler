package cm.inv.com.crawler.module.reptile.lagou.dao;

import cm.inv.com.crawler.common.persistence.CrudDao;
import cm.inv.com.crawler.common.persistence.annotation.MyBatisDao;
import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouJobCategory;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
@MyBatisDao
public interface LaGouJobCategoryDao extends CrudDao<LaGouJobCategory> {

    void deleteAll();
}
