package item.com.demo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import item.com.demo.R;
import item.com.demo.bean.Item;

/**
 * Created by wuzongjie on 2018/8/20
 */
public class ShopAdater extends BaseQuickAdapter<Item,BaseViewHolder> {

    public ShopAdater( @Nullable List<Item> data) {
        super(R.layout.item_shop_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
        Glide.with(mContext)
                .load(item.getImage())
                .into((ImageView)helper.getView(R.id.image));
    }
}
