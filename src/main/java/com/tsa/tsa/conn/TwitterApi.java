package com.tsa.tsa.conn;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterApi {
    ConfigurationBuilder cb;
    TwitterFactory tf;
    Twitter twitter;

    public TwitterApi() {
        this.cb = new ConfigurationBuilder();
        this.cb.setDebugEnabled(true)
                .setOAuthConsumerKey(System.getenv("TWITTER_CONSUMER_KEY"))
                .setOAuthConsumerSecret(System.getenv("TWITTER_CONSUMER_SECRET"))
                .setOAuthAccessToken(System.getenv("TWITTER_ACCESS_TOKEN"))
                .setOAuthAccessTokenSecret(System.getenv("TWITTER_ACCESS_SECRET"));
        this.tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }

    public List<String> searchTweets(String search) {
        try {
            Query query = new Query(search);
            QueryResult result = this.twitter.search(query);
            return result.getTweets().stream()
                    .map(item -> item.getText())
                    .collect(Collectors.toList());
        } catch (TwitterException ex) {
            System.out.println("Failed to get Tweets" + ex);
            return new ArrayList<>();
        }
    }

}
