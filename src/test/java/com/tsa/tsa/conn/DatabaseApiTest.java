package com.tsa.tsa.conn;

import com.tsa.tsa.services.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseApiTest {

    @Autowired
    private DatabaseApi api;

    @Test
    public void testGetInputLayer() {
        List<Map<String, Object>> thing = api.getInputLayer("bad");
//       System.out.println(thing.get(0));
//        for (Map<String, Object> mapie: thing) {
//            System.out.println(mapie);
//        }
    }

//    @Test
//    public void testGetHiddenLayer1Neuron() {
//        List<Map<String, Object>> thing = api.getHiddenLayer1Neuron(1);
////        System.out.println(thing.get(0));
////        for (Map<String, Object> mapie: thing) {
////            System.out.println(mapie);
////        }
//    }

//    @Test
//    public void testGetHiddenLayer2Neuron() {
//        List<Map<String, Object>> thing = api.getHiddenLayer2Neuron(1);
////        System.out.println(thing.get(0));
////        for (Map<String, Object> mapie: thing) {
////            System.out.println(mapie);
////        }
//    }

//    @Test
//    public void testGetHiddenLayer1() {
//        List<Map<String, Object>> thing = api.getHiddenLayer1();
//        System.out.println(thing.get(249999));
//    }

//    @Test
//    public void testUpdateInputWeight() {
//        assert(api.updateInputWeight("monster", 1, 0.5));
//    }

//    @Test
//    public void testUpdateHl1Weight() {
//        assert(api.updateHiddenLayer1Weight(1, 1, 0.5));
//    }

//    @Test
//    public void testUpdateHl2Weight() {
//        assert(api.updateHiddenLayer2Weight(1, 1, 0.5));
//    }

//    @Test
//    public void testInsertWord() {
//        assert(api.insertWord("word"));
//    }

//    @Test
//    public void testGetWordId() {
//        System.out.println("----------------------- WORD ID: " + api.getWordId("word"));
//    }

//    @Test
//    public void testGetHiddenLayer2() {
//        List<Map<String, Object>> thing = api.getHiddenLayer2();
//        System.out.println(thing.get(0).size());
//    }

//    @Test
//    public void testGetHiddenLayer1Neurons() {
//        int thing = api.getNumberOfHl1Neurons();
//        System.out.println(thing);
//        thing = api.getNumberOfHl2Neurons();
//        System.out.println(thing);
//    }

//    @Test
//    public void populateDatabaseHl1Weights() {
//        String query = "";
//        for (int hl1 = 1; hl1 <= 100; hl1++) {
//            for (int hl2 = 1; hl2 <= 200; hl2++) {
//                Double dub = BigDecimal.valueOf(Math.random() * 20 - 10).setScale(4, RoundingMode.HALF_UP).doubleValue();
//                query = "INSERT INTO hl1_weights (hl1_id, weight_value, hl2_id) values (" + hl1 + "," + dub + "," + hl2 + ");";
//                api.executeQuery(query);
//            }
//        }
//    }

//    @Test
//    public void populateDatabaseHl2Weights() {
//        String query = "";
//        for (int hl2 = 1; hl2 <= 200; hl2++) {
//            for (int out = 1; out <= 2; out++) {
//                Double dub = BigDecimal.valueOf(Math.random() * 20 - 10).setScale(4, RoundingMode.HALF_UP).doubleValue();
//                query = "INSERT INTO hl2_weights (hl2_id, weight_value, output_layer_id) values (" + hl2 + "," + dub + "," + out + ");";
//                api.executeQuery(query);
//            }
//        }
//    }

//    @Test
//    public void testInsertWord() {
//        api.insertWord("FUN");
//        api.insertWord("BAD");
//        api.insertWord("QUALITY");
//    }

//    @Test
//    public void testBias() {
//        System.out.println("-----------------------------Bias 1-----------------------------------");
////        System.out.println(api.getHl1BiasList());
////        System.out.println("-----------------------------Bias 2-----------------------------------");
////        System.out.println(api.getHl2BiasList());
////        System.out.println("-----------------------------Bias 3-----------------------------------");
////        System.out.println(api.getOutputLayerBiasList());
//        api.updateHl1BiasList(1, 0.0000);
//        api.updateHl2BiasList(1,0.0000);
//        api.updateOutputLayerBiasList(1, 0.0000);
//    }
}
