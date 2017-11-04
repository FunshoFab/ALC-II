package com.example.samth.cryptoalc;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeContainer;
    private static final String API_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=ZAR,CHF,ZWL,YER,UYU,UGX,AUD,LRD,PKR,PLN,QAR,RUB,SAR,ARS,CAD,CNY,KPW,USD,EUR,NGN";
    ArrayList<Currency> currencyList = new ArrayList<>();
    ListView currencyListView;
    CurrencyAdapter currencyAdapter;
    public CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        currencyListView = (ListView) findViewById(R.id.listView2);
        cardView = (CardView) findViewById(R.id.cardView);

        CurrencyAsyncTask runner = new CurrencyAsyncTask();
        runner.execute(API_URL);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CurrencyAsyncTask runner = new CurrencyAsyncTask();
                runner.execute(API_URL);
            }
        });

    }




    public class CurrencyAsyncTask extends AsyncTask<String, Void, ArrayList<Currency>> {

        ProgressDialog pd;

        public CurrencyAsyncTask() {}


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(MainActivity.this, "Fetching data...", "wait for few secs" );

        }

        @Override
        protected ArrayList<Currency> doInBackground(String... url) {
            if (url == null) {
                return null;
            }

            currencyList = QueryUtils.getCurrencyList(url);
            Log.i("Main Activity", "Arraylist returned");

            return currencyList;
        }


        @Override
        protected void onPostExecute(ArrayList<Currency> arrayList) {
            pd.dismiss();
            currencyAdapter = new CurrencyAdapter(MainActivity.this, arrayList);
            currencyListView.setAdapter(currencyAdapter);
            swipeContainer.setRefreshing(false);
        }
    }

}
