package com.rosehulman.android.fortune;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class WebAdapter {
    
    private static final String TAG = "Fortune.WebAdapter";
    
    private JSONObject getJSONData(String route) {

        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://myfortune.heroku.com/" + route);

        try {
            Log.d(TAG, "GET request to: http://myfortune.heroku.com/" + route);
            
            // Execute HTTP "get" request
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(get, responseHandler);
            
            Log.d(TAG, "Response: " + new JSONObject(responseBody).getJSONObject("data"));
            
            // Get the JSON object under "data"
            return new JSONObject(responseBody).getJSONObject("data");

        } catch (ClientProtocolException e) {
            Log.e(TAG, "Error in JSON (Client Protocol): " + e.getMessage());
            return new JSONObject();
        } catch (IOException e) {
            Log.e(TAG, "Error in JSON (IOException): " + e.getMessage());
            return new JSONObject();
        } catch (JSONException e) {
            Log.e(TAG, "Error in JSON (JSONException): " + e.getMessage());
            return new JSONObject();
        }
    }
    
    public String getRandomFortune() {
        JSONObject result = getJSONData("fortune/random");
        
        try {
            if (!result.getString("result").equals("success")) {
                return "SERVER RETURNED FAILURE";
            } else {
                return result.getString("fortune");
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error in JSON: " + e.getMessage());
            return "JSON ERROR!";
        }
    }

}
