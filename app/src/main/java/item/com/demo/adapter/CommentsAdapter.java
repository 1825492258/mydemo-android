package item.com.demo.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import item.com.demo.R;
import item.com.demo.bean.EvaluationBean;

/**
 * Created by wuzongjie on 2018/7/12
 */
public class CommentsAdapter extends BaseAdapter {

    private Context context;
    private List<EvaluationBean.ReplyBean> beans;

    public CommentsAdapter(Context context, List<EvaluationBean.ReplyBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public EvaluationBean.ReplyBean getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(context,R.layout.item_comments,null);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        EvaluationBean.ReplyBean replyItem = getItem(i);
        SpannableString msp = new SpannableString(replyItem.getErReplyuser() + ":" + replyItem.getErContent());
        msp.setSpan(new ForegroundColorSpan(0xff6b8747), 0, replyItem.getErReplyuser().length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.reply.setText(msp);
        return view;
    }

    static class ViewHolder{
        @BindView(R.id.tv_reply)
        TextView reply;

        ViewHolder (View convertView){
            ButterKnife.bind(this,convertView);
        }
    }
}
