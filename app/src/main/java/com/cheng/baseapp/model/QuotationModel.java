package com.cheng.baseapp.model;

import android.content.Context;

import com.cheng.baseapp.bean.BaseBean;
import com.cheng.baseapp.bean.QuotationBean;
import com.cheng.baseapp.util.BaseUtil;
import com.cheng.baseapp.util.HttpUtil;

import java.util.List;

/**
 * @author LinWei on 2017/9/4 13:32
 */
public class QuotationModel {
    private Context context;

    public QuotationModel(Context context){
        this.context=context;

    }

    public void getQuotationData( final ResultQuotationDataListener listener){
        HttpUtil.getQuotationData(context, new HttpUtil.OnHttpListener<List<QuotationBean>>() {
            @Override
            public void onResponse(BaseBean<List<QuotationBean>> response, boolean isSuccess, Exception e) {
                if (listener!=null){
                    if (isSuccess){
                        if (!response.ret){
                            listener.ResultData(true,response.data);
                        }else {
                            BaseUtil.makeText("行情数据获取失败，"+response.forUser);
                            listener.ResultData(false,null);
                        }
                    }else {
                        listener.ResultData(false,null);
                    }
                }
            }
        });
    }

    public interface ResultQuotationDataListener{
        void ResultData(boolean isSuccess,List<QuotationBean> data);
    }


}
