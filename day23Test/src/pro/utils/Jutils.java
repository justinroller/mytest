package pro.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Jutils {
    //定义连接池
    private static JedisPool jedisPool;

    static {
        try {
            //读取配置文件
            InputStream rs = Jutils.class.getClassLoader().getResourceAsStream("jedis.properties");
            //获取properties 加载rs
            Properties pro = new Properties();
            pro.load(rs);
            //获得连接池配置对象
            JedisPoolConfig config = new JedisPoolConfig();
            //设置maxTotal=50
            //maxIdle=10
            config.setMaxTotal(Integer.parseInt(pro.getProperty("maxtotal")));
            config.setMaxIdle(Integer.parseInt(pro.getProperty("maxidle")));
            //连接池对象传入配置，ip，和端口
            jedisPool = new JedisPool(config, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
