package cm.inv.com.crawler.module.reptile.lagou.entity.response;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class LocationInfo {
    private String city;

    private String district;

    private String locationCode;

    private boolean queryByGisCode;

    private String businessZone;

    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public String getDistrict(){
        return this.district;
    }
    public void setLocationCode(String locationCode){
        this.locationCode = locationCode;
    }
    public String getLocationCode(){
        return this.locationCode;
    }
    public void setQueryByGisCode(boolean queryByGisCode){
        this.queryByGisCode = queryByGisCode;
    }
    public boolean getQueryByGisCode(){
        return this.queryByGisCode;
    }
    public void setBusinessZone(String businessZone){
        this.businessZone = businessZone;
    }
    public String getBusinessZone(){
        return this.businessZone;
    }
}
