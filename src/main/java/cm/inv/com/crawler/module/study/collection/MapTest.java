package cm.inv.com.crawler.module.study.collection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhangtao107@126.com on 2016/10/24.
 */
public class MapTest {

    private static Logger logger= LoggerFactory.getLogger(MapTest.class);

    public static void main(String[] args) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        map.put("5","5");

        for(Map.Entry<String,String> entry:map.entrySet()){
            logger.info("key={} value={}",entry.getKey(),entry.getValue());
        }

        LinkedHashMap<String, String> linkedHashMap=new LinkedHashMap<String, String>();
        linkedHashMap.put("1","1");
        linkedHashMap.put("2","2");
        linkedHashMap.put("3","3");
        linkedHashMap.put("4","4");
        linkedHashMap.put("5","5");

        for(Map.Entry<String,String> entry:linkedHashMap.entrySet()){
            logger.info("key={} value={}",entry.getKey(),entry.getValue());
        }

        map=new TreeMap<String, String>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        map.put("6","6");
        map.put("7","7");
        map.put("8","8");
        map.put("5","5");

        for(Map.Entry<String,String> entry:map.entrySet()){
            logger.info("key={} value={}",entry.getKey(),entry.getValue());
        }
    }
}
