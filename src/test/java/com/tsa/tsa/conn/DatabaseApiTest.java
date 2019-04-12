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
    public DatabaseApi api;

    @Test
    public void testGetInputLayer() {
        List<Map<String, Object>> thing = api.getInputLayer("ass");
//        System.out.println(thing.get(0));
//        for (Map<String, Object> mapie: thing) {
//            System.out.println(mapie);
//        }
    }

    @Test
    public void testGetHiddenLayer1Neuron() {
        List<Map<String, Object>> thing = api.getHiddenLayer1Neuron(1);
//        System.out.println(thing.get(0));
//        for (Map<String, Object> mapie: thing) {
//            System.out.println(mapie);
//        }
    }

    @Test
    public void testGetHiddenLayer2Neuron() {
        List<Map<String, Object>> thing = api.getHiddenLayer2Neuron(1);
//        System.out.println(thing.get(0));
//        for (Map<String, Object> mapie: thing) {
//            System.out.println(mapie);
//        }
    }

    @Test
    public void testGetHiddenLayer1() {
        List<Map<String, Object>> thing = api.getHiddenLayer1();
//        System.out.println(thing.get(249999));
    }

    @Test
    public void testGetHiddenLayer2() {
        List<Map<String, Object>> thing = api.getHiddenLayer2();
        System.out.println(thing.get(999));
    }


//    @Test
//    public void populateDatabaseHl1Weights() {
//        String query = "";
//        for (int hl1 = 1; hl1 <= 500; hl1++) {
//            for (int hl2 = 1; hl2 <= 500; hl2++) {
//                Double dub = BigDecimal.valueOf(Math.random() * 20 - 10).setScale(4, RoundingMode.HALF_UP).doubleValue();
//                query = "INSERT INTO hl1_weights (hl1_id, weight_value, hl2_id) values (" + hl1 + "," + dub + "," + hl2 + ");";
//                api.executeQuery(query);
//            }
//        }
//    }

//    @Test
//    public void populateDatabaseHl2Weights() {
//        String query = "";
//        for (int hl2 = 1; hl2 <= 500; hl2++) {
//            for (int out = 1; out <= 2; out++) {
//                Double dub = BigDecimal.valueOf(Math.random() * 20 - 10).setScale(4, RoundingMode.HALF_UP).doubleValue();
//                query = "INSERT INTO hl2_weights (hl2_id, weight_value, output_layer_id) values (" + hl2 + "," + dub + "," + out + ");";
//                api.executeQuery(query);
//            }
//        }
//    }

//    @Test
//    public void populateDatabaseWords() {
//        api.executeQuery("insert into words (word) values (\"ASS\"), (\"DICK\"), (\"FUN\"), (\"QUALITY\");");
//        String query = "";
//        for (int word = 1; word <= 4; word++) {
//            for (int hl1 = 1; hl1 <= 500; hl1++) {
//                Double dub = BigDecimal.valueOf(Math.random() * 20 - 10).setScale(4, RoundingMode.HALF_UP).doubleValue();
//                query = "INSERT INTO input_weights (word_id, weight_value, hl1_id) values (" + word + "," + dub + "," + hl1 + ");";
//                api.executeQuery(query);
//            }
//        }
//    }

}
