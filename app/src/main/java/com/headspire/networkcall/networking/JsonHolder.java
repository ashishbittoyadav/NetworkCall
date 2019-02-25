package com.headspire.networkcall.networking;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http call operation are done in this class, in a seprate thread using Async Task.
 */
public class JsonHolder extends AsyncTask<String,Void,String> {
    private URL url;
    @Override
    protected String doInBackground(String... strings) {
        StringBuffer temp = new StringBuffer();
        try {
            url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String result;
            while ((result = bufferedReader.readLine()) != null) {
                temp.append(result);
            }
        } catch (Exception e) {
        }
        return temp.toString();
    }
}

