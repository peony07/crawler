package cm.inv.com.crawler.common.utils.sign;

import cm.inv.com.crawler.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangtao107@126.com on 2016/8/19.
 */
public class LjhuiSignUtil {

    private static Logger log = LoggerFactory.getLogger(LjhuiSignUtil.class);
    // 字符编码格式
    public static String input_charset = "utf-8";


    /**
     * 比较签名
     *
     * @param paramsMap 请求接收方接收到的参数
     * @param secret    乐家慧平台分配的用于计算签名的秘钥
     * @param sign      调用方使用签名生成规则生成的签名
     * @return
     */
    public static boolean checkSign(Map<String, String> paramsMap, String secret, String sign) {
        boolean checkFlag = false;

        //计算签名
        String newSign = generateSign(paramsMap, secret);
        if (StringUtils.isNotEmpty(newSign) && newSign.equals(sign)) {
            checkFlag = true;
        }
        return checkFlag;
    }

    /**
     * 根据请求接收方接收到的参数组paramsMap生成签名
     *
     * @param paramsMap 请求接收方接收到的参数
     * @param secret    乐家慧平台分配的用于计算签名的秘钥
     * @return
     */
    public static String generateSign(Map<String, String> paramsMap, String secret) {
        //返回的签名变量
        String sign = null;
        try {

            //过滤空值、sign与sign_type参数
            Map<String, String> sParaNew = paraFilter(paramsMap);

            sParaNew.put("signedSecKey", secret);
            //获取待签名字符串
            String preSignStr = createLinkString(sParaNew);
            log.debug("****************preSignStr " + preSignStr + "*****************");

            //计算签名
            sign = generateSign(preSignStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    /**
     * 根据请求接收方接收到的参数生成签名
     *
     * @param params 请求接收方接收到的参数
     * @return
     */
    protected static String generateSign(String params) {
        //返回的签名变量
        String sign = null;
        try {
            String toSign = (params == null ? "" : params);
            sign = getMd5Str(getContentBytes(toSign, input_charset));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    /**
     * 根据编码格式返回对应的字节数组
     *
     * @param content 字符串
     * @param charset 编码格式
     * @return
     * @throws UnsupportedEncodingException
     */
    protected static byte[] getContentBytes(String content, String charset) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(charset)) {
            return content.getBytes();
        }
        return content.getBytes(charset);
    }

    /**
     * 获取MD5字符串
     *
     * @param signbyte
     * @return
     * @throws NoSuchAlgorithmException
     */
    protected static String getMd5Str(byte[] signbyte) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return new BASE64Encoder().encode(md.digest(signbyte));
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    protected static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param paramsMap 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    protected static String createLinkString(Map<String, String> paramsMap) {
        List<String> keys = new ArrayList<String>(paramsMap.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = paramsMap.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    public static void main(String args[]){
        Map map =new HashMap();
        map.put("appId","146c74602a3c422b8e5aaf41d5eb73db");
        map.put("accessToken","56f6c3575b65684f33d23895f60a1505");
        map.put("tradeNo","test_zj1000000000");
        map.put("totalAmount","1080");
        map.put("backUrl","http://localhost:8181/ljhui-api/common/test/notify");


        String secret="f0ff6faaa34a4893b583b0568f61ca35";//乐家慧平台分配的秘钥
        String sign=generateSign(map,secret);
        log.info(sign);

        //log.info(checkSign(map,secret,sign));
    }
}
