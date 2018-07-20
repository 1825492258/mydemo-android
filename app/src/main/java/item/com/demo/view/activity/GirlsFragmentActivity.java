package item.com.demo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.item.sdk.base.activity.BaseCompatActivity;

import java.util.ArrayList;
import java.util.List;

import item.com.demo.R;

/**
 * 福利
 */
public class GirlsFragmentActivity extends BaseCompatActivity {

    /**
     * show
     */
    public static void show(Activity activity) {
        Intent intent = new Intent(activity,GirlsFragmentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_girls_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
    private List<Fragment> fragments;
    private List<String> title;
    private void setupViewPager(ViewPager viewPager) {
        fragments = new ArrayList<>();
        title = new ArrayList<>();
        // ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        fragments.add(MztuFragment.newInstance("http://www.mzitu.com/hot"));
        title.add("最热");

        fragments.add(MztuFragment.newInstance("http://www.mzitu.com/xinggan"));
        title.add("性感妹子");

        fragments.add(MztuFragment.newInstance("http://www.mzitu.com/japan"));
        title.add("日本妹子");

        fragments.add(MztuFragment.newInstance("http://www.mzitu.com/taiwan"));
        title.add( "台湾妹子");

        fragments.add( MztuFragment.newInstance("http://www.mzitu.com/mm"));
        title.add("清纯妹子");

        fragments.add( MztuFragment.newInstance("http://www.mzitu.com/share"));
        title.add("妹子自拍");

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                fragments,title));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private  List<Fragment> mFragmentList ;
        private List<String> mFragmentTitleList;

        private ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> mFragmentList,
                                List<String> mFragmentTitleList) {
            super(fm);
            this.mFragmentList = mFragmentList;
            this.mFragmentTitleList = mFragmentTitleList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
