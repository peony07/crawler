package cm.inv.com.crawler.module.reptile.lagou.entity.response;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class PositionResult {

    private int totalCount;

    private StrategyProperty strategyProperty;

    private QueryAnalysisInfo queryAnalysisInfo;

    private LocationInfo locationInfo;

    private int resultSize;

    private List<Result> result ;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public StrategyProperty getStrategyProperty() {
        return strategyProperty;
    }

    public void setStrategyProperty(StrategyProperty strategyProperty) {
        this.strategyProperty = strategyProperty;
    }

    public QueryAnalysisInfo getQueryAnalysisInfo() {
        return queryAnalysisInfo;
    }

    public void setQueryAnalysisInfo(QueryAnalysisInfo queryAnalysisInfo) {
        this.queryAnalysisInfo = queryAnalysisInfo;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public int getResultSize() {
        return resultSize;
    }

    public void setResultSize(int resultSize) {
        this.resultSize = resultSize;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
