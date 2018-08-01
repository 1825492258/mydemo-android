package item.com.demo.view.fragment.tab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.item.sdk.base.fragment.BaseCompatFragment;
import com.item.sdk.utils.StatusBarUtil;

import butterknife.BindView;
import item.com.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopMovieFragment extends BaseCompatFragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public static TopMovieFragment newInstance(){
        // Required empty public constructor
        Bundle args = new Bundle();
        TopMovieFragment fragment = new TopMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_top_moive;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {
        StatusBarUtil.immersive(mActivity);
        StatusBarUtil.setPaddingSmart(mActivity,toolbar);
        toolbar.setTitle("豆瓣电影Top250");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

}
