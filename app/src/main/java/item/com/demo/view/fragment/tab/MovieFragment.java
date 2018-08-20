package item.com.demo.view.fragment.tab;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.item.sdk.base.fragment.BaseCompatFragment;
import com.item.sdk.utils.ResourcesUtils;
import com.item.sdk.utils.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.MovieAdapter;
import item.com.demo.bean.SubBean;
import item.com.demo.net.JsonCallback;
import item.com.demo.net.UrlFactory;
import item.com.demo.view.activity.MovieDetailActivity;
import item.com.demo.view.activity.TextActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseCompatFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView title;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.rv_hot_movie)
    RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private View headView;

    public static MovieFragment newInstance(){
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {
        StatusBarUtil.immersive(mActivity);
        StatusBarUtil.setPaddingSmart(mActivity,toolbar);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextActivity.show(mActivity);
            }
        });
        // 初始化一个空list的adapter
        mAdapter = new MovieAdapter(R.layout.item_hot_movie,null);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(ResourcesUtils.inflate(R.layout.view_error));
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRefresh.setColorSchemeResources(android.R.color.holo_red_light,android.R.color.holo_blue_light,
                android.R.color.holo_green_light);
        mRefresh.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // 由于有headview click position需要+1 adapter.getItem返回的是数据list的position，所以不用+1
                MovieDetailActivity.show(mActivity,
                        (SubBean.SubjectsBean) adapter.getItem(position),
                        (ImageView)view.findViewById(R.id.iv_moive_photo));
            }
        });
    }

    @Override
    public void onRefresh() {
        OkGo.<SubBean>get(UrlFactory.URL_MOVIE)
                .execute(new JsonCallback<SubBean>() {
                    @Override
                    public void onError(String errMessage) {
                        setRefreshing(false);
                    }

                    @Override
                    public void onSuccess(Response<SubBean> response) {
                        setRefreshing(false);
                        List<SubBean.SubjectsBean> beans = response.body().getSubjects();
                        Log.d("jiejie","----" + mAdapter.getData().size());
                        if(mAdapter.getData().isEmpty()) {
                            initHeadView();
                            mAdapter.setNewData(beans);
                        }else {
                            mAdapter.setNewData(beans);
                        }
                    }
                });
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setRefreshing(true);
        onRefresh();
    }
    public void setRefreshing(final boolean refreshing) {
        mRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefresh.setRefreshing(refreshing);
            }
        },500);
    }
    private void initHeadView(){

        if(headView == null) {
            headView = ResourcesUtils.inflate(R.layout.layout_top_header);
        }
        headView.findViewById(R.id.ll_movie_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startNewFragment 跳转新的Fragment
                // 启动新的Fragment, 另有start(fragment,SINGTASK)、startForResult、startWithPop等启动方法
               start(TopMovieFragment.newInstance());
            }
        });
        mAdapter.addHeaderView(headView);
    }


}
