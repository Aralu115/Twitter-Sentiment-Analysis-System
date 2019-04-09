package com.tsa.tsa.services;

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

    public TweetService() {
        this.cb = new ConfigurationBuilder();
        this.cb.setDebugEnabled(true)
                .setOAuthConsumerKey(System.getenv("TWITTER_CONSUMER_KEY"))
                .setOAuthConsumerSecret(System.getenv("TWITTER_CONSUMER_SECRET"))
                .setOAuthAccessToken(System.getenv("TWITTER_ACCESS_TOKEN"))
                .setOAuthAccessTokenSecret(System.getenv("TWITTER_ACCESS_SECRET"));
        this.tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }

    public List<String> grabTweets(String term, String num) {
        System.out.println(num);
        int tweetNum = Integer.parseInt(num);
        try {
            Query query = new Query(term);
            query.setCount(tweetNum);
            QueryResult result = this.twitter.search(query);
            return result.getTweets().stream()
                    .map(item -> item.getText())
                    .collect(Collectors.toList());
        } catch (TwitterException ex) {
            System.out.println("Failed to get Tweets" + ex);
            return new ArrayList<>();
        }
    }

    public String getTweet() {
        List list = grabTweets("a", "1");
        return list.get(0).toString();
    }
}
