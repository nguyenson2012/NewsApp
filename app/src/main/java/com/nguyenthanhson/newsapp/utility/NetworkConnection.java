package com.nguyenthanhson.newsapp.utility;

/**
 * Created by Blej on 23/01/2016.
 */


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
public class NetworkConnection {

    private static final String TAG = NetworkConnection.class.getSimpleName();
    public static final int READ_TIME_OUT = 10000;
    public static final int CONNECTION_TIME_OUT = 15000;
    public static final String METHOD_GET = "GET";


    /**
     * Given a URL, establishes an HttpUrlConnection and retrieves
     * the web page content as a InputStream, which it returns as
     * a string.
     */
    public static String downloadUrl(String myURL) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIME_OUT /* milliseconds */);
            conn.setConnectTimeout(CONNECTION_TIME_OUT /* milliseconds */);
            conn.setRequestMethod(METHOD_GET);
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            return readStream(is);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static InputStream downloadUrlInputStream(String myURL) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIME_OUT /* milliseconds */);
            conn.setConnectTimeout(CONNECTION_TIME_OUT /* milliseconds */);
            conn.setRequestMethod(METHOD_GET);
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            return conn.getInputStream();
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * For convert InputStream to String
     *
     * @param in
     * @return
     * @throws IOException
     */
    private static String readStream(InputStream in) throws IOException {
        StringBuilder stringBuider = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuider.append(line);
            }
            return stringBuider.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Check a network is available or not
     *
     * @return true if network is available
     * false if network is not available
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
