package item.com.demo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import item.com.demo.R;
import item.com.demo.bean.Msg;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class MsgAdapter extends BaseQuickAdapter<Msg,BaseViewHolder>{
   // private List<Msg> mItems;

    public MsgAdapter(@Nullable List<Msg> data) {
        super(R.layout.item_wechat_msg, data);
       //this.mItems = data;
     //   mItems = new ArrayList<>();
    }

//    public MsgAdapter(@Nullable List<Msg> data) {
//        super(R.layout.item_wechat_msg, data);
//        this.mItems = data;
//    }

//    public void addMes (Msg bean){
//        mItems.add(bean);
//        notifyItemInserted(mItems.size() - 1);
//    }
    @Override
    protected void convert(BaseViewHolder helper, Msg item) {
       //Msg i =  mItems.get(helper.getPosition());
        helper.setText(R.id.tv_msg,item.getMessage());
    }
}
