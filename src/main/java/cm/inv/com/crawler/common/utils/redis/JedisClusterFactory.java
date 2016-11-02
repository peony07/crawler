package cm.inv.com.crawler.common.utils.redis;

/**
 * Created by zhangtao107@126.com on 2016/11/1.
 */

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;


public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Resource addressConfig;
    private String addressKeyPrefix ;
    private JedisCluster jedisCluster;
    private Integer timeout;
    private Integer maxRedirections;
    private GenericObjectPoolConfig config;
    private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        Set<HostAndPort> haps = this.parseHostAndPort();
        jedisCluster = new JedisCluster(haps, timeout, maxRedirections,config);
    }

    @Override
    public JedisCluster getObject() throws Exception {
        // TODO Auto-generated method stub
        return jedisCluster;
    }

    @Override
    public Class<? extends JedisCluster> getObjectType() {
        // TODO Auto-generated method stub
        return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
    }

    @Override
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setAddressConfig(Resource addressConfig) {
        this.addressConfig = addressConfig;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setMaxRedirections(int maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public void setAddressKeyPrefix(String addressKeyPrefix) {
        this.addressKeyPrefix = addressKeyPrefix;
    }

    public void setConfig(GenericObjectPoolConfig config) {
        this.config = config;
    }


    private Set<HostAndPort> parseHostAndPort() throws Exception {
        Set<HostAndPort> haps = new HashSet<HostAndPort>();
        try {
            Properties prop = new Properties();
            prop.load(this.addressConfig.getInputStream());
            for (Object key : prop.keySet()) {

                if (!((String) key).startsWith(addressKeyPrefix)) {
                    continue;
                }

                String val = (String) prop.get(key);

                boolean isIpPort = p.matcher(val).matches();

                if (!isIpPort) {
                    throw new IllegalArgumentException("ip 或 port 不合法");
                }
                String[] ipAndPort = val.split(":");

                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                haps.add(hap);
            }

        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("解析 jedis 配置文件失败",ex);
            throw new Exception("解析 jedis 配置文件失败", ex);
        }finally{
            return haps;
        }
    }

}

