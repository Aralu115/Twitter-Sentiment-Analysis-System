package com.tsa.tsa.services;

import org.springframework.stereotype.Service;

@Service
public class MachineTrainer {

    public void TrainMachine(String tweet[], Double userInput){
        //receive some tokanized array of a tweet and user input
        //CallTweetAnalyzer to analyze tweet
        //call gitDiff to compare
        //Call BackPropogate to adjust weights for hl2 then hl1 then input
        //done
    }

    public void BackPropogate(){
        //call tweetanalyzer vectorizer&maptomatrix to get weight matrix for adjustments
        //call adjustweights
    }

    public void GetDiff(){
        //do calculus for difference btween userinput and estimated output
    }

    public void AdjustWeights(){
        //adjustweights in database
    }

}
