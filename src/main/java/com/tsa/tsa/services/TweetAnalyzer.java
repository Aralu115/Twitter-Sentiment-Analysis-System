package com.tsa.tsa.services;

import com.tsa.tsa.conn.DatabaseApi;
import org.ejml.simple.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class TweetAnalyzer {

    @Autowired
    public DatabaseApi api;

    public void AnalyzeTweet(String Tweet[]){
        for (String t: Tweet) {
            if (api.getWordId(t) == -1) {
                api.insertWord(t);
            }
        }
        System.out.println("Finished Checking words");
        //take in some tokenized tweet
        //input layer value vector will have the value 1 for each neuron
        //loop through and get a map of each word --- call vectorize input to vectorize weights
        //call MapToMatrix to condense the input weight vectors into a matrix
        //call CalculateOutput to utilize the weight matrix and get the output vector
        //repeat process until output layer reached
//        String[] hl = new String[500];
//        for(int x=0; x<(500); x++){
//            hl[x] = Integer.toString(x+1);
//        }
//        SimpleMatrix WeightMatrix = MapToMatrix(Tweet, "Input");
//        SimpleMatrix InputVector = new SimpleMatrix(Tweet.length, 1);
//        InputVector = InputVector.plus(1);
//        InputVector = CalculateOutput(WeightMatrix, InputVector);
//        WeightMatrix = MapToMatrix(hl, "HL1");
//        InputVector = CalculateOutput(WeightMatrix, InputVector);
//        WeightMatrix = MapToMatrix(hl, "HL2");
//        InputVector = CalculateOutput(WeightMatrix, InputVector);
//        System.out.println(InputVector);
    }

    public SimpleMatrix MapToMatrix(String[] Neurons, String Layer){
        //condense the input vectors into a matrix
        List<Map<String, Object>> WeightMap;
        int WeightsPerNeuron = getWeightsPerNeuron(Neurons, Layer);
        double[][] ArrayOfMatrix = new double[WeightsPerNeuron][Neurons.length];

        for(int index=0; index<Neurons.length; index++){

            switch(Layer){
                case "Input":
                    WeightMap = api.getInputLayer(Neurons[index]);
                    break;
                case "HL1":
                    WeightMap = api.getHiddenLayer1Neuron(Integer.parseInt(Neurons[index]));
                    break;
                case "HL2":
                    WeightMap = api.getHiddenLayer2Neuron(Integer.parseInt(Neurons[index]));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Layer);
            }
            double[] tempArray = VectorizeInputWeights(WeightMap);
            for(int y =0; y<tempArray.length; y++){
                ArrayOfMatrix[y][index] = tempArray[y];
            }
        }
        SimpleMatrix GeneratedMatrix = new SimpleMatrix(ArrayOfMatrix);
        return GeneratedMatrix;
    }

    public int getWeightsPerNeuron(String[] Neurons, String Layer){
        switch(Layer){
            case "Input":
                return VectorizeInputWeights(api.getInputLayer(Neurons[0])).length;
            case "HL1":
                return api.getHiddenLayer1Neuron(Integer.parseInt(Neurons[0])).size();
            case "HL2":
                return api.getHiddenLayer2Neuron(Integer.parseInt(Neurons[0])).size();
            default:
                throw new IllegalStateException("Unexpected value: " + Layer);
        }
    }

    public double[] VectorizeInputWeights(List<Map<String, Object>> weightMap){
        //take map of word and create a weight vector for word
        // Print values
        double[] valueVector = new double[weightMap.size()];
        int placeHolder = 0;
        for (Map<String, Object> entry : weightMap) {
            valueVector[placeHolder] = (double) entry.get("weight_value");
            placeHolder++;
        }

        return valueVector;
    }

    public SimpleMatrix CalculateOutput(SimpleMatrix weightMatrix, SimpleMatrix inputVector){
        //calculate the next layers values

        SimpleMatrix TempOutPutVector = weightMatrix.mult(inputVector);

        double[][] SigmoidPlaceHolder = new double[TempOutPutVector.numRows()][1];

        for(int y=0; y<TempOutPutVector.numRows(); y++){
            SigmoidPlaceHolder[y][0] = TempOutPutVector.get(y, 0);
            SigmoidPlaceHolder[y][0] = 1/(1+(Math.pow(Math.E, -1*Math.abs(SigmoidPlaceHolder[y][0]))));
        }

        SimpleMatrix OutPutVector = new SimpleMatrix(SigmoidPlaceHolder);

        return OutPutVector;
    }

}