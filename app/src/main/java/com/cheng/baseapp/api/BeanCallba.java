package com.cheng.baseapp.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.cheng.baseapp.bean.KChartBean;
import com.cheng.baseapp.bean.Baseb;
import com.cheng.baseapp.bean.KChartBean;
import com.cheng.baseapp.bean.StockOneMinData;
import com.cheng.baseapp.view.widget.LoadingDialog;
import com.zhy.http.okhttp.callback.Callback;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by chenweiqi on 2017/1/18.
 */
public abstract class BeanCallba<T> extends Callback<T> {

    LoadingDialog dialog;

    Context context;

    boolean isShow;

    public BeanCallba(Context context) {
        this.context = context;
        dialog = new LoadingDialog(context);
    }


    public BeanCallba(Context context, boolean isShow) {
        this.context = context;
        if (isShow) {
            dialog = new LoadingDialog(context);
//            dialog.setCancelable(cancelable);
        }
    }

    public BeanCallba(Context context, String message) {
        this.context = context;
        dialog = new LoadingDialog(context);
        dialog.setText(message);
    }

    public BeanCallba(Context context, boolean cancelable, String message) {
        this.context = context;
        dialog = new LoadingDialog(context);
        dialog.setCancelable(cancelable);
        dialog.setText(message);
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if (dialog != null) {
            if (!dialog.isShowing())
                dialog.show();
        }

    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        if (dialog != null) {
            if (dialog.isShowing())
                dialog.dismiss();
        }

    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        Type type = this.getClass().getGenericSuperclass();
        String bodyString = response.body().string();
        try {
            Log.e("http", bodyString);

            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type beanType = parameterizedType.getActualTypeArguments()[0];

            if (type instanceof ParameterizedType) {

                //如果用户写了泛型，就会进入这里，否则不会执行
//                ParameterizedType parameterizedType = (ParameterizedType) type;
//                Type beanType = parameterizedType.getActualTypeArguments()[0];
                if (beanType == String.class) {
                    //如果是String类型，直接返回字符串
                    return (T) bodyString;

//                    return new Gson().fromJson(bodyString, beanType);
                } else {
                    Gson gson = new Gson();
                    KChartBean oneMinData = gson.fromJson(bodyString, KChartBean.class);
                    //如果是 Bean List Map ，则解析完后返回
                    return (T) oneMinData ;
//                    return new Gson().fromJson(bodyString, KChartBean.class);
//                    return new Gson().fromJson(bodyString, beanType);
                }
            } else {
                //默认返回字符串


//                return new Gson().fromJson(bodyString, beanType);

                return (T) bodyString;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) bodyString;
    }


}