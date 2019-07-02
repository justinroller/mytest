package pro.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pro.Dao.IUserDao;
import pro.Dao.impl.UserDao;
import pro.domain.User;
import pro.service.intService;
import pro.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class UserService implements intService {
    //通过多态生成Dao对象，调用其重写的方法
    //获得Jedis对象
    IUserDao Dao = new UserDao();
    ObjectMapper mapper  = new ObjectMapper();
    Jedis jedis = JedisUtils.getJedis();
    @Override
    public List<User> FindAll() {
        return Dao.FindUsers();
    }

    @Override
    public int AddUser(User user) {
        //由于每次添加的时候重新加载list页面的时候用的是jedis缓存的数据

        //通过判断是否添加成功 来执行删除jedis的数据
        int row=Dao.Add(user);
        if(row>0){
            //所以每次返回的时候都需要清空jedis对应的key原来的数据 让list重新通过sql获取数据
            jedis.del("All");
        }
        return row;
    }

    @Override
    public int DelUser(String id) {
        //由于每次删除的时候重新加载list页面的时候用的是jedis缓存的数据

        //通过判断是否删除成功 来执行删除jedis的数据

        int row= Dao.Del(id);
        System.out.println("被删除的行数是"+row);
        if(row>0){
            //所以每次返回的时候都需要清空jedis对应的key原来的数据 让list重新通过sql获取数据
            jedis.del("All");
        }
        return row;
    }

    @Override
    public User Find(String id) {
        int uid = Integer.parseInt(id);
        return Dao.Fine(uid);
    }

    @Override
    public int UpdateUser(User user) {
        //由于每次更新的时候重新加载list页面的时候用的是jedis缓存的数据

        //通过判断是否更新成功 来执行删除jedis的数据
        int row=Dao.update(user);
        if(row>0){
            //所以每次返回的时候都需要清空jedis对应的key原来的数据 让list重新通过sql获取数据
            jedis.del("All");
        }

        return row;
    }

    @Override
    public String FindAllbyJason() {
        //获得Jedis对象
        //获得Jedis的key All
        String all = jedis.get("All");
        //判断返回的字符串是否为空 或者长度是否0
        //如果是的话 则从sql中获取数据
        if (all == null || all.length() == 0) {
            List<User> users = Dao.FindUsers();
            //通过jason将获得的集合数据转换为字符串
            try {
                //由于接口没有抛出异常 所以通过捕获异常来完成
                all = mapper.writeValueAsString(users);
                jedis.set("All",all);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println("jedis中没有，通过sql获取");
            return  all;
        }
        System.out.println("通过jedis获取");
           return  all;
    }
}
