package item.com.demo.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.item.sdk.base.fragment.BaseCompatFragment;
import com.item.sdk.utils.StatusBarUtil;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.view.activity.GirlsFragmentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseCompatFragment {

    @BindView(R.id.text)
    Button mText;
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
     //   StatusBarUtil.immersive(mActivity);
     //   StatusBarUtil.setPaddingSmart(mActivity,mText);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GirlsFragmentActivity.show(mActivity);
            }
        });
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d("jiejie","ThreeFragment  onLazyInitView");
    }
}
