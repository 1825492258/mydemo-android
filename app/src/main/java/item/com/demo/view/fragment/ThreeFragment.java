package item.com.demo.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.item.sdk.base.fragment.BaseCompatFragment;

import item.com.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseCompatFragment {


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
        Log.d("jiejie","ThreeFragment  Bundle");
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d("jiejie","ThreeFragment  onLazyInitView");
    }
}