package com.tsa.tsa.conn;

import com.tsa.tsa.services.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseApiTest {

    @Autowired
    public DatabaseApi api;

    @Test
    public void testApi() {
        String selectQuery = "Select * from hl1;";
        List<Map<String, Object>> thing = api.selectQuery(selectQuery);
        for (Map<String, Object> mapie: thing) {
            System.out.println(mapie);
        }
    }
}
