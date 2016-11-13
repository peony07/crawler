package cm.inv.com.crawler.module.reptile.lagou.entity;

import cm.inv.com.crawler.common.persistence.DataEntity;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class LaGouJobInfo extends DataEntity<LaGouJobInfo> {
    private static final long serialVersionUID = 1L;

    private String jobCode;
    private String jobCategoryId;
    private String jobCompanyId;
    private String title;
    private String monthlyPay;
    private String cityName;
    private String seniority;
    private String education;
    private String nature;
    private String publishTime;
    private String officeAddress;
    private String advantage;

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(String jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    public String getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(String jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMonthlyPay() {
        return monthlyPay;
    }

    public void setMonthlyPay(String monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }
}
