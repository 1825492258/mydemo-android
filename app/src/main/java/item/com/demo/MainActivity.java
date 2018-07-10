package item.com.demo;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.item.sdk.base.activity.BaseCompatActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import item.com.demo.bean.TextBean;
import item.com.demo.net.HttpResult;
import item.com.demo.net.HttpsCallback;

public class MainActivity extends BaseCompatActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        TextView textView = findViewById(R.id.title);
//        StatusBarUtil.immersive(this);
//        StatusBarUtil.setPaddingSmart(this,textView);
//        change = findViewById(R.id.change);
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SPManagerUtils.setNightModel(MainActivity.this,
//                        !SPManagerUtils.getNightModel(MainActivity.this));
//                MainActivity.this.reload();
//            }
//        });
//       String jj =  new CacheUtils(this).getCacheSizeString();
//       change.setText(jj);
        String jj = "{\n" +
                "      \"data\" : [ {\n" +
                "        \"serialNumber\" : \"9FC4D311A324478F867C222102852C8B\",\n" +
                "        \"name\" : \"app1\",\n" +
                "        \"sysAdvertiseLocation\" : 0,\n" +
                "        \"startTime\" : \"2018-06-01 00:00:00\",\n" +
                "        \"endTime\" : \"2019-06-30 00:00:00\",\n" +
                "        \"url\" : \"https://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/06/29/12623f2a-38d9-4118-b7ef-2eddc5a64c9f.jpg\",\n" +
                "        \"linkUrl\" : \"http://focctest.io/#/login\",\n" +
                "        \"remark\" : \"\",\n" +
                "        \"status\" : 0,\n" +
                "        \"createTime\" : \"2018-06-29 05:51:44\",\n" +
                "        \"content\" : null,\n" +
                "        \"author\" : null,\n" +
                "        \"sort\" : 0\n" +
                "      } ],\n" +
                "      \"code\" : 0,\n" +
                "      \"message\" : \"SUCCESS\"\n" +
                "    }";
        Gson gson = new Gson();
        HttpResult<List<TextBean>> jjs = gson.fromJson(jj, new TypeToken<HttpResult<List<TextBean>>>() {
        }.getType());
       // HttpResult<List<TextBean>> mm = GsonUtils.get(jj);
     //   Log.d("jiejie","--" + mm.getData().get(0).getName() + "    " + jjs.getData().get(0).getLinkUrl());
        //HttpResult<String> stringHttpResult = GsonUtils.GsonToBean(jj, HttpResult<String>.class);
        //HttpResult result =  gson.fromJson(jj,HttpResult<TextBean>.class);
        //HttpResult<List<TextBean>> jjs = new Gson().fromJson(jj,HttpResult<List<TextBean>>.class);
        //Log.d("jiejie","---" + jjs.getData().get(0).getLinkUrl() + "  " + jjs.getData().get(0).getName());
    }
    private void getHttp(){
        OkGo.<HttpResult<TextBean>> get("")
                .headers("","")
                .execute(new HttpsCallback<HttpResult<TextBean>>() {
                    @Override
                    public void onError(String errMessage) {

                    }

                    @Override
                    public void onSuccess(Response<HttpResult<TextBean>> response) {

                    }
                });
    }
}
