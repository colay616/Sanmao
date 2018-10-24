package com.cheng.baseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheng.baseapp.R;
import com.cheng.baseapp.bean.GoldBean;
import com.cheng.baseapp.view.viewholder.BaseViewHolder;
import com.cheng.baseapp.view.viewholder.GoldHolder;

import java.util.List;

/**
 * @author LinWei on 2017/9/8 10:34
 */
public class GoldAdapter extends BaseAdapter<GoldBean> {
    public GoldAdapter(List<GoldBean> data, Context context) {
        super(data, context);
    }

    @Override
    public BaseViewHolder<GoldBean> getHolder(LayoutInflater mInflater, ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.item_case,parent,false);
        GoldHolder holder=new GoldHolder(itemView);
        holder.setOnViewHolderClickListener(mListener);
        return holder;
    }
}
