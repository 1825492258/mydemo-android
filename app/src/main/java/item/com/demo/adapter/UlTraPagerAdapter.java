package item.com.demo.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.item.sdk.global.GlideApp;

import java.util.List;

import item.com.demo.R;

/**
 * 这个轮播图的适配器
 */
public class UlTraPagerAdapter extends PagerAdapter {

    // 这个是默认的轮播图
    private int[] image = {R.drawable.img_default_meizi, R.drawable.img_default_meizi, R.drawable.img_default_meizi};
    private List<String> banns; // 这个是从网络获取的轮播数据

    public UlTraPagerAdapter(List<String> banns) {
        this.banns = banns;
    }

    @Override
    public int getCount() {
        return banns == null || banns.size() == 0 ? image.length : banns.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child, null);
        final ImageView imageView = view.findViewById(R.id.pager_image);
        final ProgressBar progressBar = view.findViewById(R.id.pager_bar);
        // 加载图片
        GlideApp.with(container.getContext())
                .load(banns == null || banns.size() == 0 ? image[position] : banns.get(position))
                .centerCrop()
                .placeholder(R.drawable.ic_default_color) // 默认显示图片
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 使用缓存
                .into(new ImageViewTarget<Drawable>(imageView) { // 加载图片的
                    @Override
                    protected void setResource(@Nullable Drawable resource) {
                        imageView.setImageDrawable(resource);
                        progressBar.setVisibility(View.GONE);
                    }
                });
        container.addView(view);
//        view.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, container.getContext().getResources().getDisplayMetrics());
//        view.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, container.getContext().getResources().getDisplayMetrics());
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
