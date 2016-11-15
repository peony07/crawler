package cm.inv.com.crawler.module.reptile.lagou.service;

import cm.inv.com.crawler.common.utils.DateUtils;
import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouJobCategoryDao;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouJobCompanyDao;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouJobInfoDao;
import cm.inv.com.crawler.module.reptile.lagou.dao.LaGouTagsDao;
import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouJobCompany;
import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouJobInfo;
import cm.inv.com.crawler.module.reptile.lagou.entity.LaGouTags;
import cm.inv.com.crawler.module.reptile.lagou.entity.response.Response;
import cm.inv.com.crawler.module.reptile.lagou.entity.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
@Service
@Scope(value = "prototype")
public class ResonseService extends Thread {

    @Autowired
    private LaGouJobInfoDao laGouJobInfoDao;
    @Autowired
    private LaGouJobCategoryDao laGouJobCategoryDao;
    @Autowired
    private LaGouTagsDao laGouTagsDao;
    @Autowired
    private LaGouJobCompanyDao laGouJobCompanyDao;

    private String staticUrl="https://www.lgstatic.com/i/";
    private Response response;
    private String categoryId;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void run() {
        saveResponse(this.response,this.categoryId);
    }

    @Transactional(readOnly = false)
    private void saveResponse(Response response, String categoryId) {
        List<Result> list=response.getContent().getPositionResult().getResult();
        if(!StringUtils.isEmpty(list)){
            for(Result position:list){
                String positionId=new Integer(position.getPositionId()).toString();
                if(null==laGouJobInfoDao.get(positionId)) {
                    LaGouJobInfo laGouJobInfo = new LaGouJobInfo();
                    laGouJobInfo.setId(positionId);
                    laGouJobInfo.setTitle(position.getPositionName());
                    laGouJobInfo.setJobCompanyId(new Integer(position.getCompanyId()).toString());
                    laGouJobInfo.setJobCategoryId(categoryId);
                    laGouJobInfo.setJobCode(positionId);
                    laGouJobInfo.setMonthlyPay(position.getSalary());
                    laGouJobInfo.setCityName(position.getCity());
                    laGouJobInfo.setSeniority(position.getWorkYear());
                    laGouJobInfo.setEducation(position.getEducation());
                    laGouJobInfo.setNature(position.getJobNature());
                    laGouJobInfo.setPublishTime(position.getCreateTime());
                    laGouJobInfo.setOfficeAddress(null);
                    laGouJobInfo.setAdvantage(position.getPositionAdvantage());
                    laGouJobInfo.setCreateTime(new Date());
                    laGouJobInfo.setUpdateTime(new Date());
                    laGouJobInfoDao.insert(laGouJobInfo);

                    List<String> lableLists=position.getPositionLables();
                    if(!StringUtils.isEmpty(lableLists)){
                        for(String lable:lableLists){
                            LaGouTags laGouTags=new LaGouTags();
                            laGouTags.setBusinessId(laGouJobInfo.getId());
                            laGouTags.setTagCategory("POSITION");
                            laGouTags.setTagDescribe(lable);
                            laGouTags.preInsert();
                            laGouTagsDao.insert(laGouTags);
                        }
                    }

                    if(null==laGouJobCompanyDao.get(laGouJobInfo.getJobCompanyId())){
                        LaGouJobCompany laGouJobCompany=new LaGouJobCompany();
                        laGouJobCompany.setId(laGouJobInfo.getJobCompanyId());
                        laGouJobCompany.setCompanyShortName(position.getCompanyShortName());
                        laGouJobCompany.setCompanyFullName(position.getCompanyFullName());
                        laGouJobCompany.setCompanyCode(laGouJobCompany.getId());
                        laGouJobCompany.setFinancing(position.getFinanceStage());
                        laGouJobCompany.setCompanySize(position.getCompanySize());
                        laGouJobCompany.setLogo(staticUrl+position.getCompanyLogo());
                        laGouJobCompany.setMainBussiness(position.getIndustryField());
                        laGouJobCompany.setLastLogin( DateUtils.formatDate(new Date(position.getLastLogin()),"yyyy-MM-dd HH:mm:ss"));
                        laGouJobCompany.setCreateTime(new Date());
                        laGouJobCompany.setUpdateTime(new Date());
                        laGouJobCompanyDao.insert(laGouJobCompany);

                        List<String> companyLabelList=position.getCompanyLabelList();
                        if(!StringUtils.isEmpty(companyLabelList)){
                            for(String label:companyLabelList){
                                LaGouTags laGouTags=new LaGouTags();
                                laGouTags.setBusinessId(laGouJobCompany.getId());
                                laGouTags.setTagCategory("COMPANY");
                                laGouTags.setTagDescribe(label);
                                laGouTags.preInsert();
                                laGouTagsDao.insert(laGouTags);
                            }
                        }
                    }
                }
            }
        }
    }
}
