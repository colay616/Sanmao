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
import android.widget.TextView;

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

/**交易Fragment
 * @author LinWei on 2017/8/31 14:44
 */
public class TransactionFragment extends BaseFragment {


    @BindView(R.id.rv_infomation_container)
    RecyclerView mRvContainer;
    Unbinder unbinder;

    private InfoModel mModel;
    private List<InfoBean> mData;
    private InfoAdapter mInfoAdapter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Const.STATE_DATA_SUCCESS:
                    List<InfoBean> moreData= (List<InfoBean>) msg.obj;
                    if (moreData!=null){
                        mData=moreData;
                        mInfoAdapter.notifyData(mData);
                    }
                    break;

                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_information, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        initData();
        initRecycle();
        initListener();
        return rootView;
    }

    private void init() {
        mModel=new InfoModel();
        mData=new ArrayList<>();
        mInfoAdapter=new InfoAdapter(mData,getActivity());
    }

    private void initData() {
        mModel.getInfomationData(getActivity(), new InfoModel.ResultInfoDataListener() {
            @Override
            public void ResultData(boolean isSuccess, List<InfoBean> data) {
                if (isSuccess){
                    Message message=new Message();
                    message.obj=data;
                    message.what=Const.STATE_DATA_SUCCESS;
                    mHandler.sendMessage(message);
                }
            }
        });
    }

    private void initRecycle(){
        mRvContainer.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRvContainer.setAdapter(mInfoAdapter);
    }

    private void initListener() {
        mInfoAdapter.setonViewHolderClickListener(new BaseViewHolder.OnViewHolderClickListener<InfoBean>() {
            @Override
            public void onClick(BaseViewHolder holder, InfoBean data, int position) {
                Intent intent=new Intent(getActivity(), InfoDetailActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
