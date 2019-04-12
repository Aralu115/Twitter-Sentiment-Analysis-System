package com.tsa.tsa.conn;


import com.tsa.tsa.services.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApiTest {

    @Test
    public void testApi() {
        assert true;
    }

//    @Test
//    public void testMtyApi() {
//        String str = "";
//        for (int i = 1; i < 501; i++) {
//            str += "(1, " + Math.random() + ", " + i + "),";
//        }
//        System.out.println(str);
//    }
}
