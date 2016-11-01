package cm.inv.com.crawler.module.demo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class CommonUtil {
	private static final Log log = LogFactory.getLog(CommonUtil.class);
	private static String uid = "0";
	private static String tempStr=":,/,?,*,“,<,>,|";
	private static String[] temp=tempStr.split(",");
	private static String configPath = "config.properties";

    /**
     * 读取属性文件
     * @param key
     * @return
     */
    public static String getPropertiesValue(String key) {
        String propertyValue = "";
        Properties prop = new Properties();
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(configPath);
            prop.load(in);
            propertyValue = prop.getProperty(key);
            in.close();
        } catch (FileNotFoundException e) {
            log.error("没有提供的属性文件！");
            e.printStackTrace();
            throw new RuntimeException("配置文件" + configPath + "不存在!");
        } catch (IOException e) {
            log.error("读取属性文件有误，请检查属性文件！");
            e.printStackTrace();
            throw new RuntimeException("读取属性文件有误，请检查属性文件！");
        }
        return propertyValue;

    }

    /**
     *采用Properties类取得属性文件对应值
     *@parampropertiesFileNameproperties文件名，如a.properties
     *@parampropertyName属性名
     *@return根据属性名得到的属性值，如没有返回""
     */
    public static String getValueByPropertyName(String propertiesFileName, String propertyName) {
        String s = "";
        Properties p = new Properties();//加载属性文件读取类
        InputStream in;
        try {
            //propertiesFileName如test.properties
            in =Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);//以流的形式读入属性文件
            p.load(in);//属性文件将该流加入的可被读取的属性中
            in.close();//读完了关闭
            s = p.getProperty(propertyName);//取得对应的属性值
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     *采用ResourceBundel类取得属性文件对应值，这个只能够读取，不可以更改及写新的属性
     *@parampropertiesFileNameWithoutPostfixproperties文件名，不带后缀
     *@parampropertyName属性名
     *@return根据属性名得到的属性值，如没有返回""
     */
    public String getValueByPropertyName_(String propertiesFileNameWithoutPostfix, String propertyName) {
        String s = "";
        //如属性文件是test.properties，那此时propertiesFileNameWithoutPostfix的值就是test
        ResourceBundle bundel = ResourceBundle.getBundle(propertiesFileNameWithoutPostfix);
        s = bundel.getString(propertyName);
        return s;
    }

    /**
     *更改属性文件的值，如果对应的属性不存在，则自动增加该属性
     *@parampropertiesFileNameproperties文件名，如a.properties
     *@parampropertyName属性名
     *@parampropertyValue将属性名更改成该属性值
     *@return是否操作成功
     */
    public static boolean changeValueByPropertyName(String propertiesFileName, String propertyName, String propertyValue) {
        boolean writeOK = true;
        Properties p = new Properties();
        InputStream in;
        try {
            in =Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);
            p.load(in);//
            in.close();
            p.setProperty(propertyName, propertyValue);//设置属性值，如不属性不存在新建
            //p.setProperty("testProperty","testPropertyValue");
            FileOutputStream out =new FileOutputStream(Thread.currentThread().getContextClassLoader().getResource(propertiesFileName).getPath());//输出流
            p.store(out, "Just Test");//设置属性头，如不想设置，请把后面一个用""替换掉
            out.flush();//清空缓存，写入磁盘
            out.close();//关闭输出流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writeOK;
    }
	/**
	 * 获取图片字节流
	 * 
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public static byte[] getByte(String uri, int TIME_OUT) throws Exception {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
		HttpGet get = new HttpGet(uri);
		get.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
		try {
			HttpResponse resonse = client.execute(get);
			if (resonse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = resonse.getEntity();
				if (entity != null) {
					return EntityUtils.toByteArray(entity);
				}
			}
		} catch (Exception e) {
		    log.info(uri);
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
		return new byte[0];
	}

    /**
     * 保存图片
     * @param picURL
     * @param PIC_DIR
     * @param TIME_OUT
     * @param pic_name
     * @return
     * @throws Exception
     */
	public String save(String picURL, String PIC_DIR, int TIME_OUT,String pic_name) throws Exception {
		String fileName ="";
		if("".equals(pic_name)||null==pic_name||pic_name.length()==0){
			fileName = picURL.substring(picURL.lastIndexOf("/")+1);
		}else{
			fileName=pic_name;
		}
		 
		if (fileName.indexOf(".") == -1) {
			fileName = fileName + ".jpg";
		}
		
		File urlDir = new File(PIC_DIR);
		if (!urlDir.exists()) {
			urlDir.mkdirs();
		}
		String filePath = urlDir + "/" + fileName;
		File file = new File(filePath);
		if (file.exists()) {
			//log.info("该文件已经存在! [" + filePath + "]");
			return "";
		}
		BufferedOutputStream out = null;
		byte[] bit = CommonUtil.getByte(picURL, TIME_OUT);
		if (bit.length > 0 && bit.length >= 10000) {
			try {
				out = new BufferedOutputStream(new FileOutputStream(filePath));
				out.write(bit);
				out.flush();
				log.info("New File Success! [" + filePath + "]");
			} finally {
				if (out != null)
					out.close();
			}
		}
		return fileName;
	}

	/**
	 * 获取系统唯一ID
	 * 
	 * @return
	 */
	public static String getUUID() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String tempId = sf.format(new Date());
		if (Long.parseLong(uid) >= Long.parseLong(tempId))
			uid = (Long.parseLong(uid) + 1) + "";
		else
			uid = tempId;

		return uid;
	}
	
	public static String getNewDirName(String dirName){
		for(int i=0;i<temp.length;i++){
			if(dirName.indexOf(temp[i])>0){
				dirName=dirName.replaceAll("\\"+temp[i], "&");
			}
		}
		return dirName;
	}

}
