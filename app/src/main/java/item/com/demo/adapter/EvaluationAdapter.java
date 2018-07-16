package item.com.demo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.item.sdk.utils.ToastUtils;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import item.com.demo.R;
import item.com.demo.bean.EvaluationBean;

/**
 * Created by wuzongjie on 2018/7/12
 * 评价页的Adapter
 */
public class EvaluationAdapter extends BaseAdapter {

    private Context context;
    private List<EvaluationBean> data;
    private LayoutInflater mInflater;

    public void setData(List<EvaluationBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public EvaluationAdapter(Context context, List<EvaluationBean> data) {
        this.context = context;
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public EvaluationBean getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_evaluation, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        EvaluationBean bean = getItem(position);
        holder.username.setText(bean.getUserName());

        ArrayList<ImageInfo> imageInfo= new ArrayList<>();
        List<String> imageDetails = bean.getImageInfo();
        if(imageDetails != null){
            for (String image : imageDetails){
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(image);
                info.setBigImageUrl(image);
                imageInfo.add(info);
            }
        }
        holder.nineGridView.setAdapter(new NineGridViewClickAdapter(context,imageInfo));
        // 下面的ListView
        if(bean.getReplyBeans() == null) {
            holder.contents.setVisibility(View.GONE);
        }else {
            holder.contents.setVisibility(View.VISIBLE);
            holder.contents.setAdapter(new CommentsAdapter(context,bean.getReplyBeans()));
        }
        return convertView;
    }

    class ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_content)
        TextView content;
        @BindView(R.id.item_name)
        TextView username;
        @BindView(R.id.item_time)
        TextView greateTime;
        @BindView(R.id.item_grade)
        RatingBar grade;
        @BindView(R.id.item_comments)
        ListView contents;
        @BindView(R.id.nineGrid)
        NineGridView nineGridView;
        private PopupWindow window;
        private PopupWindow editWindow;
        private View rootView;

        public ViewHolder(View convertView) {
            rootView = convertView;
            ButterKnife.bind(this, convertView);
        }

        @OnClick(R.id.item_more)
        public void more(View view) {
            View popupView = mInflater.inflate(R.layout.popup_reply, null);
            popupView.findViewById(R.id.favour).setOnClickListener(this);
            popupView.findViewById(R.id.comment).setOnClickListener(this);
            window = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setOutsideTouchable(true);
            window.setFocusable(true);
            window.setBackgroundDrawable(new ColorDrawable(Color.RED));
            window.setAnimationStyle(R.style.popup_more_anim);
            popupView.measure(0, 0);
            int x = -popupView.getMeasuredWidth();
            int y = -(popupView.getMeasuredHeight() + view.getHeight()) / 2;
            window.showAsDropDown(view, x, y);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.favour:
                    ToastUtils.showToast("赞+1");
                    if (window != null) window.dismiss();
                    break;
                case R.id.comment: // 评价
                    View editView = mInflater.inflate(R.layout.replay_input, null);
                    editWindow = new PopupWindow(editView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    editWindow.setOutsideTouchable(true);
                    editWindow.setFocusable(true);
                    editWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                    EditText replyEdit = (EditText) editView.findViewById(R.id.reply);
                    replyEdit.setFocusable(true);
                    replyEdit.requestFocus();
                    // 以下两句不能颠倒
                    editWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                    editWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    editWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
                    // 显示键盘
                    final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                    editWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            if (imm.isActive()) imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
                        }
                    });
                    if (window != null) window.dismiss();
                    break;
            }
        }
    }
}
