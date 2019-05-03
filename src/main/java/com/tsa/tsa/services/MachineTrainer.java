package com.tsa.tsa.services;

import org.ejml.simple.SimpleMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class MachineTrainer {

    @Autowired
    public TweetAnalyzer ta;

    public void TrainMachine(String tweet[], Double userInput){
        //receive some tokanized array of a tweet and user input
        //CallTweetAnalyzer to analyze tweet
        HashMap<String, SimpleMatrix> sentiment = ta.getAllLayerValues(tweet);
        //call gitDiff to compare
        if(userInput == 0.5){
            SimpleMatrix TempVector = sentiment.get("OutputLayer");
            double trueValue = TempVector.get(0, 0);
            double falseValue = TempVector.get(1, 0);
            userInput = (trueValue + falseValue)/2;
        }

        sentiment = GetDiff(sentiment, userInput);
    }

    public HashMap<String, SimpleMatrix> GetDiff(HashMap<String, SimpleMatrix> sentiment, Double userInput){
        //do calculus for difference btween userinput and estimated output

        SimpleMatrix expectedOutput = new SimpleMatrix(2, 1);
        if(userInput == 1){
            expectedOutput.set(0, 0, 1);
        } else if(userInput == 0){
            expectedOutput.set(1, 0, 1);
        } else {
            expectedOutput.plus(userInput);
        }

        SimpleMatrix ActualOutput = sentiment.get("OutputLayer");

        //sentiment =

        return sentiment;
    }

    public void AdjustWeights(){
        //adjustweights in database
    }

}
