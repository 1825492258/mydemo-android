package item.com.demo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.item.sdk.base.activity.BaseCompatActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.EvaluationAdapter;
import item.com.demo.bean.EvaluationBean;

/**
 * QQ空间
 */
public class EvaluationActivity extends BaseCompatActivity {

    public static void show(Context context) {
        Intent intent = new Intent(context, EvaluationActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evluation;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        View emptyView = View.inflate(this, com.item.sdk.R.layout.view_empty, null);
        listView.setEmptyView(emptyView);
        List<EvaluationBean.ReplyBean> item = new ArrayList<>();
        item.add(new EvaluationBean.ReplyBean(1, "无总结", "你好大东方"));
        item.add(new EvaluationBean.ReplyBean(1, "无胜多负少水电费水电费水电费", "张三"));
        item.add(new EvaluationBean.ReplyBean(1, "是打发打发的发送到发送到发送到发的说法是打发斯蒂芬", "李四"));
        List<EvaluationBean> jiejie = new ArrayList<>();
        jiejie.add(new EvaluationBean("wuzongjie---0"));
        jiejie.add(new EvaluationBean("wuzongjie---1", item));
        jiejie.add(new EvaluationBean("wuzongjie---2", item));
        jiejie.add(new EvaluationBean("wuzongjie---3"));
        jiejie.add(new EvaluationBean("wuzongjie---4", item));
        jiejie.add(new EvaluationBean("wuzongjie---5"));
        jiejie.add(new EvaluationBean("wuzongjie---6", item));
        jiejie.add(new EvaluationBean("wuzongjie---7"));
        jiejie.add(new EvaluationBean("wuzongjie---8", item));
        jiejie.add(new EvaluationBean("wuzongjie---9"));
        jiejie.add(new EvaluationBean("wuzongjie---10"));
        EvaluationAdapter mAdapter = new EvaluationAdapter(this, jiejie);
        listView.setAdapter(mAdapter);
    }

}
