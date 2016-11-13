package cm.inv.com.crawler.module.reptile.lagou.entity.response;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class QueryAnalysisInfo {
    private String positionName;

    private String companyName;

    private String industryName;

    private boolean usefulCompany;

    public void setPositionName(String positionName){
        this.positionName = positionName;
    }
    public String getPositionName(){
        return this.positionName;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getCompanyName(){
        return this.companyName;
    }
    public void setIndustryName(String industryName){
        this.industryName = industryName;
    }
    public String getIndustryName(){
        return this.industryName;
    }
    public void setUsefulCompany(boolean usefulCompany){
        this.usefulCompany = usefulCompany;
    }
    public boolean getUsefulCompany(){
        return this.usefulCompany;
    }
}
