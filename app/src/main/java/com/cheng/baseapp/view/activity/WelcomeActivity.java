package com.cheng.baseapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.cheng.baseapp.R;
import com.cheng.baseapp.api.Const;
import com.cheng.baseapp.util.BaseUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WelcomeActivity extends AppCompatActivity {

    protected AgentWeb mAgentWeb;
    protected AgentWeb nAgentWeb;
    private LinearLayout mLinearLayout;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayout = (LinearLayout) this.findViewById(R.id.container);
        setContentView(R.layout.activity_welcome);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("https://xueqiu.com");


//        mAgentWeb.destroy();
//        nAgentWeb = AgentWeb.with(this)
//                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator()
//                .createAgentWeb()
//                .ready()
//                .go("https://xueqiu.com/stock/forchartk/stocklist.json?type=before&begin=1478620800000&end=1510126200000&_=15101" +
//                        "26200000&symbol=SH600756&period=1day&symbol=SH600756&period=1day");
//        nAgentWeb.destroy();
        //登录状态，直接进入主界面
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

//               String targetUrl="";
//               Log.i("Info","cookies:"+ AgentWebConfig.getCookiesByUrl(targetUrl="https://xueqiu.com"));
//               System.out.println(AgentWebConfig.getCookiesByUrl(targetUrl="https://xueqiu.com")+"lalalalala");
//
//
//               String tagInfo=AgentWebConfig.getCookiesByUrl(targetUrl);
//               Log.i("Info","tag:"+tagInfo);

               if (!BaseUtil.getSpBoolean(Const.IsLogin)){

//                   https://xueqiu.com
                   startActivity(new Intent(WelcomeActivity.this,ContentActivity.class));
               }else {
                   startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
               }
               finish();
           }
       },2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("Info", "onResult:" + requestCode + " onResult:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.destroy();
//        nAgentWeb.destroy();
//        mAgentWeb.getWebLifeCycle().onDestroy();
    }
}
