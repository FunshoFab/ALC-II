package com.example.samth.cryptoalc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Luminous on 10/26/2017.
 */

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    public CurrencyAdapter(Context context, ArrayList<Currency> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.row_details,parent, false);
        }

        Currency currency = getItem(position);

        TextView btcCurrencyTitle = (TextView) listItemView.findViewById(R.id.btc);
        btcCurrencyTitle.setText("BTC - " + currency.getBTCCurrencyCode());

//        btcCurrencyTitle.setText("BTC - " );

        TextView btcCurrencyValue = (TextView) listItemView.findViewById(R.id.btc_currency);
        btcCurrencyValue.setText(currency.getBTCCurrencyCode() + " " + currency.getBTCCurrencyValue());
//        btcCurrencyValue.setText("$");

        TextView ethCurrencyTitle = (TextView) listItemView.findViewById(R.id.eth);
        ethCurrencyTitle.setText("ETH - " + currency.getETHCurrencyCode());
//        ethCurrencyTitle.setText("ETH - " );

        TextView ethCurrencyValue = (TextView) listItemView.findViewById(R.id.eth_currency);
        ethCurrencyValue.setText( currency.getETHCurrencyCode() + " " + currency.getETHCurrencyValue());
//        ethCurrencyValue.setText("$");

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You just selected " + currency.getBTCCurrencyCode(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), CurrencyConverter.class);
                intent.putExtra("currencyCode", currency.getBTCCurrencyCode());
                intent.putExtra("currencyValueBTC", currency.getBTCCurrencyValue());
                intent.putExtra("currencyValueETH", currency.getETHCurrencyValue());
                getContext().startActivity(intent);
            }
        });

        return listItemView;

    }


}
