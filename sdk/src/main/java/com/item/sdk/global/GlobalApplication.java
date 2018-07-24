package com.item.sdk.global;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * 全局的Application
 */
public class GlobalApplication extends Application{

    @SuppressLint("StaticFieldLeak")
    protected static Context context;
    @SuppressLint("StaticFieldLeak")
    protected static GlobalApplication mApp;
    protected static Handler handler;
    protected static int mainThreadId;

    public static synchronized GlobalApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
        initOkGo();
    }

    /**
     * https://github.com/jeasonlzy/okhttp-OkGo/wiki/Init
     * 设置OkGo
     */
    private void initOkGo(){
        // 构建OkHttpClient.Builder
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        // log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               // log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 // 添加OkGo默认debug日志

        // 超时时间设置，默认60s
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);      // 全局的读取超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);     // 全局的写入超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);   // 全局的连接超时时间

        // 自动管理cookie（或者叫session的保持），以下几种任选其一就行
        // builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            // 使用sp保持cookie，如果cookie不过期，则一直有效
        // builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));            // 使用数据库保持cookie，如果cookie不过期，则一直有效
        // builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            // 使用内存保持cookie，app退出后，cookie消失

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           // 必须调用初始化
                .setOkHttpClient(builder.build())               // 建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               // 全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   // 全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                               // 全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
               // .addCommonHeaders(headers)                      // 全局公共头
               // .addCommonParams(params);                       // 全局公共参数
    }
    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }
}
