package cm.inv.com.crawler.module.reptile.lagou.entity;

import cm.inv.com.crawler.common.persistence.DataEntity;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class LaGouTags extends DataEntity<LaGouTags> {
    private static final long serialVersionUID = 1L;

    private String businessId;
    private String tagCategory;
    private String tagDescribe;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getTagCategory() {
        return tagCategory;
    }

    public void setTagCategory(String tagCategory) {
        this.tagCategory = tagCategory;
    }

    public String getTagDescribe() {
        return tagDescribe;
    }

    public void setTagDescribe(String tagDescribe) {
        this.tagDescribe = tagDescribe;
    }
}
