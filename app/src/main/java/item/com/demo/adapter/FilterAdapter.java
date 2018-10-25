package item.com.demo.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import item.com.demo.R;
import item.com.demo.bean.FilterBean;

/**
 * 提币地址
 * Created by Administrator on 2018/3/8.
 */

public class FilterAdapter extends BaseQuickAdapter<FilterBean, BaseViewHolder> {


    public FilterAdapter( @Nullable List<FilterBean> data) {
        super(R.layout.item_filter, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, FilterBean item) {
        TextView tvName = helper.getView(R.id.tvName);
        tvName.setText(item.getName());
        if (item.isSelected()) { // 选中
            tvName.setBackgroundResource(R.drawable.shape_bg_normal_corner_grey_enabled);
            tvName.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            tvName.setBackgroundResource(R.drawable.shape_bg_ripple_btn_global_option_normal_small_radius);
            tvName.setTextColor(mContext.getResources().getColor(R.color.text_normal));
        }
    }
}
