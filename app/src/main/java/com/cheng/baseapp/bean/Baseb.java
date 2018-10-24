/**
 * Copyright 2018 bejson.com
 */
package com.cheng.baseapp.bean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Auto-generated: 2018-09-09 8:38:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Baseb {

    private float min=0f;
    private float max=0f;

    private Stock stock;
    private String success;
    public List<KChartData> chartlist;
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    public Stock getStock() {
        return stock;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
    public String getSuccess() {
        return success;
    }

    public void setChartlist(List<KChartData> chartlist) {
        this.chartlist = chartlist;
    }


//    public float getMin(){
//        if (chartlist!=null && chartlist.size()>0){
//            for (int i=0;i<chartlist.size();i++){
//                float num=Float.valueOf(chartlist.get(i).low);
//                if (min>num){
//                    min=num;
//                }
//                if (max<num){
//                    max=num;
//                }
//            }
//        }
//        return this.min;
//    }
//
//    public float getMax(){
//        if (chartlist!=null && chartlist.size()>0){
//            for (int i=0;i<chartlist.size();i++){
//                float num=Float.valueOf(chartlist.get(i).high);
//                if (max<num){
//                    max=num;
//                }
//            }
//        }
//        return this.max;
//    }
    private boolean isreverse;
    public List<KChartData> getK(){
        if (chartlist==null){
            chartlist=new ArrayList<>();
        }
        if (!isreverse){
            Collections.reverse(chartlist);
            isreverse=true;
        }
        return chartlist;
    }




    public List<KChartData> getChartlist() {
        return chartlist;
    }

}