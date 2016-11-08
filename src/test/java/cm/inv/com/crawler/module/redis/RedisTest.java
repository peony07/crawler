package cm.inv.com.crawler.module.redis;

import cm.inv.com.crawler.common.utils.DateUtils;
import cm.inv.com.crawler.common.utils.JedisUtils;
import cm.inv.com.crawler.common.utils.StringUtils;
import cm.inv.com.crawler.common.utils.redis.JedisClusterHelper;
import cm.inv.com.crawler.common.test.SpringTestBase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangtao107@126.com on 2016/11/8.
 */
public class RedisTest extends SpringTestBase {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private JedisClusterHelper jedisHelper;

    @Test
    public void redisTest(){
        //redis单机测试
        String redisClustervalue= JedisUtils.get("redisClusterKey_1");
        if(StringUtils.isEmpty(redisClustervalue)){
            JedisUtils.set("redisClusterKey_1","redisClustervalue_"+ DateUtils.getDate("yyyyMMddHHmmss"),0);
        }else{
            logger.info(redisClustervalue);
        }

        //redis 集群测试
        redisClustervalue=jedisHelper.get("redisClusterKey_1");
        if(StringUtils.isEmpty(redisClustervalue)){
            jedisHelper.saveOrUpdate("redisClusterKey_1","redisClustervalue_"+ DateUtils.getDate("yyyyMMddHHmmss"));
        }else{
            logger.info(redisClustervalue);
        }
    }
}
