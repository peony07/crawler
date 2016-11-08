package cm.inv.com.crawler.module.reptile.chouti.service;

import cm.inv.com.crawler.common.service.CrudService;
import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.module.reptile.chouti.dao.ChouTiDao;
import cm.inv.com.crawler.module.reptile.chouti.entity.ChouTi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
@Service
public class ChouTiService extends CrudService<ChouTiDao, ChouTi> implements PageModelPipeline<ChouTiParse> {

    @Transactional(readOnly = false)
    public void process(ChouTiParse chouTiParse, Task task) {
        if(!StringUtils.isEmpty(chouTiParse.getShowContent())){
            for(int i=0;i<chouTiParse.getShowContent().size();i++){
                ChouTi chouTi=new ChouTi();
                chouTi.setSummary(chouTiParse.getShowContent().get(i));
                chouTi.setComeFrom(chouTiParse.getFrom().get(i));
                chouTi.setContent(null);
                chouTi.setTitle(chouTiParse.getShowContent().get(i));

                chouTi.setHeart(new Integer(chouTiParse.getDigg().get(i)==null?"0":chouTiParse.getDigg().get(i)));
                chouTi.setComment(new Integer(chouTiParse.getDiscus().get(i)==null?"0":chouTiParse.getDiscus().get(i)));
                chouTi.setPublishTime(chouTiParse.getPublicTime().get(i));
                chouTi.setExtUrl(chouTiParse.getExtUrl().get(i));
                chouTi.preInsert();
                dao.insert(chouTi);
            }
        }
    }
}
