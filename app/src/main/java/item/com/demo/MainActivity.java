package item.com.demo;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.item.sdk.base.activity.BaseCompatActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import item.com.demo.bean.CoinBean;
import item.com.demo.bean.LoginBean;
import item.com.demo.bean.TextBean;
import item.com.demo.net.HttpResult;
import item.com.demo.net.HttpsCallback;
import item.com.demo.net.UrlFactory;

public class MainActivity extends BaseCompatActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
            getHttp();
    }

    /**
     * 测试登录
     */
    private void getHttp(){
        OkGo.<HttpResult<LoginBean>> post(UrlFactory.LOGIN_URL)
                .params("username","18356025758")
                .params("password","123456q")
                .execute(new HttpsCallback<HttpResult<LoginBean>>() {
                    @Override
                    public void onError(String errMessage) {
                        Log.d("jiejie","---" + errMessage);
                    }

                    @Override
                    public void onSuccess(Response<HttpResult<LoginBean>> response) {
                        if(response.body().getData() !=null) {
                            Log.d("jiejie",response.body().getData().getToken());
                            getCoin(response.body().getData().getToken());
                        }else {
                            Log.d("jiejie",response.body().getCode() + "");
                        }

                    }
                });
    }

    /**
     * 获取钱包
     */
    private void getCoin(String token){
        OkGo.<HttpResult<CoinBean>> post(UrlFactory.COIN_URL)
                .headers("x-auth-token",token)
                .execute(new HttpsCallback<HttpResult<CoinBean>>() {
                    @Override
                    public void onError(String errMessage) {
                        Log.d("jiejie","---" + errMessage);
                    }

                    @Override
                    public void onSuccess(Response<HttpResult<CoinBean>> response) {
                        Log.d("jiejie","----" + response.body().getData().getAddress());
                    }
                });
    }
}
