package com.tsa.tsa.services;

import org.springframework.stereotype.Service;

@Service
public class TweetAnalyzer {

    public void AnalyzeTweet(){
        //take in some tokenized tweet
        //input layer value vector will have the value 1 for each neuron
        //loop through and get a map of each word --- call vectorize input to vectorize weights
        //call MapToMatrix to condense the input weight vectors into a matrix
        //call CalculateOutput to utilize the weight matrix and get the output vector
        //repeat process until output layer reached
    }

    public void MapToMatrix(){
        //condense the input vectors into a matrix

    }

    public void VectorizeInput(){
        //take map of word and create a weight vector for word
    }

    public void CalculateOutput(){
        //calculate the next layers values
    }

}
