package item.com.demo.view.fragment.tab;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.item.sdk.base.fragment.BaseCompatFragment;
import com.item.sdk.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.WelfareAdapter;
import item.com.demo.bean.WalfareBean;
import item.com.demo.net.JsonCallback;
import item.com.demo.net.UrlFactory;
import item.com.demo.view.activity.ImageBrowseActivity;

/**
 * 福利
 */
public class WelfareFragment extends BaseCompatFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    public static WelfareFragment newInstance() {
        Bundle args = new Bundle();
        WelfareFragment fragment = new WelfareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.welfare)
    RecyclerView mRecyclerView;
    private WelfareAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    private String url;
    private static final int PAGE_SIZE = 6;

    @Override
    public void initUI(Bundle savedInstanceState) {
        url = UrlFactory.URL_GANK_BASE + "福利" + "/" + PAGE_SIZE + "/";
        // 初始化一个空List的适配器,网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        mAdapter = new WelfareAdapter(R.layout.item_welfare, null);
        mRecyclerView.setAdapter(mAdapter);
        // 构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        // 加载更多
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
              WalfareBean.ResultsBean i= (WalfareBean.ResultsBean) adapter.getItem(position);
              ImageBrowseActivity.show(mActivity,i.getUrl());
            }
        });
    }

    /**
     * 懒加载
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        onLoadLatestList();
    }

    // 加载第一页
    private void onLoadLatestList() {
        OkGo.<WalfareBean>get(url + 1)
                .execute(new JsonCallback<WalfareBean>() {
                    @Override
                    public void onError(String errMessage) {

                    }

                    @Override
                    public void onSuccess(Response<WalfareBean> response) {
                        if (response.body().getResults() != null) {
                            mAdapter.setNewData(response.body().getResults());
                            currentPage = 2;
                        }
                    }
                });
    }

    private int currentPage = 2;

    @Override
    public void onLoadMoreRequested() {
        OkGo.<WalfareBean>get(url + currentPage)
                .execute(new JsonCallback<WalfareBean>() {
                    @Override
                    public void onError(String errMessage) {
                        mAdapter.loadMoreFail();
                    }

                    @Override
                    public void onSuccess(Response<WalfareBean> response) {
                        List<WalfareBean.ResultsBean> result = response.body().getResults();
                        if (result != null && result.size() > 0) {
                            currentPage++;
                            mAdapter.addData(result);
                            mAdapter.loadMoreComplete();
                        } else {
                            // 显示没有更多的数据
                            mAdapter.loadMoreEnd(true);
                            View view = View.inflate(mActivity, R.layout.item_no_data, null);
                            mAdapter.addFooterView(view);
                        }
                    }
                });
    }
}
