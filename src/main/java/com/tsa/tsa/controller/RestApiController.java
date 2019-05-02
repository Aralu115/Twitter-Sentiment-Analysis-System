package com.tsa.tsa.controller;

import com.tsa.tsa.conn.DatabaseApi;
import com.tsa.tsa.models.TS;
import com.tsa.tsa.models.Test;
import com.tsa.tsa.services.Processor;
import com.tsa.tsa.services.TweetAnalyzer;
import com.tsa.tsa.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class RestApiController {

    @Autowired
    public DatabaseApi api;

    @Autowired
    public Processor processor;

    @Autowired
    public TweetAnalyzer analyze;

    @Autowired
    public TweetService tweetService;

    @RequestMapping("/test")
    public List<Map<String, Object>> testInfo() {
        List<Map<String, Object>> tests = api.selectQuery("Select * from hl1");
        return tests;
    }

    @RequestMapping("/api/hi")
    public String hi() {
        // Developed using https://grokonez.com/frontend/angular/how-to-integrate-angular-6-springboot-2-0-restapi-springtoolsuite
        return "Hello world! This is TSA";
    }

    @RequestMapping("/api/tweets/{term}/{num}")
    public List<TS> grabTweets(@PathVariable("term") String term, @PathVariable("num") String num) {
        List<String> tweets = tweetService.grabTweets(term, num);
        List<TS> results = processor.processTweets(tweets);
        for (TS ts : results) {
            System.out.println(ts.toString());
        }
        return results;
    }

    @RequestMapping("/api/test/{tweet}/{sentiment}")
    public String testData(@PathVariable("tweet") String tweet, @PathVariable("sentiment") String sentiment) {
        System.out.println(tweet + sentiment);
        return "success!";
    }

    @RequestMapping("/api/tweet")
    public String getTweet() {
        return tweetService.getTweet();
    }
}
