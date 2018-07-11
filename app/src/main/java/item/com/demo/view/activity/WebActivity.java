package item.com.demo.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    @BindView(R.id.title)
    TextView mTitle;
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

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView(Bundle savedInstanceState) {
        String url = getIntent().getStringExtra(URL);
        String title = getIntent().getStringExtra(TITLE);
        //mTitle.setText(title);
        pb.setMax(100);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if (newProgress >= 100) {
                    pb.setVisibility(View.GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }


}
