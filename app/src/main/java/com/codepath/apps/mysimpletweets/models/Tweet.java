package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by andrj148 on 8/4/16.
 */
public class Tweet {
    private String body;
    private long unuqueID;
    private User user;
    private String createdAt;


    public String getBody() {
        return body;
    }

    public long getUnuqueID() {
        return unuqueID;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }


    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();

        try {
            tweet.body = jsonObject.getString("text");
            tweet.unuqueID = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    public static ArrayList<Tweet> fromJSONarray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();

         for (int i = 0; i < jsonArray.length(); i++) {
             try {
                 JSONObject tweetJSON = jsonArray.getJSONObject(i);
                 Tweet newTweet = Tweet.fromJSON(tweetJSON);

                 if (newTweet != null) {
                     tweets.add(newTweet);
                 }
             } catch (JSONException e) {
                 e.printStackTrace();
                 continue;
             }
         }

        return tweets;
    }

}
