package cm.inv.com.crawler.module.demo.jiandan;


import cm.inv.com.crawler.module.demo.util.BaseImageBean;

/**
 * Created by zhangtao107@126.com on 2016/9/21.
 */

public class JianDanImage extends BaseImageBean {

    private String imageUrl;

    private String imageId;

    private String title;

    private String support;

    private String unsupport;

    private String dsThreadCount;

    private int totalNum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getUnsupport() {
        return unsupport;
    }

    public void setUnsupport(String unsupport) {
        this.unsupport = unsupport;
    }

    public String getDsThreadCount() {
        return dsThreadCount;
    }

    public void setDsThreadCount(String dsThreadCount) {
        this.dsThreadCount = dsThreadCount;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String getImageId() {
        return imageId;
    }

    @Override
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
