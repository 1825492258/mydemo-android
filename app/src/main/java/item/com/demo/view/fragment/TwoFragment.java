package item.com.demo.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.item.sdk.base.fragment.BaseCompatFragment;

import butterknife.BindView;
import item.com.demo.R;

/**
 * 第二个Fragment
 */
public class TwoFragment extends BaseCompatFragment {

    public static TwoFragment newInstance(){
        Bundle args = new Bundle();
        TwoFragment oneFragment = new TwoFragment();
        oneFragment.setArguments(args);
        return oneFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {
        if(findChildFragment(ViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_two_container,ViewPagerFragment.newInstance());
        }
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 这里可以不用懒加载,因为Adapter的场景下,Adapter内的子Fragment只有在父Fragment是show状态时,才会被Attach,Create
        Log.d("jiejie","TwoFragment  onLazyInitView");
    }
}
