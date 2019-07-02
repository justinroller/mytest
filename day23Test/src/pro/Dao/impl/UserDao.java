package pro.Dao.impl;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pro.Dao.IUserDao;
import pro.domain.User;
import pro.utils.JDBCUtils;

import java.util.List;

public class UserDao implements IUserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> FindUsers() {
        String sql = "select * from user";

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class));

    }

    @Override
    public int Add(User user) {
        String sql = " insert into user values(null,?,?,?,?,?,?,null,null)";
        return template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public int Del(String id) {
        //由于传入的id是字符串 所以需要转换
        int uid = Integer.parseInt(id);
        String sql = "delete from user where id = ?";
        return template.update(sql, uid);
    }

    @Override
    public User Fine(int id) {
        String sql = "select * from user where id = ?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int update(User user) {
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        return template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }
}