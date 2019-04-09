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

    private static final String SQL = "select * from people";

    public List<Test> isData() {
        List<Test> tests = new ArrayList<Test>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

        for (Map<String, Object> row : rows) {
            Test test = new Test();
            test.setId((int)row.get("id"));
            test.setFirst((String)row.get("first"));
            test.setLast((String)row.get("last"));

            tests.add(test);
        }

        return tests;
    }


}
