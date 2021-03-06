package item.com.demo.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.item.sdk.base.fragment.BaseCompatFragment;
import com.item.sdk.utils.ToastTipUtil;
import com.item.sdk.utils.ToastUtils;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.MyPagerAdapter;
import item.com.demo.adapter.SpikeAdapter;
import item.com.demo.adapter.SpikeAdapters;
import item.com.demo.adapter.UlTraPagerAdapter;
import item.com.demo.view.MyRightDialog;
import item.com.demo.view.activity.GirlsFragmentActivity;
import item.com.demo.view.activity.KeyActivity;
import item.com.demo.view.activity.MultipleActivity;
import item.com.demo.view.activity.ShopActivity;
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
    @BindView(R.id.btnKey)
    Button btnKey;
    @BindView(R.id.ultra_view)
    UltraViewPager ultraViewPager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnToOther)
    Button btnToOther;
    @BindView(R.id.btnToShop)
    Button btnToShop;
    @BindView(R.id.btnToast)
    Button btnToast;
    @BindView(R.id.ultra_views)
    UltraViewPager mViewPager;

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
        // 这里是横向的RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        List<String> a = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            a.add("-" + i);
        }
        SpikeAdapter  spikeAdapter = new SpikeAdapter(R.layout.adapter_spike, a);
        recyclerView.setAdapter(spikeAdapter);
        spikeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
        btnToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopActivity.show(mActivity);
            }
        });
        btnKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 KeyActivity.show(mActivity);
                ToastTipUtil.showTip("失败了",1000,2);
            }
        });
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToastTipUtil.showTip("你好啊",1500,1);
                ToastTipUtil.showTip("你好啊");
                show();
            }
        });
        initView();
    }
    private MyRightDialog mdialog;
    private void show(){
        if(mdialog == null) mdialog = new MyRightDialog(mActivity);
        mdialog.show();
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
        PagerAdapter pagerAdapter = new UlTraPagerAdapter(null);
        ultraViewPager.setAdapter(pagerAdapter);
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
   // private int mPageSize = 3; //每页显示的最大的数量
    private void initView(){
        // 添加集合数据
        List<String> a = new ArrayList<>();
        List<View> viewPagerList = new ArrayList<View>();
        for (int i = 0; i < 8; i++) {
            a.add("首页" + i);
        }
        // 总的页数向上取整
        int totalPage = (int) Math.ceil(a.size() *1.0 / 3);

        for ( int i =0; i < totalPage;i++){
             RecyclerView recycler = (RecyclerView) View.inflate(mActivity,
                    R.layout.item_grid,null);
            GridLayoutManager mgr = new GridLayoutManager(mActivity, 3);
             recycler.setLayoutManager(mgr);
             recycler.setAdapter(new SpikeAdapters(R.layout.adapter_spike,a,i));
             // 每个RecyclerView作为一个View对象添加到ViewPager中
            viewPagerList.add(recycler);
        }
        // 设置ViewPager的适配器
        mViewPager.setAdapter(new MyPagerAdapter(viewPagerList));
        // 内置indicator 初始化
        mViewPager.initIndicator();
        // 设置indicator样式
        mViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusResId(R.drawable.ic_select_bg)
                .setNormalResId(R.drawable.ic_no_bg)
//                .setFocusColor(Color.GREEN)
//                .setNormalColor(Color.WHITE)
                .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM).build();
                //.setMargin(0, 0, 0, ToastUtils.dp2px(2))
                //.setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics())).build();
    }
}
