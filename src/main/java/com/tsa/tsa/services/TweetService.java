package com.tsa.tsa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {

    ConfigurationBuilder cb;
    TwitterFactory tf;
    Twitter twitter;

    @Autowired
    private Processor processor;

    public ArrayList<String> terms = new ArrayList<>();
    public int testDataPosition = 0;

    public TweetService() {
        this.cb = new ConfigurationBuilder();
        this.cb.setDebugEnabled(true)
                .setOAuthConsumerKey(System.getenv("TWITTER_CONSUMER_KEY"))
                .setOAuthConsumerSecret(System.getenv("TWITTER_CONSUMER_SECRET"))
                .setOAuthAccessToken(System.getenv("TWITTER_ACCESS_TOKEN"))
                .setOAuthAccessTokenSecret(System.getenv("TWITTER_ACCESS_SECRET"));
        this.tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
        terms.add("Apple");
        terms.add("Tesla");
        terms.add("Sony");
        terms.add("Toyota");
        terms.add("Trump");
    }

    public List<String> grabTweets(String term, String num) {
        int tweetNum = Integer.parseInt(num);
        try {
            Query query = new Query(term);
            query.setCount(tweetNum);
            QueryResult result = this.twitter.search(query);
            System.out.println(result.getCount());
            return result.getTweets().stream()
                    .map(item -> processor.processTweet(item.getText()))
                    .collect(Collectors.toList());
        } catch (TwitterException ex) {
            System.out.println("Failed to get Tweets" + ex);
            return new ArrayList<>();
        }
    }

    public String getTweet() {
        List<String> list = grabTweets(terms.get(testDataPosition), "1");
        if (testDataPosition == 4) {testDataPosition = 0;} else {testDataPosition++;}
        return list.get(0);
    }
}
