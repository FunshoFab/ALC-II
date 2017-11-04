package com.example.samth.cryptoalc;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by samth on 10/25/2017.
 */

public class QueryUtils {


    public static ArrayList<Currency> currencyArrayList = new ArrayList<>();


    public QueryUtils() {

    }

    public static ArrayList getCurrencyList(String... url) {


        URL urls = convertAPItoUrl(url[0]);
//        URL urls = convertAPItoUrl(url);

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";

        currencyArrayList.clear();


        try {
            urlConnection =(HttpURLConnection) urls.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
                Log.i("URL Connection", "Connection Successful");
            }


            JSONObject mainJSONResponse = new JSONObject(jsonResponse);
            JSONObject btcObject = mainJSONResponse.getJSONObject("BTC");
            JSONObject ethObject = mainJSONResponse.getJSONObject("ETH");
            Iterator<String> btckeysIterator = btcObject.keys();
            Iterator<String> ethKeysIterator = ethObject.keys();
            while (btckeysIterator.hasNext() && ethKeysIterator.hasNext()) {

                String btcCtyCurrencyCode = btckeysIterator.next();
                double btcEquivalentCurrency = btcObject.getDouble(btcCtyCurrencyCode);

                String ethCtyCurrencyCode = ethKeysIterator.next();
                double ethEquivalentCurrency = ethObject.getDouble(ethCtyCurrencyCode);

                Log.i("Currency Check", "btc "+ btcCtyCurrencyCode
                + "\n btc Value " + btcEquivalentCurrency
                + "\n eth " + ethCtyCurrencyCode
                + "\n eth value " + ethEquivalentCurrency);

                Currency currency = new Currency(btcCtyCurrencyCode, btcEquivalentCurrency, ethCtyCurrencyCode, ethEquivalentCurrency);

                currencyArrayList.add(currency);
                Log.i("QueryUtils Activity", "Success adding to arraylist");
//                currencyArrayList.clear();

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {
            if (urlConnection != null)
                urlConnection.disconnect();

            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


        return currencyArrayList;
    }

    private static String readFromStream(InputStream inputStream) {

        StringBuilder builder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedReader.readLine();

                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();

    }

    public static URL convertAPItoUrl(String s)  {
        try {
            URL url = new URL(s);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //throw new MalformedURLException("MAlinformed Url exception with string " + s);
            return null;
        }
    }


}
