package com.zmlejia.ljlife.api.module.demo.chouti;

import com.zmlejia.ljlife.api.common.utils.StringUtils;
import com.zmlejia.ljlife.api.module.demo.dao.ChouTiDao;
import com.zmlejia.ljlife.api.module.demo.entity.ChouTi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Task;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
@Service
public class CrtChouTiPageModelPipeline implements us.codecraft.webmagic.pipeline.PageModelPipeline<CrtChouTi> {

    @Autowired
    private ChouTiDao chouTiDao;

    @Override
    public void process(CrtChouTi crtChouTi, Task task) {
        if(!StringUtils.isEmpty(crtChouTi.getShowContent())){
            for(int i=0;i<crtChouTi.getShowContent().size();i++){
                ChouTi chouTi=new ChouTi();
                chouTi.setSummary(crtChouTi.getShowContent().get(i));
                chouTi.setComeFrom(crtChouTi.getFrom().get(i));
                chouTi.setContent(null);
                chouTi.setTitle(crtChouTi.getShowContent().get(i));

                chouTi.setHeart(new Integer(crtChouTi.getDigg().get(i)==null?"0":crtChouTi.getDigg().get(i)));
                chouTi.setComment(new Integer(crtChouTi.getDiscus().get(i)==null?"0":crtChouTi.getDiscus().get(i)));
                chouTi.setPublishTime(crtChouTi.getPublicTime().get(i));
                chouTi.setExtUrl(crtChouTi.getExtUrl().get(i));
                chouTi.preInsert();
                chouTiDao.insert(chouTi);
            }
        }
    }
}
