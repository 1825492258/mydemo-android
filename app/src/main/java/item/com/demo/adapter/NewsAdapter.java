package item.com.demo.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.NineGridViewAdapter;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

import item.com.demo.R;
import item.com.demo.bean.GankModel;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class NewsAdapter extends BaseQuickAdapter<GankModel,BaseViewHolder>{

    public NewsAdapter(@Nullable List<GankModel> data) {
        super(R.layout.adapter_news, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankModel model) {
        helper.setText(R.id.title,model.desc)
                .setText(R.id.desc, model.desc)//
                .setText(R.id.pubDate, model.publishedAt.toString())//
                .setText(R.id.source, model.source);
        View view = helper.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        NineGridView nineGrid = helper.getView(R.id.nineGrid);
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        if (model.images != null) {
            for (String image : model.images) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(image);
                info.setBigImageUrl(image);
                imageInfo.add(info);
            }
        }
        nineGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
    }
}
