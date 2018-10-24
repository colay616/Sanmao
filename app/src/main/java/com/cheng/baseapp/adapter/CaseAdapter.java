package com.cheng.baseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheng.baseapp.R;
import com.cheng.baseapp.bean.CaseBean;
import com.cheng.baseapp.view.viewholder.BaseViewHolder;
import com.cheng.baseapp.view.viewholder.CaseHolder;

import java.util.List;

/**
 * @author LinWei on 2017/9/8 10:34
 */
public class CaseAdapter extends BaseAdapter<CaseBean> {
    public CaseAdapter(List<CaseBean> data, Context context) {
        super(data, context);
    }

    @Override
    public BaseViewHolder<CaseBean> getHolder(LayoutInflater mInflater, ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.item_case,parent,false);
        CaseHolder holder=new CaseHolder(itemView);
        holder.setOnViewHolderClickListener(mListener);
        return holder;
    }
}
