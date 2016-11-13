package cm.inv.com.crawler.module.reptile.lagou.entity;

import cm.inv.com.crawler.common.persistence.DataEntity;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class LaGouJobCategory extends DataEntity<LaGouJobCategory> {
    private static final long serialVersionUID = 1L;

    private String largeTypeName;
    private String middleTypeId;
    private String middleTypeName;
    private String middleTypeEnName;
    private String smallTypeId;
    private String smallTypeName;
    private String smallTypeEnName;
    private String searchUrl;

    public String getLargeTypeName() {
        return largeTypeName;
    }

    public void setLargeTypeName(String largeTypeName) {
        this.largeTypeName = largeTypeName;
    }

    public String getMiddleTypeId() {
        return middleTypeId;
    }

    public void setMiddleTypeId(String middleTypeId) {
        this.middleTypeId = middleTypeId;
    }

    public String getMiddleTypeName() {
        return middleTypeName;
    }

    public void setMiddleTypeName(String middleTypeName) {
        this.middleTypeName = middleTypeName;
    }

    public String getMiddleTypeEnName() {
        return middleTypeEnName;
    }

    public void setMiddleTypeEnName(String middleTypeEnName) {
        this.middleTypeEnName = middleTypeEnName;
    }

    public String getSmallTypeId() {
        return smallTypeId;
    }

    public void setSmallTypeId(String smallTypeId) {
        this.smallTypeId = smallTypeId;
    }

    public String getSmallTypeName() {
        return smallTypeName;
    }

    public void setSmallTypeName(String smallTypeName) {
        this.smallTypeName = smallTypeName;
    }

    public String getSmallTypeEnName() {
        return smallTypeEnName;
    }

    public void setSmallTypeEnName(String smallTypeEnName) {
        this.smallTypeEnName = smallTypeEnName;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }
}
