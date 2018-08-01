package item.com.demo.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.item.sdk.global.GlideApp;

import java.util.List;

import item.com.demo.R;
import item.com.demo.bean.MovieDetailBean;
import item.com.demo.view.activity.WebActivity;

/**
 * Created by wuzongjie on 2018/8/1
 */
public class MovieDetailAdapter extends BaseQuickAdapter<MovieDetailBean.DirectorsBean,BaseViewHolder>{

    public MovieDetailAdapter( @Nullable List<MovieDetailBean.DirectorsBean> data) {
        super(R.layout.item_detail_person, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MovieDetailBean.DirectorsBean item) {
        helper.setText(R.id.tv_person_name, item.getName());
        helper.setText(R.id.tv_person_type, item.getType());
        if(TextUtils.isEmpty(item.getAvatars().getLarge())) return;
        GlideApp.with(mContext).load(item.getAvatars().getLarge())
                .into((ImageView)helper.getView(R.id.iv_avatar_photo));
        helper.getView(R.id.ll_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebActivity.show(mContext,item.getName(),item.getAlt());
            }
        });
    }
}
