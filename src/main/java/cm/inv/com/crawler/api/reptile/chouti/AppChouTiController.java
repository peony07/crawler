package cm.inv.com.crawler.api.reptile.chouti;

import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.common.web.BaseController;
import cm.inv.com.crawler.module.base.entity.RetApp;
import cm.inv.com.crawler.module.reptile.chouti.entity.ChouTi;
import cm.inv.com.crawler.module.reptile.chouti.service.ChouTiService;
import cm.inv.com.crawler.module.reptile.chouti.service.ChouTiParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhangtao107@126.com on 2016/11/8.
 */

@RestController
@RequestMapping(value = "reptile/chouti/")
public class AppChouTiController extends BaseController {

    @Autowired
    private ChouTiParse chouTiParse;

    @Autowired
    private ChouTiService chouTiService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RetApp add(HttpServletRequest request, HttpServletResponse response) {
        RetApp retApp = new RetApp();
        try {
            chouTiParse.start();
            retApp.setStatus(OK);
            retApp.setMessage("成功！");
            return retApp;
        } catch (Exception e) {
            retApp.setStatus(FAIL);
            retApp.setMessage("失败！");
            return retApp;
        }
    }

    @RequestMapping(value = "getList", method = RequestMethod.POST)
    public RetApp getList(@ModelAttribute ChouTi chouTi, HttpServletRequest request, HttpServletResponse response) {
        RetApp retApp = new RetApp();
        try {
            List<ChouTi> list=chouTiService.findList(chouTi);
            retApp.setTotal(new Long(!StringUtils.isEmpty(list)?list.size():0));
            retApp.setData(list);
            retApp.setStatus(OK);
            retApp.setMessage("成功！");
            return retApp;
        } catch (Exception e) {
            retApp.setStatus(FAIL);
            retApp.setMessage("失败！");
            return retApp;
        }
    }
}
