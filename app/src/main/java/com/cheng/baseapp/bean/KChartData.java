package com.cheng.baseapp.bean;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author LinWei on 2017/10/12 10:11
 */
public class KChartData {
//    public String volume;//成交量
//    public String open;//开盘价 0
//    public String high;//最高价
//    public String close;//收盘价
//    public String low;//最低价
//    public String chg;
//    public String percent;
//    public String turnrate;
//    public String ma5;
//    public String ma10;
//    public String ma20;
//    public String ma30;
//    public String dif;
//    public String dea;
//    public String macd;
//    public String lot_volume;
//    public String timestamp;
//    public Date time;



    public String volume;
    public String open;//开盘价 0
    public String high;//最高价
    public String close;//收盘价
    public String low;//最低价




    public double chg;
    public double percent;
    public double turnrate;
    public double ma5;
    public double ma10;
    public double ma20;
    public double ma30;
    public double dif;
    public double dea;
    public double macd;
    public int lot_volume;
    public long timestamp;
    public  String time;

//    public String zdf;//涨跌幅
//    public String day;//时间


    //时间表示为年月日
    public String getTimeY() {
        String tt = Formating(time);
        String num[] = tt.split(" ");
//        String num[]= time.split(" ");
        return num[0].substring(2, num[0].length());
    }

    //时间表示为时分
    public String getTimeD() {
//        String num[]= time.split(" ");
        String tt = Formating(time);
        String num[] = tt.split(" ");
        return num[1].substring(0, num[1].lastIndexOf(":"));
    }

    private static String Formating( String args) {
        Date ss = new Date();
        System.out.println("yiban" + ss);
        SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String aa = mat.format(ss.getTime());
        System.out.println(aa);
        return aa;
    }
}




