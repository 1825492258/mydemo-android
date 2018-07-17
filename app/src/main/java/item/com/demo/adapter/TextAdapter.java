package item.com.demo.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;
import com.item.sdk.utils.ToastUtils;

import java.util.List;

import item.com.demo.R;
import item.com.demo.view.activity.ChatActivity;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class TextAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public TextAdapter( @Nullable List<String> data) {
        super(R.layout.adapter_text, data);
    }
    private MyTextInterface textInterface;

    public void setTextInterface(MyTextInterface textInterface) {
        this.textInterface = textInterface;
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        helper.setText(R.id.content_title,item + "--------标题");
        helper.getView(R.id.right_menu_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ViewHolder.getLayoutPosition() 获取当前item的position
                if(textInterface !=null) textInterface.onOne(helper.getLayoutPosition());
                EasySwipeMenuLayout easySwipeMenuLayout = helper.getView(R.id.es);
                easySwipeMenuLayout.resetStatus();
            }
        });
        helper.getView(R.id.right_menu_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast("点击收藏");
                EasySwipeMenuLayout easySwipeMenuLayout = helper.getView(R.id.es);
                easySwipeMenuLayout.resetStatus();
            }
        });
        helper.getView(R.id.cv_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatActivity.show(mContext);
            }
        });
    }
    public interface MyTextInterface{
        void onOne(int position);
    }
}
