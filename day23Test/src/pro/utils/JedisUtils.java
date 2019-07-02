package pro.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtils {
    //创建静态连接池对象
  private   static JedisPool jedisPool;
    //读取配置文件
    static{
        InputStream rs = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建property对象
        Properties pro = new Properties();
        //获取连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置参数
        try {
            pro.load(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
        //给连接池配置端口和配置
        jedisPool= new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
    }
    //获取jedis对象

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
