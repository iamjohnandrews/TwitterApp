package com.codepath.apps.mysimpletweets;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
	public static final String REST_URL = "https://api.twitter.com/1.1";
	public static final String REST_CONSUMER_KEY = "Xob5YJMX68j6rM1C868gWLm1d";
	public static final String REST_CONSUMER_SECRET = "YtUqecn3JK4UuU5ATGCgdxCdLI19ewgSEg7VKbROMGcZ14GCDj";
	public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets";

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	// CHANGE THIS
	// DEFINE METHODS for different API endpoints here
	public void getInterestingnessList(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}


	public void getHomeTimeline(String fetchTag, long sinceID, AsyncHttpResponseHandler handler) {
		String apiURL = getApiUrl("statuses/home_timeline.json");
		getTimeline(apiURL, fetchTag, sinceID, handler);
	}

	public void getMentionsTimeline(String fetchTag, long sinceID, AsyncHttpResponseHandler handler) {
		String apiURL = getApiUrl("statuses/mentions_timeline.json");
		getTimeline(apiURL, fetchTag, sinceID, handler);
	}

	private void getTimeline(String apiURL, String fetchTag, long sinceID, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("count", 10);
		params.put(fetchTag, sinceID);

		getClient().get(apiURL, params, handler);
	}

	public void composeTweet(String message, JsonHttpResponseHandler handler) {
		String postAPIurl = getApiUrl("statuses/update.json");

		RequestParams params = new RequestParams();
		params.put("status", message);

		getClient().post(postAPIurl, params, handler);
	}

	public void getUserTimeline(String screenName, String fetchTag, long sinceID, AsyncHttpResponseHandler handler) {
		String apiURL = getApiUrl("statuses/user_timeline.json");

		RequestParams params = new RequestParams();
		params.put("count", 25);
		params.put("screen_name", screenName);
		params.put(fetchTag, sinceID);

		getClient().get(apiURL, params, handler);
	}

	public void getUserInfo(String email, AsyncHttpResponseHandler handler) {
		String apiURL = getApiUrl("account/verify_credentials.json");

		RequestParams params = null;
		if (email != null) {
			params = new RequestParams();
			params.put("include_email", email);
		}

		getClient().get(apiURL, params, handler);
	}
}