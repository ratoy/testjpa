package com.example.entity.mapper;

import com.example.entity.CustomModel2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wuhao on 2017/11/9.
 */
public class CustomModelMapper  implements RowMapper {
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomModel2 orderOutVO=CustomModel2.builder()
                .brandid(rs.getInt("id"))
                .brandimg(rs.getString("brandimg"))
                .company(rs.getString("company"))
                .build();

        return orderOutVO;
    }

}
