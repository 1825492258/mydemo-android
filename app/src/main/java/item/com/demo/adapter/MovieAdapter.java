package item.com.demo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.item.sdk.global.GlideApp;

import java.util.List;

import item.com.demo.R;
import item.com.demo.bean.SubBean;

/**
 * Created by wuzongjie on 2018/8/1
 */
public class MovieAdapter extends BaseQuickAdapter<SubBean.SubjectsBean,BaseViewHolder>{

    public MovieAdapter(int layoutResId, @Nullable List<SubBean.SubjectsBean> data) {
        super(layoutResId, data);
        init();
    }

    public MovieAdapter(@Nullable List<SubBean.SubjectsBean> data) {
        super(data);
        init();
    }

    public MovieAdapter(int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, SubBean.SubjectsBean item) {
        helper.setText(R.id.tv_movie_title, item.getTitle());
        helper.setText(R.id.tv_movie_directors, pListToString(item.getDirectors()));
        helper.setText(R.id.tv_movie_actors, pListToStrings(item.getCasts()));
        helper.setText(R.id.tv_movie_genres, sListToString(item.getGenres()));
        helper.setText(R.id.tv_movie_rating_rate, String.valueOf(item.getRating().getAverage()));
        GlideApp.with(mContext).load(item.getImages().getLarge()).centerCrop()
                .placeholder(R.drawable.ic_default_color).into((ImageView)helper.getView(R.id.iv_moive_photo));

    }
    private void init(){
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }
    /**
     * 格式化list为字符串
     *
     * @param list 类型list
     * @return 字符串 A/B/C..
     */
    private String pListToString(List<SubBean.SubjectsBean.DirectorsBean> list) {
        String str = "";
        if (list==null||list.isEmpty())
            return str;
        for (int i = 0; i < list.size(); i++) {
            str = str + list.get(i).getName();
            if (i < list.size() - 1)
                str += " / ";
        }
        return str;
    }
    /**
     * 格式化list为字符串
     *
     * @param list 类型list
     * @return 字符串 A/B/C..
     */
    private String pListToStrings(List<SubBean.SubjectsBean.CastsBean> list) {
        String str = "";
        if (list.isEmpty())
            return str;
        for (int i = 0; i < list.size(); i++) {
            str = str + list.get(i).getName();
            if (i < list.size() - 1)
                str += " / ";
        }
        return str;
    }

    /**
     * 格式化list为字符串
     *
     * @param list 类型list
     * @return 字符串 A/B/C..
     */
    private String sListToString(List<String> list) {
        String str = "";
        if (list.size() == 0)
            return str;
        for (int i = 0; i < list.size(); i++) {
            str = str + list.get(i);
            if (i < list.size() - 1)
                str += " / ";
        }
        return str;
    }
}
