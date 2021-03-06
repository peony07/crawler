/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cm.inv.com.crawler.module.log.service;


import cm.inv.com.crawler.common.utils.DateUtils;
import cm.inv.com.crawler.module.log.dao.LogDao;
import cm.inv.com.crawler.common.persistence.Page;
import cm.inv.com.crawler.common.service.CrudService;
import cm.inv.com.crawler.module.log.entity.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

	public Page<Log> findPage(Page<Log> page, Log log) {

        // 设置默认时间范围，默认一个月
        if (log.getBeginDate() == null){
            log.setBeginDate(DateUtils.addMonths(new Date(),-1));
        }
        if (log.getEndDate() == null){
            log.setEndDate(new Date());
        }
		return super.findPage(page, log);
		
	}

    public Page<Log> findApiLogListPage(Page<Log>page, Log log) {
        // 设置默认时间范围，默认一个月
        if (log.getBeginDate() == null){
            log.setBeginDate(DateUtils.addMonths(new Date(),-1));
        }
        if (log.getEndDate() == null){
            log.setEndDate(new Date());
        }
        log.setPage(page);
        page.setList(dao.findApiLogList(log));
        return page;
    }
}
