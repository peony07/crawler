package cm.inv.com.crawler.module.reptile.lagou.entity.response;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class LableList {
    private int createUserId;

    private boolean isDelete;

    private long lastUseTime;

    private String createType;

    private long createTime;

    private long updateTime;

    private String pinyin;

    private boolean isForbidden;

    private int showOrder;

    private String name;

    private int id;

    private String type;

    public void setCreateUserId(int createUserId){
        this.createUserId = createUserId;
    }
    public int getCreateUserId(){
        return this.createUserId;
    }
    public void setIsDelete(boolean isDelete){
        this.isDelete = isDelete;
    }
    public boolean getIsDelete(){
        return this.isDelete;
    }

    public long getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(long lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public void setCreateType(String createType){
        this.createType = createType;
    }
    public String getCreateType(){
        return this.createType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setPinyin(String pinyin){
        this.pinyin = pinyin;
    }
    public String getPinyin(){
        return this.pinyin;
    }
    public void setIsForbidden(boolean isForbidden){
        this.isForbidden = isForbidden;
    }
    public boolean getIsForbidden(){
        return this.isForbidden;
    }
    public void setShowOrder(int showOrder){
        this.showOrder = showOrder;
    }
    public int getShowOrder(){
        return this.showOrder;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }


}
