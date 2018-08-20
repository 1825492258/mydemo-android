package item.com.demo.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.item.sdk.base.activity.BaseCompatActivity;
import com.item.sdk.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.MsgAdapter;
import item.com.demo.bean.Msg;

public class ChatActivity extends BaseCompatActivity {
    public static void show(Context context){
        Intent intent = new Intent(context,ChatActivity.class);
        context.startActivity(intent);
    }
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.myTitle)
    TextView mTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.et_send)
    EditText etSend;
    @BindView(R.id.btn_send)
    Button btnSend;
    private MsgAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }



    public static void editKeyboard(MotionEvent event, View view, Activity activity) {
        try {
            if ( view instanceof EditText) {
                int[] location = {0, 0};
                view.getLocationInWindow(location);
                int left = location[0], top = location[1], right = left
                        + view.getWidth(), bootom = top + view.getHeight();
                if (event.getRawX() < left || event.getRawX() > right
                        || event.getY() < top || event.getRawY() > bootom) {
                    IBinder token = view.getWindowToken();
                    InputMethodManager inputMethodManager = (InputMethodManager) activity
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(token,
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this,mTitle);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new MsgAdapter(null);
        recyclerView.setAdapter(mAdapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str= etSend.getText().toString().trim();
                if(TextUtils.isEmpty(str)) return;
                mAdapter.addData(new Msg(str));
                etSend.setText(null);
                recyclerView.scrollToPosition(mAdapter.getItemCount() - 1 );
            }
        });
        mAdapter.setNewData(getData());

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startUpFetch();
            }
        });
    }

    private void startUpFetch(){
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addData(0,getData());

                mRefresh.setRefreshing(false);
            }
        },800);
    }
    private List<Msg> getData (){
        ArrayList<Msg> list = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<5 ;i++){
            String name = "message" + random.nextInt(80);
            Msg m = new Msg(name);
            list.add(m);
        }
        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hiddenKeyboard();
    }
}
