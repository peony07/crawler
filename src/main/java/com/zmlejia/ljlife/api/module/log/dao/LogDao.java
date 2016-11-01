/**
 * .
 */
package com.zmlejia.ljlife.api.module.log.dao;


import com.zmlejia.ljlife.api.common.persistence.CrudDao;
import com.zmlejia.ljlife.api.common.persistence.annotation.MyBatisDao;
import com.zmlejia.ljlife.api.module.log.entity.Log;

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
