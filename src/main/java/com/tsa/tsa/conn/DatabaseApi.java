package com.tsa.tsa.conn;

import com.tsa.tsa.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * Get Total Number of Hl1 Neuron
     */
    public int getNumberOfHl1Neurons() {
        String query = "select count(id) as count from hl1;";
        return Integer.parseInt(selectQuery(query).get(0).get("count").toString());
    }

    /*
     * Get Total Number of Hl2 Neurons
     */
    public int getNumberOfHl2Neurons() {
        String query = "select count(id) as count from hl2;";
        return Integer.parseInt(selectQuery(query).get(0).get("count").toString());
    }

    /*
     * Gather all of the weights for a specified word. {{word=ASS, word_id=1, weight_value=5.1034, hl1_id=1}}
     */
    public List<Map<String, Object>> getInputLayer(String word) {
        String query = "select distinct word.word, i.word_id, i.weight_value,	hl1.hl1_id from words as word inner join input_weights as i on word.id = i.word_id inner join hl1_weights as hl1 on hl1.hl1_id = i.hl1_id where word.word like UPPER(\"" + word + "\");";
        return selectQuery(query);
    }

    /*
     * Gather all of the values for a specified hidden layer 1 neuron. {{hl1_id=1, weight_value=-3.7149, hl2_id=1}}
     */
    public List<Map<String, Object>> getHiddenLayer1Neuron(int neuron) {
        String query = "select distinct " +
                "hl1.hl1_id, " +
                "hl1.weight_value, " +
                "hl1.hl2_id " +
                "from hl1_weights as hl1 " +
                "where hl1.hl1_id = " + neuron + ";";
        return selectQuery(query);
    }

    /*
     * Gather all of the values for a specified hidden layer 2 neuron. {{hl2_id=1, weight_value=6.8162, output_layer_id=1}}
     */
    public List<Map<String, Object>> getHiddenLayer2Neuron(int neuron) {
        String query = "select distinct " +
                "hl2.hl2_id, " +
                "hl2.weight_value, " +
                "hl2.output_layer_id " +
                "from hl2_weights as hl2 " +
                "where hl2.hl2_id = " + neuron + ";";
        return selectQuery(query);
    }

    /*
     * Gather all of the values for the hidden layer 1. {hl1_id=500, weight_value=-0.2313, hl2_id=500}
     */
    public List<Map<String, Object>> getHiddenLayer1() {
        String query = "select distinct " +
                "hl1.hl1_id, " +
                "hl1.weight_value, " +
                "hl1.hl2_id " +
                "from hl1_weights as hl1;";
        return selectQuery(query);
    }

    /*
     * Gather all of the values for the hidden layer 2. {hl2_id=500, weight_value=0.6575, output_layer_id=2}
     */
    public List<Map<String, Object>> getHiddenLayer2() {
        String query = "select distinct " +
                "hl2.hl2_id, " +
                "hl2.weight_value, " +
                "hl2.output_layer_id " +
                "from hl2_weights as hl2;";
        return selectQuery(query);
    }

    public int getWordId(String word) {
        String query = "select id from words where word like UPPER(\""+ word + "\");";
        try {
            return Integer.parseInt(selectQuery(query).get(0).get("id").toString());
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean insertWord(String word) {
        String query = "insert into words (word) values (UPPER(\"" + word + "\"));";
        try {
            executeQuery(query);
            query = "INSERT INTO input_weights (word_id, weight_value, hl1_id) values ";
            for (int hl1 = 1; hl1 <= getNumberOfHl1Neurons(); hl1++) {
                //Defines the random number
                Double dub = BigDecimal.valueOf(Math.random() * 20 - 10).setScale(4, RoundingMode.HALF_UP).doubleValue();
                query += "(" + getWordId(word) + "," + dub + "," + hl1 + "),";
            }
            query = query.substring(0, query.length() - 1);
            query += ";";
            executeQuery(query);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean updateHiddenLayer1Weight(int hl1Neuron, int hl2neuron, double weight) {
        String query = "update hl1_weights" +
                " set weight_value = " + weight +
                " where hl1_id = " + hl1Neuron +
                " and hl2_id = " + hl2neuron + ";";
        try {
            executeQuery(query);
            return true;
        } catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean updateHiddenLayer2Weight(int hl2Neuron, int outputNeuron, double weight) {
        String query = "update hl2_weights" +
                " set weight_value = " + weight +
                " where hl2_id = " + hl2Neuron +
                " and output_layer_id = " + outputNeuron + ";";
        try {
            executeQuery(query);
            return true;
        } catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean updateInputWeight(String word, int hl1Neuron, double weight) {
        String query = "update input_weights " +
                "inner join words on words.id = input_weights.word_id " +
                "set input_weights.weight_value = " + weight +
                " where words.word like UPPER(\"" + word + "\") " +
                "and input_weights.hl1_id = " + hl1Neuron + ";";
        try {
            executeQuery(query);
            return true;
        } catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean insertInputWeight(int wordId, int hl1Neuron, double weight) {
        return false;
    }

    /*
     *
     */
    public List<Map<String, Object>> selectQuery(String SQL) {
        List<Test> tests = new ArrayList<Test>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);
        return rows;
    }

    /*
     *
     */
    public void executeQuery(String query) {
        jdbcTemplate.execute(query);
    }

    public void makeThing() {
        String query = "";
        for (int hl1 = 1; hl1 <= 500; hl1++) {
            //Defines the random number
            Double dub = BigDecimal.valueOf(Math.random() * 20 - 10).setScale(4, RoundingMode.HALF_UP).doubleValue();
            query += "(" + hl1 + "," + dub + "),";
        }
        System.out.print(query);
    }

}
