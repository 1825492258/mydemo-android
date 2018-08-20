package item.com.demo.view.ohter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.item.sdk.base.activity.BaseCompatActivity;
import com.item.sdk.utils.ToastUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import item.com.demo.R;
import item.com.demo.bean.db.Ant;
import item.com.demo.bean.db.Group;
import item.com.demo.bean.db.User;

public class OtherActivity extends BaseCompatActivity {

    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnQuery)
    Button btnQuery;

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, OtherActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @OnClick({R.id.btnAdd, R.id.btnQuery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                add();
                break;
            case R.id.btnQuery:
                List<Group> groups = query();
                ToastUtils.showToast(groups.size() + "");
                break;
        }
    }

    /**
     * 添加几个数据
     */
    private void add() {
        Ant ant1 = new Ant("你好");
        Ant ant2 = new Ant("我不好");
        List<Ant> ants = new ArrayList<>();
        ants.add(ant1);ants.add(ant2);
        Group one = new Group("第一个", 1, new User("李四", "111"));
        one.ants = ants;
        Group two = new Group("第二个", 2, new User("张三", "110"));
        two.ants = null;
        one.save();
        two.save();
    }

    private List<Group> query() {
        // 这里用同步查询
        List<Group> groups = SQLite.select().from(Group.class).queryList();
       Log.d("jiejie","---" + groups.toString());
//        for (Group group : groups){
//            group.getUser().load();
//        }
//        Log.d("jiejie","---" + groups.toString());
        return groups;
    }
}
