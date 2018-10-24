package com.cheng.baseapp.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.just.agentweb.AgentWebConfig;
import com.cheng.baseapp.api.BeanCallba;
import com.cheng.baseapp.api.BeanCallback;
import com.cheng.baseapp.api.Const;
import com.cheng.baseapp.api.Path;
import com.cheng.baseapp.bean.BankBean;
import com.cheng.baseapp.bean.BaseBean;
import com.cheng.baseapp.bean.CaseBean;
import com.cheng.baseapp.bean.ContractBean;
import com.cheng.baseapp.bean.GoldBean;
import com.cheng.baseapp.bean.HintBean;
import com.cheng.baseapp.bean.InfoBean;
import com.cheng.baseapp.bean.IntoGlodeBean;
import com.cheng.baseapp.bean.KChartBean;
import com.cheng.baseapp.bean.LoginBean;
import com.cheng.baseapp.bean.MoneyBean;
import com.cheng.baseapp.bean.PersonInfoBean;
import com.cheng.baseapp.bean.QuotationBean;
import com.cheng.baseapp.bean.StockOneMinData;
import com.cheng.baseapp.bean.TimeBean;
import com.cheng.baseapp.bean.TodayBean;
import com.cheng.baseapp.bean.TransactionBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author LinWei on 2017/8/30 11:04
 */
public class HttpUtil {

