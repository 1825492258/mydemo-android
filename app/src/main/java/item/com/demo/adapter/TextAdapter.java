package item.com.demo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import item.com.demo.R;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class TextAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public TextAdapter( @Nullable List<String> data) {
        super(R.layout.adapter_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
