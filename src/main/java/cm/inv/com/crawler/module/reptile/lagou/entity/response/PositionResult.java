package cm.inv.com.crawler.module.reptile.lagou.entity.response;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class PositionResult {
    private Position position;

    private String labelList;

    private List<LableList> lableList ;

    private String recPositionVo;

    private String hotPositionVo;

    private String positionVo;

    public void setPosition(Position position){
        this.position = position;
    }
    public Position getPosition(){
        return this.position;
    }
    public void setLabelList(String labelList){
        this.labelList = labelList;
    }
    public String getLabelList(){
        return this.labelList;
    }
    public void setLableList(List<LableList> lableList){
        this.lableList = lableList;
    }
    public List<LableList> getLableList(){
        return this.lableList;
    }
    public void setRecPositionVo(String recPositionVo){
        this.recPositionVo = recPositionVo;
    }
    public String getRecPositionVo(){
        return this.recPositionVo;
    }
    public void setHotPositionVo(String hotPositionVo){
        this.hotPositionVo = hotPositionVo;
    }
    public String getHotPositionVo(){
        return this.hotPositionVo;
    }
    public void setPositionVo(String positionVo){
        this.positionVo = positionVo;
    }
    public String getPositionVo(){
        return this.positionVo;
    }
}
