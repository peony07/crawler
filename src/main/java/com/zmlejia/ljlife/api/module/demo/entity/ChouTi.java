package com.zmlejia.ljlife.api.module.demo.entity;

import com.zmlejia.ljlife.api.common.persistence.DataEntity;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
public class ChouTi extends DataEntity<ChouTi> {

    private static final long serialVersionUID = 1L;

    private String comeFrom;
    private String title;
    private String summary;
    private String content;
    private String extUrl;
    private String publishTime;
    private int heart;
    private int comment;

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtUrl() {
        return extUrl;
    }

    public void setExtUrl(String extUrl) {
        this.extUrl = extUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
