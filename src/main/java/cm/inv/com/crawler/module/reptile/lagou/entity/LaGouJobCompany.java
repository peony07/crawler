package cm.inv.com.crawler.module.reptile.lagou.entity;

import cm.inv.com.crawler.common.persistence.DataEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class LaGouJobCompany extends DataEntity<LaGouJobCompany> {
    private static final long serialVersionUID = 1L;

    private String companyShortName;
    private String companyFullName;
    private String companyCode;
    private int jobNum;
    private BigDecimal promptTreatmentRate;
    private int treatmentDays;
    private int interviewEvaluationNum;
    private String lastLogin;
    private String financing;
    private String companySize;
    private String cityName;
    private String address;
    private String introduction;
    private String homePage;
    private String logo;
    private String slogan;
    private String mainBussiness;
    private List<LaGouTags> laGouTagsList;


    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public int getJobNum() {
        return jobNum;
    }

    public void setJobNum(int jobNum) {
        this.jobNum = jobNum;
    }

    public BigDecimal getPromptTreatmentRate() {
        return promptTreatmentRate;
    }

    public void setPromptTreatmentRate(BigDecimal promptTreatmentRate) {
        this.promptTreatmentRate = promptTreatmentRate;
    }

    public int getTreatmentDays() {
        return treatmentDays;
    }

    public void setTreatmentDays(int treatmentDays) {
        this.treatmentDays = treatmentDays;
    }

    public int getInterviewEvaluationNum() {
        return interviewEvaluationNum;
    }

    public void setInterviewEvaluationNum(int interviewEvaluationNum) {
        this.interviewEvaluationNum = interviewEvaluationNum;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getFinancing() {
        return financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getMainBussiness() {
        return mainBussiness;
    }

    public void setMainBussiness(String mainBussiness) {
        this.mainBussiness = mainBussiness;
    }

    public List<LaGouTags> getLaGouTagsList() {
        return laGouTagsList;
    }

    public void setLaGouTagsList(List<LaGouTags> laGouTagsList) {
        this.laGouTagsList = laGouTagsList;
    }
}
