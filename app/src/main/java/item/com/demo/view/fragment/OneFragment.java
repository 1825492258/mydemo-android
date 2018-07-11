package item.com.demo.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.item.sdk.base.fragment.BaseCompatFragment;
import com.item.sdk.utils.StatusBarUtil;
import com.item.sdk.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.TextAdapter;
import item.com.demo.view.activity.ChatActivity;

/**
 * 第一个界面，只是简单的展示一个ListView
 */
public class OneFragment extends BaseCompatFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public static OneFragment newInstance(){
        Bundle args = new Bundle();
        OneFragment oneFragment = new OneFragment();
        oneFragment.setArguments(args);
        return oneFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {
        Log.d("jiejie","OneFragment  Bundle");
        StatusBarUtil.immersive(mActivity);
        StatusBarUtil.setPaddingSmart(mActivity,mToolbar);
        List<String> te = new ArrayList<>();
        for(int i=0; i < 50 ;i++){
            te.add("----" + i);
        }
        TextAdapter adapter = new TextAdapter(te);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
       adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast((String) adapter.getItem(position));
               ChatActivity.show(getActivity());
           }
       });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d("jiejie","OneFragment  onLazyInitView");
    }
}
