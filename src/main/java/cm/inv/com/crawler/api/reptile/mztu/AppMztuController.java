package cm.inv.com.crawler.api.reptile.mztu;

import cm.inv.com.crawler.common.web.BaseController;
import cm.inv.com.crawler.module.base.entity.RetApp;
import cm.inv.com.crawler.module.reptile.mztu.MztuParse;
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
@RequestMapping(value = "reptile/mztu/")
public class AppMztuController extends BaseController {


    @Autowired
    private MztuParse mztuParse;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RetApp add(HttpServletRequest request, HttpServletResponse response) {
        RetApp retApp = new RetApp();
        try {
            mztuParse.start();
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
