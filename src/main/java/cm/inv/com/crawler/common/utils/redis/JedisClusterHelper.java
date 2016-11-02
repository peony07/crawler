package cm.inv.com.crawler.common.utils.redis;

import redis.clients.jedis.JedisCluster;

/**
 * Created by zhangtao107@126.com on 2016/11/1.
 */
public class JedisClusterHelper {


    private JedisCluster jedisCluster;
    private int timeOut;

    public JedisClusterHelper(JedisCluster jedisCluster){
        this.jedisCluster = jedisCluster;
    }

    public void setTimeOut(int timeout) {
        this.timeOut = timeout;
    }

    public void saveOrUpdate(String key,String value,int timeout) {
        jedisCluster.setex(key, timeout, value);

    }

    public void saveOrUpdate(String key,String value) {
        jedisCluster.setex(key,this.timeOut , value);
    }

    public void incr(String key){
        jedisCluster.incr(key);
    }

    public void expire(String key, int unixTime){
        jedisCluster.expire(key, unixTime);
    }

    public void setnx(String key, String value){
        jedisCluster.setnx(key, value);
    }

    public void hset(String key, String field, String value){
        jedisCluster.hset(key, field, value);
    }

    public boolean exists(String key){
        return jedisCluster.exists(key);
    }

    public String get(String key){
        return jedisCluster.get(key);
    }

    public boolean del(String key){
        boolean delFlag = false;
        jedisCluster.del(key);
        return delFlag;
    }


}
