package cm.inv.com.crawler.module.demo.Proxy;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/25 0025.
 */
public class ProxyFilePipeline extends FilePersistentBase implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static Map<String,String> proxyMap=new HashMap<String,String>();

    /**
     * create a FilePipeline with default path"D:\yunpan\pptModel"
     */
    public ProxyFilePipeline() {
        setPath("D:\\yunpan\\proxy");
    }

    public ProxyFilePipeline(String path) {
        setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String[]> httpProxyList = resultItems.get("httpProxyList");
        if (!CollectionUtils.isEmpty(httpProxyList)) {
            for (String[] temp : httpProxyList) {
                String ip = temp[0];
                String port = temp[1];
                String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
                try {

                    if(proxyMap.containsKey(ip)) continue;

                    File file = new File(path);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String filePath = path + "proxy.txt";
                    file = new File(filePath);
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
                    printWriter.println(ip + ":" + port);
                    printWriter.close();

                    proxyMap.put(ip,port);
                } catch (IOException e) {
                    logger.warn("write file error", e);
                }
            }
        }
    }


    public List<String[]> getProxyPool() {
        List<String[]> proxyPoolList = new ArrayList<String[]>();
        try {
            //逐行读取文本
            String path = getPath();
            List<File> list=getAllFile(path);
            if (!CollectionUtils.isEmpty(list)) {
                for (File f : list) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                    for (String line = br.readLine(); line != null; line = br.readLine()) {
                        if(line.indexOf(":")>0){
                            if(!StringUtils.isEmpty(line.split(":")[1])) {
                                proxyPoolList.add(new String[]{null,null,line.split(":")[0], line.split(":")[1]});
                            }
                        }
                    }
                    br.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxyPoolList;
    }

    public List<File> getAllFile(String dirPath) {
        List list = new ArrayList<File>();
        File dir = new File(dirPath);
        if (dir.exists()) {
            if (dir.isDirectory()) {
                File[] proxyFiles = dir.listFiles();
                for (File f : proxyFiles) {
                    if (f.isDirectory()) {
                        list.addAll(getAllFile(f.getPath()));
                    } else {
                        list.add(f);
                    }
                }
                return list;
            } else {
                list.add(dir);
                return list;
            }
        } else {
            return null;
        }
    }
}
