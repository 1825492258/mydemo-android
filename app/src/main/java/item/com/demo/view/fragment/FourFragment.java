package item.com.demo.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.item.sdk.base.fragment.BaseCompatFragment;

import item.com.demo.R;


/**
 *
 */
public class FourFragment extends BaseCompatFragment {

    public static FourFragment newInstance(){
        Bundle args = new Bundle();
        FourFragment oneFragment = new FourFragment();
        oneFragment.setArguments(args);
        return oneFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_four;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {
        Log.d("jiejie","FourFragment  Bundle");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d("jiejie","FourFragment  onLazyInitView");
    }
}
