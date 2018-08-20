package item.com.demo.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.item.sdk.base.fragment.BaseCompatFragment;
import com.item.sdk.utils.StatusBarUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.FramentAdapter;
import item.com.demo.view.fragment.tab.TabOneFragment;
import item.com.demo.view.fragment.tab.WelfareFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends BaseCompatFragment {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    public static ViewPagerFragment newInstance() {
        Bundle args = new Bundle();
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_view_pager;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {
        StatusBarUtil.immersive(mActivity);
        StatusBarUtil.setPaddingSmart(mActivity,mTab);
        List<Fragment> fragments = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("Android");
        list.add("iOS");
        list.add("福利");
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    fragments.add(TabOneFragment.newInstance(list.get(0)));
                    break;
                case 1:
                    fragments.add(TabOneFragment.newInstance(list.get(1)));
                    break;
                case 2:
                    fragments.add(WelfareFragment.newInstance());
                    break;
            }
        }
        mTab.setTabMode(TabLayout.MODE_FIXED); // MODE_FIXED* （固定的tab）和 MODE_SCROLLABLE（滑动的tab）
        mTab.setTabGravity(TabLayout.GRAVITY_FILL); // GRAVITY_CENTER （内容中心显示） 和 GRAVITY_FILL （内容尽可能充满
        mViewPager.setAdapter(new FramentAdapter(getChildFragmentManager(), fragments, list));
        mViewPager.setCurrentItem(0); // 要设置到viewpager.setAdapter后才起作用
        //关联ViewPager和TabLayout
        mTab.setupWithViewPager(mViewPager);
        //tlTabs.setupWithViewPager方法内部会remove所有的tabs，这里重新设置一遍tabs的text，否则tabs的text不显示
//        //设置分割线
//        LinearLayout linear = (LinearLayout)tabLayout.getChildAt(0);
//        linear.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        linear.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.divider));
//        //设置分割线间隔
//        linear.setDividerPadding(dip2px(15));
    }


}
