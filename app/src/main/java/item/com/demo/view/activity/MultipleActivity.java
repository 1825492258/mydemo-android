package item.com.demo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.item.sdk.base.activity.BaseCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.MyTextItemAdapter;
import item.com.demo.bean.MyTextItem;

/**
 * 多布局的Recycler
 */
public class MultipleActivity extends BaseCompatActivity {

    public static void show(Activity activity){
        Intent intent = new Intent(activity,MultipleActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<MyTextItem> data = new ArrayList<>();
    private MyTextItemAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_multiple;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<String> a = new ArrayList<>();
        for (int i =0 ; i < 8;  i++){
            a.add("-" + i);
        }
        data.add(new MyTextItem(1," "));
        data.add(new MyTextItem(1," "));
        data.add(new MyTextItem(1," "));
        data.add(new MyTextItem(1," "));
        data.add(new MyTextItem(3," ",a));
        data.add(new MyTextItem(2," "));
        data.add(new MyTextItem(1," "));
        data.add(new MyTextItem(1," "));
        data.add(new MyTextItem(1," "));
        data.add(new MyTextItem(1," "));
        data.add(new MyTextItem(3," ",a));
        data.add(new MyTextItem(2," "));
        data.add(new MyTextItem(2," "));
        data.add(new MyTextItem(2," "));
        data.add(new MyTextItem(2," "));
        data.add(new MyTextItem(2," "));
        adapter = new MyTextItemAdapter(data);
        //RecyclerView.LayoutManager manager =  new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        GridLayoutManager manager = new GridLayoutManager(this,4,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);

        // 如果考虑到在GridLayoutManager复用item问题可以配置
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getItemType();
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
