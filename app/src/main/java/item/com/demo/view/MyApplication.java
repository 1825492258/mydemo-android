package item.com.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.item.sdk.global.GlideApp;
import com.item.sdk.global.GlobalApplication;
import com.lzy.ninegrid.NineGridView;
import com.raizlabs.android.dbflow.config.FlowManager;

import item.com.demo.R;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class MyApplication extends GlobalApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化DBFlow的操作
        FlowManager.init(this);
        NineGridView.setImageLoader(new GlideImageLoader());
    }
    private class GlideImageLoader implements NineGridView.ImageLoader{

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            GlideApp.with(context).load(url)
                    .centerCrop()
                    .placeholder(R.drawable.default_portrait)
                    .error(R.drawable.default_portrait)
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}
