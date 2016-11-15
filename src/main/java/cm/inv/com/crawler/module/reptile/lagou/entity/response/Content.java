package cm.inv.com.crawler.module.reptile.lagou.entity.response;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class Content {
    private int pageNo;
    private int pageSize;
    private PositionResult positionResult;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PositionResult getPositionResult() {
        return positionResult;
    }

    public void setPositionResult(PositionResult positionResult) {
        this.positionResult = positionResult;
    }
}
