package pro.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pro.Dao.Provincedao;
import pro.Dao.impl.ProvinceDaoImp;
import pro.domain.Province;
import pro.service.province;
import pro.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class provinceSerImp implements province {
    Provincedao dao = new ProvinceDaoImp();

    @Override
    public String FindProbyJason() {
        //通过jedis获得对应的键，进行判断
        Jedis jedis = JedisUtils.getJedis();
        String s = jedis.get("provinces");
        if (s == null || s.length() == 0) {
            //如果s不存在 则通过sql获取到集合 然后通过jason序列化 同时保存到jedis中
            List<Province> provinces = dao.Find();
            ObjectMapper mapper = new ObjectMapper();
            try {
                s = mapper.writeValueAsString(provinces);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("provinces",s);
            System.out.println("找不到资源，从sql中获取");
            return s;
        }
        System.out.println("找到资源，从jedis中获取");
        return s;
    }

    @Override
    public List<Province> FindAll() {
        return dao.Find();
    }
}
