package com.tsa.tsa.conn;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApiTest {

    @Test
    public void testApi() {
        TwitterApi twitterApi = new TwitterApi();
        System.out.println("Start of the tweets");
        for (String tweet : twitterApi.searchTweets("Fortnight")) {
            System.out.println("----------------------------------------");
            System.out.println(tweet);
            System.out.println("----------------------------------------");
        }
    }
}
