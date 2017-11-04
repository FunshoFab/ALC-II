package com.example.samth.cryptoalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CurrencyConverter extends AppCompatActivity {

    Spinner spinner;
    String currencyCode;
    double currencyValueBTC, currencyValueETH;
    TextView currencyCode_textView;
    EditText currency_input, currency_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        currency_input = (EditText) findViewById(R.id.currency_input);
        currency_result = (EditText) findViewById(R.id.currency_result);

        currencyCode = getIntent().getExtras().getString("currencyCode");
        currencyValueBTC = getIntent().getExtras().getDouble("currencyValueBTC");
        currencyValueETH = getIntent().getExtras().getDouble("currencyValueETH");

        //sets the curency title header for the activity textview
        currencyCode_textView = (TextView) findViewById(R.id.converter_currency_code);
        currencyCode_textView.setText(currencyCode);

        spinner = (Spinner) findViewById(R.id.btc_eth_spinner);
        spinnerSetUp();

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        converterUtil();
    }

    public void spinnerSetUp() {
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(spinnerAdapter);

        if (spinner.getSelectedItem().equals("BTC")) {
            Toast.makeText(this, "You're Converting to BitCoin", Toast.LENGTH_SHORT).show();
        } else if (spinner.getSelectedItem().equals("ETH")) {
            Toast.makeText(this, "You're Converting to ETHEREUM", Toast.LENGTH_SHORT).show();
        }
    }

    public void converterUtil() {

        currency_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (spinner.getSelectedItem().equals("BTC")) {
//                    currency_result.setText(s.toString());
//                }
                if (s.length() > 0 ) {
                    if (spinner.getSelectedItem().equals("BTC")) {
                        double currencyIN = Double.parseDouble(currency_input.getText().toString());
                        double result = currencyIN/currencyValueBTC;
                        DecimalFormat formatter = new DecimalFormat("0.0000000");
                        currency_result.setText(formatter.format(result) + "");

                    } else if (spinner.getSelectedItem().equals("ETH")) {
                        double currencyIN = Double.parseDouble(currency_input.getText().toString());
                        double result = currencyIN/currencyValueETH;
                        DecimalFormat formatter = new DecimalFormat("0.0000000");
                        currency_result.setText(formatter.format(result) + "");
                    }
                } else {
                    currency_result.setText("");
                }

            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
