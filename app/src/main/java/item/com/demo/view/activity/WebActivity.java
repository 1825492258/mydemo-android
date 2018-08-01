package item.com.demo.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.item.sdk.base.activity.BaseCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import item.com.demo.R;

/**
 * 这个是Web
 */
public class WebActivity extends BaseCompatActivity {

    public final static String URL = "url";
    public final static String TITLE = "title";
   @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.webView)
    WebView webView;

    public static void show(Context context, String title, String url){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    protected void initTitleBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView(Bundle savedInstanceState) {
        String url = getIntent().getStringExtra(URL);
        String title = getIntent().getStringExtra(TITLE);
        initTitleBar(toolbar,title);
        pb.setMax(100);
        webView.getSettings().setJavaScriptEnabled(true); // 设置webview支持javascript脚本
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.d("jiejie","--------" + newProgress);
                if (newProgress >= 100) {
                    pb.setVisibility(View.GONE);
                }else {
                    pb.setVisibility(View.VISIBLE);
                    pb.setProgress(newProgress); // 设置加载进度
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            // 覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               //  自动生成的方法存根
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }


}
