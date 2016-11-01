/**
 * .
 */
package cm.inv.com.crawler.module.base.entity;

import java.io.Serializable;

/**
 * 字典Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class RetApp implements Serializable  {

	
	private static final long serialVersionUID = 1L;
	private String status;	// 状态
	private String message;	// 消息
	private Long total; // 数据总条数
	private Object data;	// 数据
	
	
	
	
	
	/**
	 * 分页
	 * @param status
	 * @param message
	 * @param total
	 * @param data
	 */
	public RetApp(String status, String message, Long total, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.total = total;
		this.data = data;
	}
	
	/**
	 * 不分页
	 * @param status
	 * @param message
	 * @param data
	 */
	public RetApp(String status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

    
	public RetApp() {
		super();
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	

	
	
}