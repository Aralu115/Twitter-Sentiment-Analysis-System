package com.tsa.tsa.conn;

import com.tsa.tsa.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> selectQuery(String SQL) {
        List<Test> tests = new ArrayList<Test>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);
        return rows;
    }

    public void executeQuery(String query) {
        jdbcTemplate.execute(query);
    }

}
