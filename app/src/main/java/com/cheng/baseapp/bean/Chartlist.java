/**
 * Copyright 2018 bejson.com
 */
package com.cheng.baseapp.bean;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Auto-generated: 2018-09-09 8:38:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Chartlist {

    public long volume;
    public double open;
    private double high;
    private double close;
    private double low;
    private double chg;
    private double percent;
    private double turnrate;
    private double ma5;
    private double ma10;
    private double ma20;
    private double ma30;
    private double dif;
    private double dea;
    private double macd;
    private long lot_volume;
    private long timestamp;
    private Date time;
    public void setVolume(long volume) {
        this.volume = volume;
    }
    public long getVolume() {
        return volume;
    }

    public void setOpen(double open) {
        this.open = open;
    }
    public double getOpen() {
        return open;
    }

    public void setHigh(double high) {
        this.high = high;
    }
    public double getHigh() {
        return high;
    }

    public void setClose(double close) {
        this.close = close;
    }
    public double getClose() {
        return close;
    }

    public void setLow(double low) {
        this.low = low;
    }
    public double getLow() {
        return low;
    }

    public void setChg(double chg) {
        this.chg = chg;
    }
    public double getChg() {
        return chg;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    public double getPercent() {
        return percent;
    }

    public void setTurnrate(double turnrate) {
        this.turnrate = turnrate;
    }
    public double getTurnrate() {
        return turnrate;
    }

    public void setMa5(double ma5) {
        this.ma5 = ma5;
    }
    public double getMa5() {
        return ma5;
    }

    public void setMa10(double ma10) {
        this.ma10 = ma10;
    }
    public double getMa10() {
        return ma10;
    }

    public void setMa20(double ma20) {
        this.ma20 = ma20;
    }
    public double getMa20() {
        return ma20;
    }

    public void setMa30(double ma30) {
        this.ma30 = ma30;
    }
    public double getMa30() {
        return ma30;
    }

    public void setDif(double dif) {
        this.dif = dif;
    }
    public double getDif() {
        return dif;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }
    public double getDea() {
        return dea;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }
    public double getMacd() {
        return macd;
    }

    public void setLot_volume(long lot_volume) {
        this.lot_volume = lot_volume;
    }
    public long getLot_volume() {
        return lot_volume;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public long getTimestamp() {
        return timestamp;
    }

    public void setTime(Date time) {
        this.time = time;
    }
//    public Date getTime() {
//        return time;
//    }


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
        return num[1].substring(0, num[2].lastIndexOf(":"));
    }

    private static String Formating(Date args) {
        Date ss = new Date();
        System.out.println("yiban" + ss);
        SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aa = mat.format(ss.getTime());
        System.out.println(aa);
        return aa;
    }

}