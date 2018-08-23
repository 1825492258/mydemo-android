package item.com.demo.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.item.sdk.global.GlideApp;
import java.util.List;
import item.com.demo.R;
import item.com.demo.bean.Girl;
import item.com.demo.view.activity.ImageBrowseActivity;

/**
 * Created by wuzongjie on 2018/7/19
 */
public class MtAdapter extends BaseQuickAdapter<Girl, BaseViewHolder> {


    public MtAdapter(@Nullable List<Girl> data) {
        super(R.layout.item_welfare, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Girl item) {
        Log.d("jiejie", item.getUrl());
        ImageView imageView = helper.getView(R.id.iv_item_image);
        GlideUrl glideUrl = new GlideUrl(item.getUrl(), new LazyHeaders.Builder()
                .addHeader("Referer", item.getRefer())
                .build());
       
        GlideApp.with(mContext)
                .load(glideUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.img_default_meizi)
                .into((ImageView) helper.getView(R.id.iv_item_image));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(item.getUrl())) {
                    ImageBrowseActivity.show(mContext, item.getUrl());
                }
            }
        });
    }
}
