package item.com.demo.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.item.sdk.utils.ToastUtils;

import java.util.List;

import item.com.demo.R;

/**
 * Created by wuzongjie on 2018/7/23
 */
public class SpikeAdapters extends BaseQuickAdapter<String,BaseViewHolder>{
    private List<String> lists; // 数据源
    private int mIndex; // 表示第几页
    private int mPager = 3; // 每页显示的最大的数量

    public SpikeAdapters(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }
    public SpikeAdapters(int layoutResId, @Nullable List<String> data,int index) {
        super(layoutResId, data);
        this.lists = data;
        this.mIndex = index;
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ((TextView)helper.getView(R.id.spike_ware_old_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.spike_ware_now_price,item);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.adapter_spike,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(0.33 * ToastUtils.getScreenWidthPixels(mContext))
                ,LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return view;
    }

    /**
     * 先搬到数据的大小是否满足本页的
     */
    @Override
    public int getItemCount() {
        return lists.size() > (mIndex + 1) * mPager ?
                mPager : (lists.size() - mIndex* mPager);
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return lists.get(position + mIndex * mPager);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPager;
    }
}
