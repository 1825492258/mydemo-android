package item.com.demo.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.item.sdk.base.fragment.BaseCompatFragment;
import com.item.sdk.utils.StatusBarUtil;
import com.item.sdk.utils.ToastUtils;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;
import com.tmall.ultraviewpager.transformer.UltraScaleTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.UlTraPagerAdapter;
import item.com.demo.view.activity.GirlsFragmentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseCompatFragment {

    @BindView(R.id.text)
    Button mText;
    @BindView(R.id.ultra_view)
    UltraViewPager ultraViewPager;

    private int[] image = {R.drawable.ic_default_color,R.drawable.ic_default_color};
    public static ThreeFragment newInstance(){
        Bundle args = new Bundle();
        ThreeFragment oneFragment = new ThreeFragment();
        oneFragment.setArguments(args);
        return oneFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GirlsFragmentActivity.show(mActivity);
            }
        });
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        // UltraPagerAdapter 绑定子view到UltraViewPager
        PagerAdapter adapter = new UlTraPagerAdapter(null);
        ultraViewPager.setAdapter(adapter);
 //       ultraViewPager.setMultiScreen(0.6f);
//        ultraViewPager.setItemRatio(1.0f);
//        ultraViewPager.setRatio(2.0f);
//        ultraViewPager.setMaxHeight(800);
 //       ultraViewPager.setAutoMeasureHeight(true);
 //       ultraViewPager.setPageTransformer(false,new UltraScaleTransformer());
        // ultraViewPager.setPageTransformer(false,new UltraDepthScaleTransformer());
        // 内置indicator 初始化
        ultraViewPager.initIndicator();
        // 设置indicator样式
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        // 设置indicator对齐方式
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        ultraViewPager.getIndicator().setMargin(0,0,0, ToastUtils.dp2px(15));
        //构造indicator,绑定到UltraViewPager
        ultraViewPager.getIndicator().build();
        // 设置页面循环播放
        ultraViewPager.setInfiniteLoop(true);
        // 设置页面自动切换的间隔为3s
        ultraViewPager.setAutoScroll(3000);
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d("jiejie","ThreeFragment  onLazyInitView");
    }
}
