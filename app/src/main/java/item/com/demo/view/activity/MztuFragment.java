package item.com.demo.view.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.item.sdk.base.fragment.BaseCompatFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.MtAdapter;
import item.com.demo.bean.Girl;

/**
 * A simple {@link Fragment} subclass.
 */
public class MztuFragment extends BaseCompatFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    public static MztuFragment newInstance(String title) {
        Bundle args = new Bundle();
        MztuFragment fragment = new MztuFragment();
        args.putString("url", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mztu;
    }

    private String url;
    private String fakeRefer;
    private int current = 1;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private MtAdapter adapter;
    @BindView(R.id.mtu)
    TextView mText;
    @BindView(R.id.mLayout)
    SwipeRefreshLayout mLayout;

    @Override
    public void initUI(Bundle savedInstanceState) {
        assert getArguments() != null;
        url = getArguments().getString("url") + "/page/";
        mText.setText(url);
        fakeRefer = getArguments().getString("url") + "/"; //伪造 refer 破解防盗链
        adapter = new MtAdapter(null);
        // 构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(adapter);
        mLayout.setOnRefreshListener(this);
        // 加载更多
        adapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showRefreshing(true);
        onLoadList();
    }

    @Override
    public void onRefresh() {
        onLoadList();
    }

    private void showRefreshing(final boolean refresh) {
        mLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLayout.setRefreshing(refresh);
            }
        }, 300);
    }

    private void onLoadList() {
        OkGo.<List<Girl>>get(url + current)
                .execute(new AbsCallback<List<Girl>>() {
                    @Override
                    public void onSuccess(Response<List<Girl>> response) {
                        showRefreshing(false);
                        List<Girl> girls = response.body();
                        if (girls != null) {
                            current = 2;
                            adapter.setNewData(girls);
                        }
                    }

                    @Override
                    public List<Girl> convertResponse(okhttp3.Response response) throws Throwable {
                        List<Girl> girls = new ArrayList<>();
                        Document doc = Jsoup.connect(url + current).timeout(10000).get();
                        Element total = doc.select("div.postlist").first();
                        Elements items = total.select("li");
                        for (Element element : items) {
                            Girl girl = new Girl(element.select("img").first().attr("data-original"));
                            girl.setLink(element.select("a[href]").attr("href"));
                            girl.setRefer(fakeRefer);
                            girls.add(girl);
                        }
                        return girls;
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        OkGo.<List<Girl>>get(url + current)
                .execute(new AbsCallback<List<Girl>>() {
                    @Override
                    public void onSuccess(Response<List<Girl>> response) {
                        if (response != null && response.body().isEmpty()) {
                            current++;
                            adapter.addData(response.body());
                            adapter.loadMoreComplete();
                        } else {
                            adapter.loadMoreEnd();
                        }
                    }

                    @Override
                    public List<Girl> convertResponse(okhttp3.Response response) throws Throwable {
                        List<Girl> girls = new ArrayList<>();

                        Document doc = Jsoup.connect(url + current).timeout(10000).get();
                        Element total = doc.select("div.postlist").first();
                        Elements items = total.select("li");
                        for (Element element : items) {
                            Girl girl = new Girl(element.select("img").first().attr("data-original"));
                            girl.setLink(element.select("a[href]").attr("href"));
                            girl.setRefer(fakeRefer);
                            girls.add(girl);
                        }
                        return girls;
                    }
                });
    }


}
