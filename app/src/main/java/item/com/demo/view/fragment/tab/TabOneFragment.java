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
public class TabOneFragment extends BaseCompatFragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.mTextView)
    TextView mText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private static final int PAGE_SIZE = 6;
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
        // 添加空布局
        newsAdapter.setEmptyView(com.item.sdk.R.layout.view_empty, (ViewGroup) recyclerView.getParent());
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        newsAdapter.isFirstOnly(false);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        newsAdapter.setOnLoadMoreListener(this, recyclerView);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 开启Loading 获取数据
        setRefreshing(true);
        onRefresh(); // 第一次显示请求最新的List
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
        Log.d("jiejie","开始加载");
        newsAdapter.setEnableLoadMore(false); // 这里的作用是防止下拉刷新的时候还可以上拉加载
        OkGo.<GankResponse<List<GankModel>>>get(url + 1)
                .execute(new JsonCallback<GankResponse<List<GankModel>>>() {
                    @Override
                    public void onSuccess(Response<GankResponse<List<GankModel>>> response) {
                        Log.d("jiejie", "---" + url + 1);
                        List<GankModel> results = response.body().results;
                        if (results != null) {
                            currentPage = 2;
                            newsAdapter.setNewData(results);
                        }
                        // 如果有跟布局，可能需要移除之前添加的布局
                        newsAdapter.removeAllFooterView();
                        // newsAdapter.setEnableLoadMore(true);
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(String errMessage) {
                        //  newsAdapter.setEnableLoadMore(true);
                        Log.d("jiejie"," 请求失败" );
                        refreshLayout.setRefreshing(false);
                        Log.d("jiejie", "---" + url + 1 + "   " + errMessage);
                    }

//                    @Override
//                    public void onFinish() {
//                        //可能需要移除之前添加的布局 android
//                        Log.d("jiejie","-------你好啊");
//                      //  newsAdapter.removeAllFooterView();
//                        //最后调用结束刷新的方法
//                        setRefreshing(false);
//                    }
                });
    }


    /**
     * 上拉加载下一页
     */
    @Override
    public void onLoadMoreRequested() {
        Log.d("jiejie", "上拉加载" + url + currentPage);
        OkGo.<GankResponse<List<GankModel>>>get(url + currentPage)
                .execute(new JsonCallback<GankResponse<List<GankModel>>>() {
                    @Override
                    public void onSuccess(Response<GankResponse<List<GankModel>>> response) {
                        Log.d("jiejie", "---" + url + currentPage);
                        List<GankModel> results = response.body().results;
                        if (results != null && results.size() > 0) {
                            currentPage++;
                            newsAdapter.addData(results);
                            // 加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
                            newsAdapter.loadMoreComplete(); // 这个必须回调
                        } else {
                            // 数据全部加载完毕 不再加载了
                            newsAdapter.loadMoreEnd();
                            View noDataView = View.inflate(getActivity(), R.layout.item_no_data, null);
                            newsAdapter.addFooterView(noDataView);
                        }
                    }

                    @Override
                    public void onError(String errMessage) {
                        Log.d("jiejie", "---" + url + currentPage + "   " + errMessage);
                        // 获取更多数据失败,点击重试
                        newsAdapter.loadMoreFail();
                    }
                });
    }
}
