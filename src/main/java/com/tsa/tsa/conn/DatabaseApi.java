package com.tsa.tsa.conn;

import com.tsa.tsa.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * Gather all of the weights for a specified word. {{word=ASS, word_id=1, weight_value=5.1034, hl1_id=1}}
     */
    public List<Map<String, Object>> getInputLayer(String word) {
        String query = "select distinct word.word, i.word_id, i.weight_value,	hl1.hl1_id from words as word inner join input_weights as i on word.id = i.word_id inner join hl1_weights as hl1 on hl1.hl1_id = i.hl1_id where word.word like UPPER(\"%" + word + "%\");";
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

}
