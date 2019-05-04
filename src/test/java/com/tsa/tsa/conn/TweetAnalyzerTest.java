package com.tsa.tsa.conn;


import com.tsa.tsa.services.MachineTrainer;
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

    @Autowired
    public MachineTrainer train;

    @Test
    public void testApi() {
        String WordArmy[] = {"BAD", "BAD", "MONSTER"};
        String WordArmy2[] = {"BAD", "BAD", "QUALITY"};
        String WordArmy3[] = {"FUN", "BAD", "QUALITY"};
        System.out.println(analyze.AnalyzeTweet(WordArmy3));

        double userInput1 = 0;
        double userInput2 = 0.5;
        double userInput3 = 1;
        int x=50;
//        for(int y=0; x>y;y++) {
//            System.out.println(y);
//            train.TrainMachine(WordArmy3, userInput1);
//        }

    }
}
