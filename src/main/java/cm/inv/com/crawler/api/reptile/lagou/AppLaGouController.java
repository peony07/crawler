package cm.inv.com.crawler.api.reptile.lagou;

import cm.inv.com.crawler.common.web.BaseController;
import cm.inv.com.crawler.module.base.entity.RetApp;
import cm.inv.com.crawler.module.reptile.lagou.service.LaGouJobCategoryParse;
import cm.inv.com.crawler.module.reptile.lagou.service.LaGouJobCategoryService;
import cm.inv.com.crawler.module.reptile.lagou.service.LaGouJobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangtao107@126.com on 2016/11/8.
 */

@RestController
@RequestMapping(value = "reptile/lagou/")
public class AppLaGouController extends BaseController {

    @Autowired
    private LaGouJobCategoryParse laGouJobCategoryParse;
    @Autowired
    private LaGouJobCategoryService laGouJobCategoryService;

    @Autowired
    private LaGouJobInfoService laGouJobInfoService;

    @RequestMapping(value = "jobCategory/add", method = RequestMethod.POST)
    public RetApp jobCategoryAdd(HttpServletRequest request, HttpServletResponse response) {
        RetApp retApp = new RetApp();
        try {
            laGouJobCategoryService.deleteAll();
            laGouJobCategoryParse.start();
            retApp.setStatus(OK);
            retApp.setMessage("成功！");
            return retApp;
        } catch (Exception e) {
            retApp.setStatus(FAIL);
            retApp.setMessage("失败！");
            return retApp;
        }
    }

    @RequestMapping(value = "jobInfo/add", method = RequestMethod.POST)
    public RetApp jobInfoAdd(HttpServletRequest request, HttpServletResponse response) {
        RetApp retApp = new RetApp();
        try {
            laGouJobInfoService.getAllJob();
            retApp.setStatus(OK);
            retApp.setMessage("成功！");
            return retApp;
        } catch (Exception e) {
            e.printStackTrace();
            retApp.setStatus(FAIL);
            retApp.setMessage("失败！");
            return retApp;
        }
    }

}
