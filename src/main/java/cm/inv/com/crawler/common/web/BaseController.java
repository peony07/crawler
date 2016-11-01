package cm.inv.com.crawler.common.web;

import cm.inv.com.crawler.common.mapper.JsonMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseController {

	public static final String OK = "1";
	public static final String FAIL = "0";
    public static final String USER_DISABLED = "-1";
	
	protected DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	protected SimpleDateFormat yearAndMonthFormat = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;
	
	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;
	
	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;

	/**
	 * 重写getParameter
	 * @param request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected String getParam(HttpServletRequest request,
			String key,String defaultValue) {
		
		String value = request.getParameter(key);
		
		if(value==null||value.isEmpty()){
			return defaultValue;
		}
		
		return StringEscapeUtils.escapeHtml4(value.trim());
	}

	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		logger.info(JsonMapper.toJsonString(object));
		return renderString(response, JsonMapper.toJsonString(object), "application/json");
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        out.println(string);
	        out.flush();
			out.close();
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 处理null
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	protected String IfIsNull(String value,String defaultValue) {
		if(value==null||value.isEmpty()){
			return defaultValue;
		}
		return value;
	}

}