    public static void Login(Context context,String username,String pwd, final OnHttpListener<LoginBean> listener){
        OkHttpUtils
                .post()
                .url(Path.URL_Login)
                .addParams("username",username)
                .addParams("pwd",pwd)
                .build()
                .execute(new BeanCallback<BaseBean<LoginBean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<LoginBean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }


    /**
     * 获取银行数据
     * @param context
     * @param listener
     */
    public static void GetBankData(Context context,final OnHttpListener<List<BankBean>> listener){
        OkHttpUtils
                .get()
                .url(Path.URL_Bank)
                .build()
                .execute(new BeanCallback<BaseBean<List<BankBean>>>(context){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<List<BankBean>> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 上传合同文件 也可用于修改头像
     * @param context
     * @param file
     * @param listener
     */
    public static void UploadContract(Context context, File file,boolean Isimg, final OnHttpListener<ContractBean> listener){
        if (Isimg){
            OkHttpUtils
                    .post()
                    .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                    .addFile("userImg",file.getName(),file)
                    .url(Path.URL_SwitchIMG)
                    .build()
                    .execute(new BeanCallback<BaseBean<ContractBean>>(context) {
                                 @Override
                                 public void onError(Call call, Exception e, int id) {
                                     listener.onResponse(null,false,null);
                                 }

                                 @Override
                                 public void onResponse(BaseBean<ContractBean> response, int id) {
                                     listener.onResponse(response,true,null);
                                 }


                             }
                    );
        }else {
            OkHttpUtils
                    .post()
                    .addFile("file",file.getName(),file)
                    .url(Path.URL_UpdataCtract)
                    .build()
                    .execute(new BeanCallback<BaseBean<ContractBean>>(context) {
                                 @Override
                                 public void onError(Call call, Exception e, int id) {
                                     listener.onResponse(null,false,null);
                                 }

                                 @Override
                                 public void onResponse(BaseBean<ContractBean> response, int id) {
                                     listener.onResponse(response,true,null);
                                 }


                             }
                    );
        }


    }

    /**
     * 注册新用户
     * @param context
     * @param user
     * @param pwd
     * @param name
     * @param mobile
     * @param agent
     * @param idCard
     * @param idCarAddress
     * @param bankName
     * @param bankBranch
     * @param bankNum
     * @param contract
     * @param sex
     * @param listener
     */
    public static void RegisterNewUser(Context context,String user,String pwd,String name,String mobile,String agent,String idCard,String idCarAddress,String bankName,String bankBranch,String bankNum,String contract,String sex,final OnHttpListener<Boolean> listener){
        OkHttpUtils
                .post()
                .addParams("username",user)//账号
                .addParams("pwd",pwd)//密码
                .addParams("repwd",pwd)//密码
                .addParams("name",name)//姓名
                .addParams("sex",sex)//性别
                .addParams("mobile",mobile)//手机号
                .addParams("agent",agent)//代理商
                .addParams("idCard",idCard)//身份证
                .addParams("idCarAddress",idCarAddress)//身份证地址
                .addParams("bankName",bankName)//银行
                .addParams("bankBranch",bankBranch)//支行
                .addParams("bankNum",bankNum)//银行号
                .addParams("contract",contract)//合同
                .url(Path.URL_Register)
                .build()
                .execute(new BeanCallback<BaseBean<Boolean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<Boolean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });


    }

    /**
     *
     * @param context
     * @param contentType
     * @param listener
     */
    public static void getHint(Context context, String contentType, final OnHttpListener<HintBean> listener){
        OkHttpUtils
                .get()
                .url(Path.URL_Hint)
                .addParams("contentType",contentType)
                .build()
                .execute(new BeanCallback<BaseBean<HintBean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<HintBean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 获取行情数据
     * @param context
//     * @param goodsType
     * @param listener
     */
    public static void getQuotationData(Context context,  final OnHttpListener<List<QuotationBean>> listener){
        OkHttpUtils
                .get()
                .url(Path.URL_Quotation)
//                .addParams("goodsType",goodsType)
                .build()
                .execute(new BeanCallback<BaseBean<List<QuotationBean>>>(context,false) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<List<QuotationBean>> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 获取个人信息
     * @param context
     * @param listener
     */
    public static void getPersonInfo(Context context, final OnHttpListener<PersonInfoBean> listener){
        OkHttpUtils
                .get()
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .url(Path.URL_PersonInfo)
                .build()
                .execute(new BeanCallback<BaseBean<PersonInfoBean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<PersonInfoBean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }




    /**
     * 获取个人订单数据
     * 1代表是已买入（包括正在挂单的）的订立，2（成功买入的订立），3代表转让挂单列表
     * @param context
     * @param listener
     */
    public static void getCaseData(Context context,String type, final OnHttpListener<List<CaseBean>> listener){
        OkHttpUtils
                .get()
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams(Const.CaseType,type)
                .url(Path.URL_CaseData)
                .build()
                .execute(new BeanCallback<BaseBean<List<CaseBean>>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<List<CaseBean>> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 获取商品编码列表和可用资金
     * @param context
     * @param listener
     */
    public static void getUseMoney(Context context, final OnHttpListener<MoneyBean> listener){
        OkHttpUtils
                .get()
                .url(Path.URL_UseMoney)
                .addParams(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .build()
                .execute(new BeanCallback<BaseBean<MoneyBean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<MoneyBean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 创建新的订单
     * @param context
     * @param goodsId 商品编号ID
     * @param orderType 订单类型
     * @param price 价格
     * @param num 数量
     */
    public static void CreateCaseData(Context context, String goodsId, String orderType, String price, final String num, final OnHttpListener<Boolean> listener){
        OkHttpUtils
                .post()
                .url(Path.URL_CreateCase)
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams("goodsId",goodsId)
                .addParams("orderType",orderType)
                .addParams("price",price)
                .addParams("num",num)
                .build()
                .execute(new BeanCallback<BaseBean<Boolean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<Boolean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 撤销订立1/转让2订单
     * @param context
     * @param type
     * @param orderNo
     * @param listener
     */
    public static void RevokeCase(Context context, String type, String orderNo, final OnHttpListener<Boolean> listener){
        OkHttpUtils
                .post()
                .url(Path.URL_RevokeCase)
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams("type",type)
                .addParams("orderNo",orderNo)
                .build()
                .execute(new BeanCallback<BaseBean<Boolean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<Boolean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 转让订单
     * @param context
     * @param orderNo
     * @param sellPrice
     * @param listener
     */
    public static void TransferCase(Context context, String orderNo, String sellPrice, final OnHttpListener<Boolean> listener){
        OkHttpUtils
                .post()
                .url(Path.URL_TransferCase)
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams("orderNo",orderNo)
                .addParams("sellPrice",sellPrice)
                .build()
                .execute(new BeanCallback<BaseBean<Boolean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<Boolean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 交易流水
     * @param context
     * @param searchTime
     * @param endTime
     * @param listener
     */
    public static void getFlowingWaterData(Context context, String searchTime, String endTime, final OnHttpListener<List<TransactionBean>> listener){
        OkHttpUtils
                .get()
                .url(Path.URL_FwData)
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams("searchTime",searchTime)
                .addParams("endTime",endTime)
                .build()
                .execute(new BeanCallback<BaseBean<List<TransactionBean>>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<List<TransactionBean>> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 获取出入金
     * @param context
     * @param type 1入金 2出金
     * @param lastId  最后一条的id
     * @param isAll 0搜索15 1全部
     * @param listener
     */
    public static void getGoldData(Context context,String type, String lastId, String isAll, final OnHttpListener<List<GoldBean>> listener){
        OkHttpUtils
                .get()
                .url(Path.URL_GoldData)
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams("type",type)
                .addParams("lastId",lastId)
                .addParams("isAll",isAll)
                .build()
                .execute(new BeanCallback<BaseBean<List<GoldBean>>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<List<GoldBean>> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 获取商品资讯界面数据列表
     * @param context
     * @param listener
     */
    public static void getInfomationData(Context context, final OnHttpListener<List<InfoBean>> listener){
        OkHttpUtils
                .get()
                .url(Path.URL_InfoData)
                .build()
                .execute(new BeanCallback<BaseBean<List<InfoBean>>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<List<InfoBean>> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 修改密码
     * @param context
     * @param oldPwd
     * @param pwd
     * @param repwd
     * @param listener
     */
    public static void ModifyPassWord(Context context,String oldPwd,String pwd,String repwd,final OnHttpListener<Boolean> listener){
        OkHttpUtils
                .post()
                .url(Path.URL_MODIFYPWD)
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams("oldPwd",oldPwd)
                .addParams("pwd",pwd)
                .addParams("repwd",repwd)
                .build()
                .execute(new BeanCallback<BaseBean<Boolean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<Boolean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 入金的操作
     * @param cash 金额
     * @param bankCode 入金的银行ID
     * @param pwd 密码
     */
    public static void IntoGlodePost(Context context,String cash,String bankCode,String pwd,final OnHttpListener<IntoGlodeBean> listener){
        OkHttpUtils
                .post()
                .url(Path.URL_IntoGold)
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams("cash",cash)
                .addParams("bakCode",bankCode)
                .addParams("pwd",pwd)
                .build()
                .execute(new BeanCallback<BaseBean<IntoGlodeBean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<IntoGlodeBean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 出金操作
     * @param context
     * @param cash 金额
     * @param pwd  密码
     * @param listener
     */
    public static void OutGlodePost(Context context,String cash,String pwd,final OnHttpListener<Boolean> listener){
        OkHttpUtils
                .post()
                .url(Path.URL_OutGold)
                .addHeader(Const.TokenSession,BaseUtil.getSpString(Const.TokenSession))
                .addParams("cash",cash)
                .addParams("pwd",pwd)
                .build()
                .execute(new BeanCallback<BaseBean<Boolean>>(context) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<Boolean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }


    /**
     * 获取K线图数据
     * @param context
//     * @param goodsId
//     * @param type  1:日线 2:分钟线
     * @param listener
     */
    public static void getKData(Context context,String symbol,String period,final OnkListener listener){
        OkHttpUtils
                .get()
                .url(Path.URL_KChart)
//                .addParams("goodsId",goodsId)
//                .addParams("type",type)
                .addParams("symbol",symbol)
//                .addParams("scale",scale)
                .addParams("period",period)
                .addHeader("Cookie", AgentWebConfig.getCookiesByUrl("https://xueqiu.com"))
                .build()
                .execute(new BeanCallba<KChartBean>(context,true) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(KChartBean response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }
//    public static void getKDa(Context context,String symbol,String period){
//
//        OkHttpUtils
//                .get()
//                .url(Path.URL_KChart)
////                .addParams("goodsId",goodsId)
////                .addParams("type",type)
//                .addParams("symbol",symbol)
////                .addParams("scale",scale)
//                .addParams("period",period)
//                .addHeader("Cookie", AgentWebConfig.getCookiesByUrl("https://xueqiu.com"))
//                .build()
//                .execute(new Callback);
//    }


    public StockOneMinData getOneMinData(String symbol){
        try{
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.cookieJar(new CookieJar() {
//                private final PersistentCookieStore cookieStore = new PersistentCookieStore(getApplicationContext());
//
//                @Override
//                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                    if (cookies != null && cookies.size() > 0) {
//                        for (Cookie item : cookies) {
//                            cookieStore.add(url, item);
//                        }
//                    }
//                }
//
//                @Override
//                public List<Cookie> loadForRequest(HttpUrl url) {
//                    List<Cookie> cookies = cookieStore.get(url);
//                    return cookies;
//                }
//            });

            OkHttpClient client = builder.build();
            client.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
            client.newBuilder().readTimeout(10, TimeUnit.SECONDS);
            //Query 1 min data for a stock
            String urlString = String.format("https://xueqiu.com/stock/forchart/" +
                    "stocklist.json?symbol=%s&period=1d&one_min=1", symbol);
            Request request = new Request.Builder().url(urlString).build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200) {
                Request request1 = new Request.Builder().url("http://xueqiu.com").build();
                Response response1 = client.newCall(request1).execute();
                String resp1Data = response1.body().string();
                response = client.newCall(request).execute();
            }

            String responseData = response.body().string();
            //StockOneMinData oneMinData = new StockOneMinData();

            //parse
            Gson gson = new Gson();
            StockOneMinData oneMinData = gson.fromJson(responseData, StockOneMinData.class);
            Log.d("Gson:", oneMinData.success);
            return oneMinData;
        } catch (IOException e) {
            Log.d("abc", "abc");
            return null;
        }
    }

    /**
     * 行情当日定理/转让详情
     * @param context
     * @param goodsId 查询订单的ID
     * @param count 查询订单的列表的数量，默认20
     * @param listener
     */
    public static void getTodayData(Context context,String goodsId,String count,final OnHttpListener<TodayBean> listener){
        OkHttpUtils
                .get()
                .url(Path.URL_TodayData)
                .addParams("goodsId",goodsId)
                .addParams("count",count)
                .build()
                .execute(new BeanCallback<BaseBean<TodayBean>>(context,false) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(BaseBean<TodayBean> response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }

    /**
     * 获取分时图信息
     * @param context
     * @param goodsCode
     * @param goodsId
     * @param listener
     */
    public static void getFenshiData(Context context, String goodsCode, String goodsId, final OnFenshiListener listener){
        OkHttpUtils
                .get()
                .url(Path.URL_FenShiChart)
                .addParams("goodsId",goodsId)
                .addParams("goodsCode",goodsCode)
                .build()
                .execute(new BeanCallback<TimeBean>(context,false) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                       listener.onResponse(null,false,e);
                    }

                    @Override
                    public void onResponse(TimeBean response, int id) {
                        listener.onResponse(response,true,null);
                    }
                });
    }


    public interface OnHttpListener<T>{
        void onResponse(BaseBean<T> response,boolean isSuccess,Exception e);
    }

    public interface OnFenshiListener{
        void onResponse(TimeBean response,boolean isSuccess,Exception e);
    }
   public interface OnkListener{
        void onResponse(KChartBean response,boolean isSuccess,Exception e);
    }





}
