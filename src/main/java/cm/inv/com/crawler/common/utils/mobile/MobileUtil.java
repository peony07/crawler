package cm.inv.com.crawler.common.utils.mobile;

import cm.inv.com.crawler.common.utils.StringUtils;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MobileUtil {
	private static Log log=LogFactory.getLog(MobileUtil.class);
	
	static String httpUrl = "http://apis.baidu.com/apistore/mobilenumber/mobilenumber";
	static String apiKey = "435518922e9500997469fc3d5836059e";
	
	/**
	* 此方法描述的是：findMobileInfo
	* @author:zhangtao107@126.com
	* @param mobile
	* @return String
	*/
	public static String findMobileInfo(String mobile) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String thisHttpUrl = httpUrl + "?phone=" + mobile;
		try {
			URL url = new URL(thisHttpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey", apiKey);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	* 此方法描述的是：getMobileInfo
	* @author:zhangtao107@126.com
	* @param mobile
	* @return MobileInfo
	*/
	public static MobileInfo getMobileInfo(String mobile) {
		MobileInfo mobileInfo = new MobileInfo();
		JSONObject result = new JSONObject(); 
		result = JSONObject.fromObject(findMobileInfo(mobile));
		if (!StringUtils.isEmpty(result.get("retMsg"))&&"success".equals(result.get("retMsg"))) {
			JSONObject jsonObject = JSONObject.fromObject(result.get("retData"));
			mobileInfo = (MobileInfo) JSONObject.toBean(jsonObject, MobileInfo.class);
		}
		mobileInfo.setRetMsg(StringUtils.isEmpty(result.get("retMsg"))?"":result.get("retMsg").toString());
		mobileInfo.setErrNum(StringUtils.isEmpty(result.get("errNum"))?"":result.get("errNum").toString());
		return mobileInfo;
	}

	public static void main(String[] args) {
		MobileInfo mobileInfo=getMobileInfo("17854871577");
		System.out.println(mobileInfo.getErrNum());
		System.out.println(mobileInfo.getRetMsg());
		
		System.out.println(mobileInfo.getPhone());
		System.out.println(mobileInfo.getPrefix());
		System.out.println(mobileInfo.getSupplier());
		System.out.println(mobileInfo.getProvince());
		System.out.println(mobileInfo.getCity());
		System.out.println(mobileInfo.getSuit());
	}
}
