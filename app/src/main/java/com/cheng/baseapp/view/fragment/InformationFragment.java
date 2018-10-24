package com.cheng.baseapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.cheng.baseapp.R;
import com.cheng.baseapp.adapter.InfoAdapter;
import com.cheng.baseapp.api.Const;
import com.cheng.baseapp.bean.InfoBean;
import com.cheng.baseapp.model.InfoModel;
import com.cheng.baseapp.view.activity.Dingli_Activity;
import com.cheng.baseapp.view.activity.InfoDetailActivity;
import com.cheng.baseapp.view.activity.IntogoldActivity;
import com.cheng.baseapp.view.activity.OutgoldActivity;
import com.cheng.baseapp.view.activity.PositionActivity;
import com.cheng.baseapp.view.activity.TransactionActivity;
import com.cheng.baseapp.view.activity.TransferActivity;
import com.cheng.baseapp.view.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author LinWei on 2017/8/31 14:44
 */
public class InformationFragment extends BaseFragment implements View.OnClickListener {




    @BindView(R.id.tv_trans_dingli)
    TextView mTv_Dingli;
    @BindView(R.id.tv_trans_tansfer)
    TextView mTv_Tansfer;
    @BindView(R.id.tv_trans_chicang)
    TextView mTv_Chicang;
    @BindView(R.id.tv_trans_transaction)
    TextView mTv_Transaction;
    @BindView(R.id.tv_trans_into)
    TextView mTv_Into;
    @BindView(R.id.tv_trans_out)
    TextView mTv_Out;
    Unbinder unbinder;
    protected AgentWeb mAgentWeb;

    private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transaction, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initListener(rootView);
        return rootView;
    }

    private void initListener(View view){
        view.findViewById(R.id.tv_trans_dingli).setOnClickListener(this);
        view.findViewById(R.id.tv_trans_tansfer).setOnClickListener(this);
        view.findViewById(R.id.tv_trans_chicang).setOnClickListener(this);
        view.findViewById(R.id.tv_trans_transaction).setOnClickListener(this);
        view.findViewById(R.id.tv_trans_into).setOnClickListener(this);
        view.findViewById(R.id.tv_trans_out).setOnClickListener(this);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_trans_dingli:

                startNewActivity(PositionActivity.class);
//                mAgentWeb = AgentWeb.with(this)
//                        .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
//                        .useDefaultIndicator()
//                        .createAgentWeb()
//                        .ready()
//                        .go("https://xueqiu.com");
//                startNewActivity(Dingli_Activity.class);
                break;
//            case R.id.tv_trans_tansfer:
//                startNewActivity(TransferActivity.class);
//                break;
//            case R.id.tv_trans_chicang:
//                startNewActivity(PositionActivity.class);
//                break;
//            case R.id.tv_trans_transaction:
//                startNewActivity(TransactionActivity.class);
//                break;
//            case R.id.tv_trans_into:
//                startNewActivity(IntogoldActivity.class);
//                break;
//            case R.id.tv_trans_out:
//                startNewActivity(OutgoldActivity.class);
//                break;
        }
    }

    private void startNewActivity(Class clazz){
        startActivity(new Intent(getActivity(),clazz));
        getActivity().overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
    }















}
