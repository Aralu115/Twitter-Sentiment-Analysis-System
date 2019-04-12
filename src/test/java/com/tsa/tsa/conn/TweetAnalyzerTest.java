package com.tsa.tsa.conn;


import com.tsa.tsa.services.TweetAnalyzer;
import com.tsa.tsa.services.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TweetAnalyzerTest {

    @Test
    public void testApi() {

        TweetAnalyzer TweetAnalyzerTest = new TweetAnalyzer();
        String BenisArmy[] = {"ASS", "ASS", "DICK"};
        String BenisArmy2[] = {"ASS", "ASS", "QUALITY"};
        String BenisArmy3[] = {"FUN", "ASS", "QUALITY"};
        TweetAnalyzerTest.AnalyzeTweet(BenisArmy);

    }
}
