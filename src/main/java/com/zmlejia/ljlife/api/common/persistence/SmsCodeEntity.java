package com.zmlejia.ljlife.api.common.persistence;


public class SmsCodeEntity extends DataEntity<SmsCodeEntity>{
	
	/**
	* 手机号
	*/
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	* 短信验证码
	*/
	private String smsCode ;

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
	
}
