package com.zmlejia.ljlife.api.module.listener;

import com.zmlejia.ljlife.api.module.service.SystemService;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;


public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
