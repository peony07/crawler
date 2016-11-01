package com.zmlejia.ljlife.api.common.utils;


import com.zmlejia.ljlife.api.common.config.Global;
import com.zmlejia.ljlife.api.common.exception.DataWarnningException;
import com.zmlejia.ljlife.api.common.persistence.SmsCodeEntity;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class SmsUtil extends Thread {
	
	private static final Logger log = Logger.getLogger(SmsUtil.class);
	public static final String SMS_PRIFIX = "ljhui.sms.";
	public static final String RESULT_OK="ok";
    public static final String RESULT_FAIL="fail";
    private static String appKeyId="6393a1b3cce36c4c4a2a4035324c42cf";
	private String telno;
	private String value;
	private String url;

	public SmsUtil(String telno,String value) {
		this.telno = telno;
		this.value = value;
	}

	public void run() {
        if(Global.TRUE.equals(Global.getConfig("CORE_SMS_SERVER_ENABLED"))){
            SmsSender.sendMsg(telno,value);
        }else {
            url = Global.getConfig("ljlife.sms.server");
            //发短信
            send(url, telno, value);
        }
	}

    public static String send(String mobile, String content) {
        if(Global.TRUE.equals(Global.getConfig("CORE_SMS_SERVER_ENABLED"))){
            return SmsSender.sendMsg(mobile,content);
        }else {
            String url = Global.getConfig("ljlife.sms.server");
            //发短信
            return send(url, mobile, content);
        }
    }

    private static String send(String url,String telno, String message) {
		String result = null;
		Map<String, Object> params = new HashMap<String, Object>();// 请求参数
		params.put("mobile",telno);// 接收短信的手机号码
		params.put("message", message);
        params.put("key", appKeyId);
		try {
			log.info("短信服务器:"+url);
			result = sendPost(url, urlencode(params));
			if(result==null){
				log.info("短信接口返回为空");
				return RESULT_OK;
			}
			JSONObject object = JSONObject.fromObject(result);
			String status = object.getString("status");
			if (status.equals("ok")) {
				log.debug("ok");
                return RESULT_OK;
			} else {
				log.debug("fail");
                return RESULT_FAIL;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
        return RESULT_FAIL;
	}


	/**
	 * 将map型转为请求参数型
	 * 
	 * @param data
	 *            请求参数。
	 * @return 返回字符串,例如value1=aaa&value2=bbb
	 */
    private static String urlencode(Map<String, ?> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, ?> i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=")
						.append(URLEncoder.encode(i.getValue() + "", "UTF-8"))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
    private static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String DEF_CHATSET = "UTF-8";
		URL realUrl = null;
		URLConnection conn = null;
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
		String result = "";
		try {
			realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", userAgent);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), DEF_CHATSET));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			log.info("短信发送结果："+param);
		} catch (Exception e) {
			log.info("短信发送请求出现异常："+e.toString());
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		finally {// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if(conn!=null) conn=null;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}


	/**
	 * 随机数
	 * @param count
	 * @return
	 */
    public static String authCode(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }
    
    
	/**
	* 此方法描述的是：校验短信验证码
	* @author:zhangtao107@126.com
	* @param mobile
	* @param smsCodeInput
	* @throws DataWarnningException void
	*/
	public static void checkSmsCode(String mobile,String key, String smsCodeInput) throws DataWarnningException {
		
		SmsCodeEntity smsCodeEntity = (SmsCodeEntity) JedisUtils.getObject(key);
		if (null==smsCodeEntity||smsCodeEntity.getSmsCode().isEmpty()) {
			log.info("从redis获取验证码为空");
			throw new DataWarnningException("短信验证码错误或已失效，请重新获取验证码");
		}
		
		if(!mobile.equals(smsCodeEntity.getMobile())){
			throw new DataWarnningException("验证码错误");
		}

		if(smsCodeInput.equals(smsCodeEntity.getSmsCode())){
			log.info("短信验证码正确，删除redis中的值");
			JedisUtils.delObject(key);
		}else {
			log.info("短信验证码已失效或不正确");
			throw new DataWarnningException("验证码错误");
		}
	}

	/**
	 * 该方法描述的是：dynamicReplaceMsg 动态替换消息
	 * @param content
	 * @param strs
	 * @return String
	 * 2016年4月27日
	 * @author:zhengshujun
	 */
	public static String dynamicReplaceMsg(String content,Object[] strs){
		String newContent = String.format(content, strs);
		return newContent;
	}

	public static void main(String[] args) {
		
		//String msg = "\r\n" +"55668";
		//System.out.println(authCode(4));
		// 13430968254,18501972564  13028897374  18320881027
		//SmsUtil su = new SmsUtil("13430968254", "6782",msg);
		//su.start();
		String content = "说电话费%s我企鹅王%s";
		String str[] = {"qq","qqq"};
		System.out.println(dynamicReplaceMsg(content, str));
	}


}
