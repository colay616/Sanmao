package com.cheng.baseapp.bean;

/**
 * Created by wuhao on 2017/9/17.
 */

public class StockOneMinData {
    static public class StockSymbol{
        String symbol;
    }

    static public class OneMinEntry{
        int volume;
        float avg_price;
        int lot_volume;
        long timestamp;
        float current;
        String time;
    }

    public StockSymbol stock;
    public String success;
    public OneMinEntry[] chartlist;
}
