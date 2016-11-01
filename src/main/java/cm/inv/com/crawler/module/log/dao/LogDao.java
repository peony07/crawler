/**
 * .
 */
package cm.inv.com.crawler.module.log.dao;


import cm.inv.com.crawler.common.persistence.CrudDao;
import cm.inv.com.crawler.common.persistence.annotation.MyBatisDao;
import cm.inv.com.crawler.module.log.entity.Log;

import java.util.List;

/**
 * 日志DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

    List<Log> findApiLogList(Log log);
}
