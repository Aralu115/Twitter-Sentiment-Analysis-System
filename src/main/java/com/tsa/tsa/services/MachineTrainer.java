package com.tsa.tsa.services;

import com.tsa.tsa.conn.DatabaseApi;
import org.ejml.simple.SimpleMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class MachineTrainer {

    @Autowired
    public TweetAnalyzer ta;

    @Autowired
    public DatabaseApi api;

    public static final double learningRate = 1*Math.pow(10, 2);

    public void TrainMachine(String tweet[], Double userInput){
        //set learning rate

        //receive some tokanized array of a tweet and user input
        //CallTweetAnalyzer to analyze tweet
        HashMap<String, SimpleMatrix> prediction = ta.getAllLayerValues(tweet);

        //first gather all necessary info

        //get all weight matrices WL[j][k], where  aL[j] aL-1[k]
        SimpleMatrix inputToHL1 = prediction.get("InputToHL1Weights");
        SimpleMatrix HL1ToHL2 = prediction.get("HL1ToHL2Weights");
        SimpleMatrix HL2ToOutput = prediction.get("HL2ToOutputWeights");

        //get all bias vectors bL[j]
        SimpleMatrix HL1 = prediction.get("biasVector1");
        SimpleMatrix HL2 = prediction.get("biasVector2");
        SimpleMatrix Output = prediction.get("biasVector3");

        //get cost --- note it is not squared yet --- cost = (prediction-expected)^2
        SimpleMatrix cost = new SimpleMatrix(GetCost(prediction, userInput));

        //begin building cost matrix for weights WL[j][k], where  aL[j] aL-1[k]
        SimpleMatrix HL1WCosts = new SimpleMatrix(inputToHL1.numRows(), inputToHL1.numCols());
        SimpleMatrix HL2WCosts = new SimpleMatrix(HL1ToHL2.numRows(), HL1ToHL2.numCols());
        SimpleMatrix OutputWCosts = new SimpleMatrix(HL2ToOutput.numRows(), HL2ToOutput.numCols());


        //begin building cost vector for bias's bL[j]
        SimpleMatrix HL1BCosts = new SimpleMatrix(HL1.numRows(), 1);
        SimpleMatrix HL2BCosts = new SimpleMatrix(HL2.numRows(), 1);
        SimpleMatrix OutputBCosts = new SimpleMatrix(Output.numRows(), 1);

        //Layer costs
        HashMap<String, SimpleMatrix> LayerCosts = new HashMap<>();

        //Start Backpropagation
        cost = cost.scale(2);

        //get costs from output to hl2
        OutputWCosts = getWBCosts(HL2ToOutput, Output, prediction, "HL2", cost, true);
        OutputBCosts = getWBCosts(HL2ToOutput, Output, prediction, "HL2", cost, false).cols(0, 1);
        LayerCosts.put("HL2Costs", getLayercost(HL2ToOutput, Output, prediction, "HL2", cost));
        //get costs from hl2 to hl1
        HL2WCosts = getWBCosts(HL1ToHL2, HL2, prediction, "HL1", LayerCosts.get("HL2Costs"), true);
        HL2BCosts = getWBCosts(HL1ToHL2, HL2, prediction, "HL1", LayerCosts.get("HL2Costs"), false).cols(0, 1);
        LayerCosts.put("HL1Costs", getLayercost(HL1ToHL2, HL2, prediction, "HL1", LayerCosts.get("HL2Costs")));
        //get costs from hl1 to input
        HL1WCosts = getWBCosts(inputToHL1, HL1, prediction, "InputLayer", LayerCosts.get("HL1Costs"), true);
        HL1BCosts = getWBCosts(inputToHL1, HL1, prediction, "InputLayer", LayerCosts.get("HL1Costs"), false).cols(0, 1);

        //everything is ready to be updated
        //bias's
        AdjustBias("HL1", HL1BCosts, HL1);
        AdjustBias("HL2", HL2BCosts, HL2);
        AdjustBias("Output", OutputBCosts, Output);
        //weights
        AdjustWeights("Output", OutputWCosts, HL2ToOutput, tweet);
        AdjustWeights("HL1", HL1WCosts, inputToHL1, tweet);
        AdjustWeights("HL2", HL2WCosts, HL1ToHL2, tweet);
        System.out.println("Finished Training!!!");
    }

    public SimpleMatrix getLayercost(SimpleMatrix WeightMatrix, SimpleMatrix bias, HashMap<String, SimpleMatrix> prediction, String Layer, SimpleMatrix cost) {

        SimpleMatrix z = (WeightMatrix.mult(prediction.get(Layer))).plus(bias);
        //da/dz
        SimpleMatrix dadz = getDADZ(z);
        //dz/da
        SimpleMatrix dzda = WeightMatrix;
        //dc/da is the cost previously calculated

        int j = dadz.numRows();
        int k = dzda.numCols();

        SimpleMatrix LayerCosts = new SimpleMatrix(k, 1);

        for(int x = 0; x<j; x++){
            for(int y = 0; y<k; y++){
                double m = dzda.get(x, y)*cost.get(x, 0)*dadz.get(x, 0);
                LayerCosts.set(y, 0, LayerCosts.get(y, 0)+(m));
            }
        }
        return LayerCosts;
    }

    public SimpleMatrix getWBCosts(SimpleMatrix WeightMatrix, SimpleMatrix bias, HashMap<String, SimpleMatrix> prediction, String Layer, SimpleMatrix cost, boolean b) {

        SimpleMatrix z = (WeightMatrix.mult(prediction.get(Layer))).plus(bias);
        //da/dz
        SimpleMatrix dadz = getDADZ(z);
        //dz/dw
        SimpleMatrix dzdwb = prediction.get(Layer);
        //dc/da is the cost previously calculated

        int j = dadz.numRows();
        int k = dzdwb.numRows();

        SimpleMatrix WBCosts = new SimpleMatrix(j, k);

        for(int x = 0; x<j; x++){
            for(int y = 0; y<k; y++){
                double m;
                if(b){m=dzdwb.get(y, 0);}else{m=1;}
                double placeholder = cost.get(x, 0)*dadz.get(x, 0)*m;
                WBCosts.set(x, y, placeholder);
            }
        }
        return WBCosts;
    }

    public SimpleMatrix getDADZ(SimpleMatrix z){
        double[][] SigmoidPlaceHolder = new double[z.numRows()][1];
        for(int y=0; y<z.numRows(); y++){
            SigmoidPlaceHolder[y][0] = z.get(y, 0);
            SigmoidPlaceHolder[y][0] = (Math.pow(Math.E, -1*SigmoidPlaceHolder[y][0]))/Math.pow((1+(Math.pow(Math.E, -1*SigmoidPlaceHolder[y][0]))), 2);
        }
        return new SimpleMatrix(SigmoidPlaceHolder);
    }

    public SimpleMatrix GetCost(HashMap<String, SimpleMatrix> prediction, Double userInput){
        //if 1 expecting true, if 0 expecting false, if 0.5 expecting neutral

        SimpleMatrix expectedOutput = new SimpleMatrix(2, 1);
        SimpleMatrix predictedOutput = prediction.get("OutputLayer");

        if(userInput == 1){
            expectedOutput.set(0, 0, 1);
        } else if(userInput == 0){
            expectedOutput.set(1, 0, 1);
        } else {
            double t = expectedOutput.get(0,0);
            double f = expectedOutput.get(1,0);
            expectedOutput = expectedOutput.plus((t+f)/2);
        }

        SimpleMatrix cost = predictedOutput.minus(expectedOutput);

        return cost;
    }

    public void AdjustWeights(String Layer, SimpleMatrix Costs, SimpleMatrix WeightMatrix, String[] tweet){
        //adjustweights in database
        //weight = weight + (learning_rate * cost)
        //adjustBias's
        //bias = bias + (learning_rate * cost)
        SimpleMatrix newWeightValues = (Costs.scale(learningRate)).plus(WeightMatrix);

        System.out.println("Starting " + Layer + " Weight Adjustment...");

        for(int j=0; j<newWeightValues.numRows(); j++){
            for(int k=0; k<newWeightValues.numCols(); k++) {
                switch (Layer) {
                    case "Output":
                        api.updateHiddenLayer2Weight(k+1,j+1,newWeightValues.get(j, k));
                        break;
                    case "HL1":
                        api.updateInputWeight(tweet[k],j+1, newWeightValues.get(j, k));
                        break;
                    case "HL2":
                        api.updateHiddenLayer1Weight(k+1,j+1,newWeightValues.get(j, k));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + Layer);
                }
            }
        }

        System.out.println("Finished " + Layer + " Weight Adjustment...");
    }

    public void AdjustBias(String Layer, SimpleMatrix Costs, SimpleMatrix BiasVector){
        //adjustBias's
        //bias = bias + (learning_rate * cost)
        SimpleMatrix newBiasValues = (Costs.scale(learningRate)).plus(BiasVector);

        System.out.println("Starting " + Layer + " Bias Adjustment...");

        for(int j=0; j<newBiasValues.numRows(); j++){
            switch(Layer){
                case "Output":
                    api.updateOutputLayerBias(j+1, newBiasValues.get(j, 0));
                    break;
                case "HL1":
                    api.updateHl1Bias(j+1, newBiasValues.get(j, 0));
                    break;
                case "HL2":
                    api.updateHl2Bias(j+1, newBiasValues.get(j, 0));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Layer);
            }
        }

        System.out.println("Finished " + Layer + " Bias Adjustment...");

    }

}
