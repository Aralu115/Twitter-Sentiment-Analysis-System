package com.tsa.tsa.conn;


import com.tsa.tsa.services.TweetAnalyzer;
import com.tsa.tsa.services.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TweetAnalyzerTest {

    @Autowired
    public TweetAnalyzer analyze;

    @Test
    public void testApi() {
        String WordArmy[] = {"BAD", "BAD", "MONSTER"};
        String WordArmy2[] = {"BAD", "BAD", "QUALITY"};
        String WordArmy3[] = {"FUN", "BAD", "QUALITY"};
        analyze.AnalyzeTweet(WordArmy3);

    }
}
