package item.com.demo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.item.sdk.base.fragment.BaseCompatFragment;
import item.com.demo.R;
import item.com.demo.view.fragment.tab.MovieFragment;

/**
 * MovieRoot
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

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 加载子fragment
        if(savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, MovieFragment.newInstance());
        }else { // 这里可能会出现该Fragment没被初始化，就被强杀导致的没有load子Fragment
            if(findChildFragment(MovieFragment.class) == null) {
                loadRootFragment(R.id.fl_container,MovieFragment.newInstance());
            }
        }
    }
}
