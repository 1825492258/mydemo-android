package item.com.demo.view.fragment.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.item.sdk.base.fragment.BaseCompatFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import item.com.demo.R;
import item.com.demo.adapter.NewsAdapter;
import item.com.demo.bean.GankModel;
import item.com.demo.bean.GankResponse;
import item.com.demo.net.JsonCallback;
import item.com.demo.net.UrlFactory;


/**
 * Created by wuzongjie on 2018/7/11
 */
public class TabOneFragment extends BaseCompatFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.mTextView)
    TextView mText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private static final int PAGE_SIZE = 20;
    private int currentPage = 2;
    private NewsAdapter newsAdapter;
    private String url;
    public static TabOneFragment newInstance(String title) {
        Bundle args = new Bundle();
        TabOneFragment fragment = new TabOneFragment();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab_one;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {
        String title = getArguments().getString("title", "Android");
        mText.setText(title);
        url = UrlFactory.URL_GANK_BASE + title + "/" + PAGE_SIZE + "/";
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        newsAdapter = new NewsAdapter(null);
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        newsAdapter.isFirstOnly(false);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        newsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        },recyclerView);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 开启Loading 获取数据
        setRefreshing(true);
        onRefresh();
    }

    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                currentPage = 2;
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        Log.d("jiejie","2222222222222");
        OkGo.<GankResponse<List<GankModel>>>get(url + "1")
                .execute(new JsonCallback<GankResponse<List<GankModel>>>() {
                    @Override
                    public void onSuccess(Response<GankResponse<List<GankModel>>> response) {
                        List<GankModel> results = response.body().results;
                        if(results !=null) {
                            newsAdapter.setNewData(results);
                        }
                    }

                    @Override
                    public void onFinish() {
                        //可能需要移除之前添加的布局
                        newsAdapter.removeAllFooterView();
                        //最后调用结束刷新的方法
                        setRefreshing(false);
                    }
                });
    }

    /**
     * 上拉加载
     */
    private void onLoadMore(){
        OkGo.<GankResponse<List<GankModel>>>get(url + currentPage)
                .execute(new JsonCallback<GankResponse<List<GankModel>>>() {
                    @Override
                    public void onSuccess(Response<GankResponse<List<GankModel>>> response) {
                        List<GankModel> results = response.body().results;
                        if (results != null && results.size() > 0) {
                            currentPage++;
                            newsAdapter.addData(results);
                        } else {
                            //显示没有更多数据
                            newsAdapter.loadMoreComplete();
                            View noDataView = View.inflate(getActivity(),R.layout.item_no_data,null);
                            //View noDataView = inflater.inflate(R.layout.item_no_data, (ViewGroup) recyclerView.getParent(), false);
                            newsAdapter.addFooterView(noDataView);
                        }
                    }

                    @Override
                    public void onError(Response<GankResponse<List<GankModel>>> response) {
                        super.onError(response);
                        //显示数据加载失败,点击重试
                       // newsAdapter.showLoadMoreFailedView();
                    }
                });
    }
}
