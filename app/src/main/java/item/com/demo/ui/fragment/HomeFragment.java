package item.com.demo.ui.fragment;

import android.os.Bundle;

import com.item.sdk.base.fragment.BaseCompatFragment;

/**
 * Created by wuzongjie on 2018/7/5
 */
public class HomeFragment extends BaseCompatFragment{

    public static HomeFragment newInstance(){
        Bundle ars = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(ars);
        return fragment;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
