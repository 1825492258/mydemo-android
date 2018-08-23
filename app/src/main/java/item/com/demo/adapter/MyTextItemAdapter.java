package item.com.demo.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import item.com.demo.R;
import item.com.demo.bean.MyTextItem;

/**
 * Created by wuzongjie on 2018/7/23

 */
public class MyTextItemAdapter extends BaseMultiItemQuickAdapter<MyTextItem,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在构造里面addItemType绑定type和layout的关系
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyTextItemAdapter(List<MyTextItem> data) {
        super(data);
        addItemType(MyTextItem.ONE, R.layout.adapter_news);
        addItemType(MyTextItem.TWO,R.layout.adapter_text);
        addItemType(3,R.layout.item_content_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyTextItem item) {
        switch (item.getItemType()){
            case 1:
                break;
            case 2:
                break;
            case 3:

                if(item.getItem().isEmpty()) return;
                RecyclerView recyclerView = helper.getView(R.id.spike_content_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                SpikeAdapter adapter = new SpikeAdapter(R.layout.adapter_spike,item.getItem());
                recyclerView.setAdapter(adapter);
                break;
                default:
                    break;
        }
    }
}
