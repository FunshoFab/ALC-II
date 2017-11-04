package com.example.samth.cryptoalc;

/**
 * Created by Luminous on 10/25/2017.
 */

public class Currency  {

    private String mbtcCurrencyCode;
    private double mbtcCurrencyValue;

    private String mEthCurrencyCode;
    private double mEThCurrencyValue;

    public Currency(String btcCurrencyCode, double btcCurrencyValue, String ethCurrencyCode, double ethCurrencyValue) {
        this.mbtcCurrencyCode = btcCurrencyCode;
        this.mbtcCurrencyValue = btcCurrencyValue;

        this.mEthCurrencyCode = ethCurrencyCode;
        this.mEThCurrencyValue = ethCurrencyValue;
    }

    public String getBTCCurrencyCode() {
        return mbtcCurrencyCode;
    }

    public double getBTCCurrencyValue() {
        return mbtcCurrencyValue;
    }

    public String getETHCurrencyCode() {
        return mEthCurrencyCode;
    }

    public double getETHCurrencyValue() {
        return mEThCurrencyValue;
    }
}
