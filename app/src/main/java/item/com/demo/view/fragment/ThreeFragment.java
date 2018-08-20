package item.com.demo.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import item.com.demo.adapter.SpikeAdapter;
import item.com.demo.adapter.UlTraPagerAdapter;
import item.com.demo.view.activity.GirlsFragmentActivity;
import item.com.demo.view.activity.MultipleActivity;
import item.com.demo.view.ohter.OtherActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseCompatFragment {
    @BindView(R.id.time_hour)
    TextView timeHour;
    @BindView(R.id.time_minute)
    TextView timeMinute;
    @BindView(R.id.time_seconds)
    TextView timeSeconds;
    @BindView(R.id.text)
    Button mText;
    @BindView(R.id.ultra_view)
    UltraViewPager ultraViewPager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private SpikeAdapter adapter;
    @BindView(R.id.btnToOther)
    Button btnToOther;
    public static ThreeFragment newInstance() {
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
        initUltra();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        List<String> a = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            a.add("-" + i);
        }
        adapter = new SpikeAdapter(R.layout.adapter_spike, a);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleActivity.show(getActivity());
            }
        });
        initTimeDown(70 * 60 * 1000 + ""); // 这表示
        btnToOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtherActivity.show(mActivity);
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d("jiejie", "ThreeFragment  onLazyInitView");
    }

    // 倒计时
    private CountDownTimer timer;

    private void initTimeDown(String my) {
        if (TextUtils.isEmpty(my) || !my.matches("^[0-9]*$")) return;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(Long.parseLong(my), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long temp = millisUntilFinished / 1000;
                long hours = temp / 3600;
                long minutes = (temp - (3600 * hours)) / 60;
                long seconds = temp - (3600 * hours) - (60 * minutes);
                timeHour.setText(hours > 9 ? "" + hours : "0" + hours);
                timeMinute.setText(minutes > 9 ? "" + minutes : "0" + minutes);
                timeSeconds.setText(seconds > 9 ? "" + seconds : "0" + seconds);
            }

            @Override
            public void onFinish() {
                timeHour.setText("00");
                timeMinute.setText("00");
                timeSeconds.setText("00");
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 对UltraViewPager的设置
     */
    private void initUltra() {
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        // UltraPagerAdapter 绑定子view到UltraViewPager
        PagerAdapter adapter = new UlTraPagerAdapter(null);
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.setMultiScreen(0.6f);
//        ultraViewPager.setItemRatio(1.0f);
//        ultraViewPager.setRatio(2.0f);
//        ultraViewPager.setMaxHeight(800);
        ultraViewPager.setAutoMeasureHeight(true);
        //       ultraViewPager.setPageTransformer(false,new UltraScaleTransformer());
        ultraViewPager.setPageTransformer(false, new UltraDepthScaleTransformer());
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
        ultraViewPager.getIndicator().setMargin(0, 0, 0, ToastUtils.dp2px(15));
        //构造indicator,绑定到UltraViewPager
        ultraViewPager.getIndicator().build();
        // 设置页面循环播放
        ultraViewPager.setInfiniteLoop(true);
        // 设置页面自动切换的间隔为3s
        ultraViewPager.setAutoScroll(3000);
    }
}
