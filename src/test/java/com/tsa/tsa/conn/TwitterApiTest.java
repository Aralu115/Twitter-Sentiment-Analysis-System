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
        TweetService twitterApi = new TweetService();
        System.out.println("Start of the tweets");
        for (String tweet : twitterApi.grabTweets("Fortnight", "100")) {
            System.out.println("----------------------------------------");
            System.out.println(tweet);
            System.out.println("----------------------------------------");
        }
    }
}
