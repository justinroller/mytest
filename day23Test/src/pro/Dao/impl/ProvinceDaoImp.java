package pro.Dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pro.Dao.Provincedao;
import pro.domain.Province;
import pro.service.province;
import pro.utils.JDBCUtils;

import java.util.List;

public class ProvinceDaoImp implements Provincedao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> Find() {
        String sql = " select * from province";
        return template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
    }
}
