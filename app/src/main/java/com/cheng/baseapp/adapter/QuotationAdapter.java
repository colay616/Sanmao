package com.cheng.baseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheng.baseapp.R;
import com.cheng.baseapp.bean.QuotationBean;
import com.cheng.baseapp.view.viewholder.BaseViewHolder;
import com.cheng.baseapp.view.viewholder.QuotationHolder;

import java.util.List;

/**
 * @author LinWei on 2017/8/31 17:57
 */
public class QuotationAdapter extends BaseAdapter<QuotationBean> {
    public QuotationAdapter(List<QuotationBean> data, Context context) {
        super(data, context);
    }

    @Override
    public BaseViewHolder<QuotationBean> getHolder(LayoutInflater mInflater, ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.item_quotation,parent,false);
        QuotationHolder holder=new QuotationHolder(itemView);
        holder.setOnViewHolderClickListener(mListener);
        return holder;

    }
}
