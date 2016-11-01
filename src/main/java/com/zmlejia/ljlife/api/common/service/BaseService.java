/**
 * .
 */
package com.zmlejia.ljlife.api.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class BaseService {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public static final String OK = "1";
	public static final String FAIL = "0";
	public static final String USER_DISABLED = "-1";


}
