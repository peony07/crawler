package cm.inv.com.crawler.module.reptile.lagou.entity.response;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class Response {
    private boolean success;

    private String requestId;

    private String msg;

    private String resubmitToken;

    private Content content;

    private int code;

    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return this.success;
    }
    public void setRequestId(String requestId){
        this.requestId = requestId;
    }
    public String getRequestId(){
        return this.requestId;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setResubmitToken(String resubmitToken){
        this.resubmitToken = resubmitToken;
    }
    public String getResubmitToken(){
        return this.resubmitToken;
    }
    public void setContent(Content content){
        this.content = content;
    }
    public Content getContent(){
        return this.content;
    }
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
}
