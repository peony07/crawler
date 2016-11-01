/**
 * .
 */
package cm.inv.com.crawler.module.log.utils;

import cm.inv.com.crawler.common.utils.Exceptions;
import cm.inv.com.crawler.common.utils.SpringContextHolder;
import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.module.log.dao.LogDao;
import cm.inv.com.crawler.module.log.entity.Log;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2014-11-7
 */
public class LogUtils {

    private static LogDao logDao = SpringContextHolder.getBean(LogDao.class);

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title){
		saveLog(request, null, null, title);
	}
	
	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title){
		//User user = UserUtils.getUser();
		//if (user != null && user.getId() != null){
			Log log = new Log();
			log.setTitle(title);
			log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
			log.setRemoteAddr(StringUtils.getRemoteAddr(request));
			log.setUserAgent(request.getHeader("user-agent"));
			log.setRequestUri(request.getRequestURI());
			log.setParams(request.getParameterMap());
			log.setMethod(request.getMethod());
			log.setRequestUri(request.getHeader(""));
			
			String token = request.getParameter("token");

			// 异步保存日志
			new SaveLogThread(log, handler, ex).start();
		//}
	}
	
	/**
	 * 保存日志api
	 */
	public static void saveLogForApi(HttpServletRequest request, Object handler, Exception ex, String title,long time){
			Log log = new Log();
			String token = request.getParameter("token");
			log.setTitle(title);
			log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
			log.setRemoteAddr(request.getHeader("devIP"));
            String userAgent =request.getHeader("user-agent");
            log.setUserAgent(userAgent);
			log.setRequestUri(request.getRequestURI());
			log.setParams(request.getParameterMap());
			log.setMethod(request.getMethod());
			log.setAppId(request.getHeader("appId"));//业主端：appNameOwner；物业端：appNameProperty
            log.setAppUserId(null);

			log.setDeviceType(request.getHeader("platform"));//终端类型 iOS：1；Android：2；wap：3
			log.setDeviceModel(request.getHeader("devmanufacturer"));//终端厂商
            log.setEquipmentModel(request.getHeader("devmodel"));//设备型号
			log.setRemoteIp(request.getHeader("devIP"));//终端的IP地址
			
			log.setSystemVersion(request.getHeader("devosversion"));//终端系统版本号
			log.setMacAddress(request.getHeader("devmac"));//MAC地址
			log.setImei(request.getHeader("devid"));//IEMI(国际移动设备标识)
			log.setRespTime((int) time);//接口耗时
			log.setLocation(request.getHeader("devsite"));//经度,纬度
			log.setNettype(request.getHeader("devnet"));//网络类型
            String devoperators =request.getHeader("devoperators");//运营商
            try {
                if(!StringUtils.isEmpty(devoperators)){
                    devoperators = URLDecoder.decode(devoperators, "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            log.setDevoperators(devoperators);
            log.setAppVersion(request.getHeader("appVersion"));//app版本号
			// 异步保存日志
			new SaveLogThread(log, handler, ex).start();
	}
	
	

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread{
		
		private Log log;
		private Object handler;
		private Exception ex;
		
		public SaveLogThread(Log log, Object handler, Exception ex){
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
		}
		
		@Override
		public void run() {
			// 获取日志标题

			// 如果有异常，设置异常信息
			log.setException(Exceptions.getStackTraceAsString(ex));
			// 如果无标题并无异常日志，则不保存信息
			if (StringUtils.isBlank(log.getTitle()) && StringUtils.isBlank(log.getException())){
				return;
			}
			// 保存日志信息
			log.preInsert();
			logDao.insert(log);
		}
	}

}
