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
public class SpikeAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public SpikeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ((TextView)helper.getView(R.id.spike_ware_old_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.adapter_spike,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(0.33 * ToastUtils.getScreenWidthPixels(mContext))
                ,LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return view;
    }
}
