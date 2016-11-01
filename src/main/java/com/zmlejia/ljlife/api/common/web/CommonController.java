/**
 * .
 */
package com.zmlejia.ljlife.api.common.web;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 此类描述的是：CommonController.java
 * 
 * @author:zhangtao107@126.com
 * @version: 2016-3-2 下午4:27:55
 */

@Controller
@RequestMapping(value = "${adminPath}/common/")
public class CommonController extends BaseController {

	/**
	 * 此方法描述的是：form
	 * 
	 * @author:zhangtao107@126.com
	 * @param request
	 * @param response
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "importExcel")
	public String form(HttpServletRequest request, HttpServletResponse response, Model model) {

		String downTemplateUrl=request.getParameter("downTemplateUrl")==null?"":request.getParameter("downTemplateUrl").toString();
		String importUrl=request.getParameter("importUrl")==null?"":request.getParameter("importUrl").toString();
		
		model.addAttribute("downTemplateUrl", downTemplateUrl); 
		model.addAttribute("importUrl", importUrl); 
		return "modules/common/excel/importExcel";
	}

}
