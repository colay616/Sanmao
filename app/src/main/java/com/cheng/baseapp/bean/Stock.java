package com.cheng.baseapp.bean;

import static java.lang.Math.abs;

/**
 * Created by wuhao on 2017/9/5.
 */

public class Stock {
    public String name;
    public String code;
    public String settlement;
    public String open;
    public String trade;
    public String high;
    public String low;
    public String buy;
    public String sell;
    public String volume; //need div 100
    public String amount; //need div 10000
    public String buy1_price;
    public String buy1_volume;
    public String buy2_price;
    public String buy2_volume;
    public String buy3_price;
    public String buy3_volume;
    public String buy4_price;
    public String buy4_volume;
    public String buy5_price;
    public String buy5_volume;
    public String sell1_price;
    public String sell1_volume;
    public String sell2_price;
    public String sell2_volume;
    public String sell3_price;
    public String sell3_volume;
    public String sell4_price;
    public String sell4_volume;
    public String sell5_price;
    public String sell5_volume;
    public String change;
    public int one_mins_count;
    public float[] one_mins;

    public Stock(String stockName) {
        this.name = stockName;
        this.settlement = "0.0";
        this.volume = "0";
        this.code = "CODE";
        this.one_mins_count = 0;
        this.one_mins = new float[242];
    }

    public float getOneMinsMax(){
        float temp = 0.0f;
        for(int i=0; i<this.one_mins.length; i++){
            if(this.one_mins[i] > temp){
                temp = this.one_mins[i];
            }
        }
        return temp;
    }

    public float getMaxChange(){
        float settlement = Float.valueOf(this.settlement);
        float temp = 0.0f;
        for(int i=0; i<this.one_mins.length; i++){
            if(this.one_mins[i] == 0.0f){
                continue;
            }
            if(abs(this.one_mins[i]-settlement) > temp){
                temp = abs(this.one_mins[i]-settlement) ;
            }
        }
        return temp;
    }
}

