package item.com.demo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.item.sdk.global.GlideApp;

import java.util.List;

import item.com.demo.R;
import item.com.demo.bean.WalfareBean;

/**
 * Created by wuzongjie on 2018/7/16
 */
public class WelfareAdapter extends BaseQuickAdapter<WalfareBean.ResultsBean,BaseViewHolder>{

    public WelfareAdapter(int layoutResId, @Nullable List<WalfareBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    public WelfareAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalfareBean.ResultsBean item) {
        GlideApp.with(mContext)
                .load(item.getUrl())
                .centerCrop()
                .placeholder(R.drawable.img_default_meizi)
                .into((ImageView)helper.getView(R.id.iv_item_image));
    }
}
