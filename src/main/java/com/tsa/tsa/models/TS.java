package com.tsa.tsa.models;

/*
*   This model class is used to return the tweet and sentiment back
*   to the front-end and results page
*/

public class TS {

    private int id;
    private String tweet;
    private String sentiment;

    public TS() {}

    public TS(int id, String tweet, String sentiment) {
        this.id = id;
        this.tweet = tweet;
        this.sentiment = sentiment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String toString() {
        return "Tweet: " + this.tweet + " Sentiment: " + this.sentiment + " ID: " + this.id;
    }
}
