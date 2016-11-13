package cm.inv.com.crawler.module.reptile.lagou.entity.response;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class Content {
    private int totalCount;

    private int pageNo;

    private StrategyProperty strategyProperty;

    private QueryAnalysisInfo queryAnalysisInfo;

    private int pageSize;

    private LocationInfo locationInfo;

    private int resultSize;

    private List<PositionResult> positionResult;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setStrategyProperty(StrategyProperty strategyProperty) {
        this.strategyProperty = strategyProperty;
    }

    public StrategyProperty getStrategyProperty() {
        return this.strategyProperty;
    }

    public void setQueryAnalysisInfo(QueryAnalysisInfo queryAnalysisInfo) {
        this.queryAnalysisInfo = queryAnalysisInfo;
    }

    public QueryAnalysisInfo getQueryAnalysisInfo() {
        return this.queryAnalysisInfo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public LocationInfo getLocationInfo() {
        return this.locationInfo;
    }

    public void setResultSize(int resultSize) {
        this.resultSize = resultSize;
    }

    public int getResultSize() {
        return this.resultSize;
    }

    public void setPositionResult(List<PositionResult> positionResult) {
        this.positionResult = positionResult;
    }

    public List<PositionResult> getPositionResult() {
        return this.positionResult;
    }
}
