package com.tsa.tsa.services;

import com.tsa.tsa.conn.DatabaseApi;
import org.ejml.simple.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TweetAnalyzer {

    @Autowired
    public DatabaseApi api;

    public double[] AnalyzeTweet(String[] Tweet){
        //take in some tokenized tweet
        //input layer value vector will have the value 1 for each neuron
        //loop through and get a map of each word --- call vectorize input to vectorize weights
        //call MapToMatrix to condense the input weight vectors into a matrix
        //call CalculateOutput to utilize the weight matrix and get the output vector
        //repeat process until output layer reached
        String[] hl1 = new String[100];
        for(int x=0; x<(100); x++){
            hl1[x] = Integer.toString(x+1);
        }
        String[] hl2 = new String[200];
        for(int x=0; x<(200); x++){
            hl2[x] = Integer.toString(x+1);
        }
        double[][] n = VectorizeInputWeights(api.getHl1BiasList());
        SimpleMatrix biasVector1 = new SimpleMatrix(n);
        double[][] n1 = VectorizeInputWeights(api.getHl2BiasList());
        SimpleMatrix biasVector2 = new SimpleMatrix(n1);
        double[][] n2 = VectorizeInputWeights(api.getOutputLayerBiasList());
        SimpleMatrix biasVector3 = new SimpleMatrix(n2);
        SimpleMatrix WeightMatrix = MapToMatrix(Tweet, "Input");
        SimpleMatrix InputVector = new SimpleMatrix(Tweet.length, 1);
        InputVector = InputVector.plus(1);
        InputVector = CalculateOutput(WeightMatrix, InputVector, biasVector1);

        WeightMatrix = MapToMatrix(hl1, "HL1");
        InputVector = CalculateOutput(WeightMatrix, InputVector, biasVector2);

        WeightMatrix = MapToMatrix(hl2, "HL2");
        InputVector = CalculateOutput(WeightMatrix, InputVector, biasVector3);

        System.out.println(InputVector);

        double trueValue = InputVector.get(0, 0);
        double falseValue = InputVector.get(1, 0);
        double[] OutputVector = {trueValue, falseValue};
        return OutputVector;
    }

    public HashMap<String, SimpleMatrix> getAllLayerValues(String[] tweet){

        HashMap<String, SimpleMatrix> allLayers = new HashMap<>();

        String[] hl1 = new String[100];
        for(int x=0; x<(100); x++){
            hl1[x] = Integer.toString(x+1);
        }
        String[] hl2 = new String[200];
        for(int x=0; x<(200); x++){
            hl2[x] = Integer.toString(x+1);
        }
        double[][] n = VectorizeInputWeights(api.getHl1BiasList());
        SimpleMatrix biasVector1 = new SimpleMatrix(n);
        double[][] n1 = VectorizeInputWeights(api.getHl2BiasList());
        SimpleMatrix biasVector2 = new SimpleMatrix(n1);
        double[][] n2 = VectorizeInputWeights(api.getOutputLayerBiasList());
        SimpleMatrix biasVector3 = new SimpleMatrix(n2);

        SimpleMatrix WeightMatrix = MapToMatrix(tweet, "Input");
        SimpleMatrix InputVector = new SimpleMatrix(tweet.length, 1);
        InputVector = InputVector.plus(1);
        allLayers.put("InputLayer", InputVector);

        InputVector = CalculateOutput(WeightMatrix, InputVector, biasVector1);
        allLayers.put("HL1", InputVector);

        WeightMatrix = MapToMatrix(hl1, "HL1");
        InputVector = CalculateOutput(WeightMatrix, InputVector, biasVector2);
        allLayers.put("HL2", InputVector);

        WeightMatrix = MapToMatrix(hl2, "HL2");
        InputVector = CalculateOutput(WeightMatrix, InputVector, biasVector3);
        System.out.println(InputVector);
        allLayers.put("OutputLayer", InputVector);

        return allLayers;
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
            double[][] tempArray = VectorizeInputWeights(WeightMap);
            for(int y =0; y<tempArray.length; y++){
                ArrayOfMatrix[y][index] = tempArray[y][0];
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

    public double[][] VectorizeInputWeights(List<Map<String, Object>> weightMap){
        //take map of word and create a weight vector for word
        // Print values
        double[][] valueVector = new double[weightMap.size()][1];
        int placeHolder = 0;
        for (Map<String, Object> entry : weightMap) {
            if(entry.get("weight_value") != null) {
                valueVector[placeHolder][0] = (double) entry.get("weight_value");
            } else{
                valueVector[placeHolder][0] = (double) entry.get("bias");
            }
            placeHolder++;
        }

        return valueVector;
    }

    public SimpleMatrix CalculateOutput(SimpleMatrix weightMatrix, SimpleMatrix inputVector, SimpleMatrix bias){
        //calculate the next layers values
        SimpleMatrix TempOutPutVector = weightMatrix.mult(inputVector);
        TempOutPutVector = TempOutPutVector.plus(bias);
        double[][] SigmoidPlaceHolder = new double[TempOutPutVector.numRows()][1];

        for(int y=0; y<TempOutPutVector.numRows(); y++){
            SigmoidPlaceHolder[y][0] = TempOutPutVector.get(y, 0);
            SigmoidPlaceHolder[y][0] = (Math.pow(Math.E, -1*SigmoidPlaceHolder[y][0]))/(1+(Math.pow(Math.E, -1*SigmoidPlaceHolder[y][0])));
        }

        SimpleMatrix OutPutVector = new SimpleMatrix(SigmoidPlaceHolder);
        return OutPutVector;
    }

}