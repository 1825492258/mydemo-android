package item.com.demo.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import item.com.demo.R;
import item.com.demo.adapter.FilterAdapter;
import item.com.demo.bean.FilterBean;

/**
 * Created by wuzongjie on 2018/10/24
 */
public class MyRightDialog extends Dialog {
    private DisplayMetrics dm; // 屏幕分辨率
    @BindView(R.id.recyclerView_one)
    RecyclerView recyclerViewOne;
    @BindView(R.id.recyclerView_two)
    RecyclerView recyclerViewTwo;
    @BindView(R.id.recyclerView_three)
    RecyclerView recyclerViewThree;
    @BindView(R.id.etLimitMin)
    EditText etLimitMin;
    @BindView(R.id.etLimitMax)
    EditText etLimitMax;
    @BindView(R.id.llC2c)
    LinearLayout llC2c;
    private Context context;
    private FilterAdapter firAdapter;
    private FilterAdapter secAdapter;
    private FilterAdapter thdAdapter;
    private ArrayList<FilterBean> firstList = new ArrayList<>();
    private ArrayList<FilterBean> secList = new ArrayList<>();
    private ArrayList<FilterBean> thdList = new ArrayList<>();

    public MyRightDialog(@NonNull Context context) {
        super(context, R.style.custom_dialogs);
        this.context = context;
        dm = context.getResources().getDisplayMetrics();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_layout_right, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        firstList.add(new FilterBean("中国", "", true));
        firstList.add(new FilterBean("加拉", "", false));
        firstList.add(new FilterBean("尼日尼亚", "", false));
        firstList.add(new FilterBean("更你呀", "", false));
        firstList.add(new FilterBean("无妨", "", false));
        secList.add(new FilterBean("微信", "", false));
        secList.add(new FilterBean("支付宝", "", false));
        secList.add(new FilterBean("银行卡", "", false));
        secList.add(new FilterBean("银行卡22", "", false));
        thdList.add(new FilterBean("CNY", "", false));
        thdList.add(new FilterBean("USD", "", false));
        thdList.add(new FilterBean("USDs", "", false));
        thdList.add(new FilterBean("USDd", "", false));
        thdList.add(new FilterBean("USDdd", "", false));
        thdList.add(new FilterBean("DJ", "", false));
        initRv();
        initCreate();
        initListener();
    }

    private void initCreate() {
        // 设置弹窗的宽和高
        Window dialogWindow = getWindow();
        assert dialogWindow != null;
        dialogWindow.setGravity(Gravity.END);
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.width = (int) (dm.widthPixels * 0.75);
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(params);
    }

    private void initRv() {
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        recyclerViewOne.setLayoutManager(manager);
        firAdapter = new FilterAdapter(firstList);
        recyclerViewOne.setAdapter(firAdapter);
        GridLayoutManager managerTwo = new GridLayoutManager(context, 3);
        recyclerViewTwo.setLayoutManager(managerTwo);
        secAdapter = new FilterAdapter(secList);
        recyclerViewTwo.setAdapter(secAdapter);
        GridLayoutManager managerThree = new GridLayoutManager(context, 3);
        recyclerViewThree.setLayoutManager(managerThree);
        thdAdapter = new FilterAdapter(thdList);
        recyclerViewThree.setAdapter(thdAdapter);
    }

    private void initListener() {
        firAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FilterBean filterBean = firstList.get(position);
                resetDataBiClick(firstList, filterBean.getName());
                firAdapter.notifyDataSetChanged();
            }
        });
        secAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FilterBean filterBean = secList.get(position);
                resetDataBiClick(secList, filterBean.getName());
                secAdapter.notifyDataSetChanged();
            }
        });
        thdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FilterBean filterBean = thdList.get(position);
                resetDataBiClick(thdList, filterBean.getName());
                thdAdapter.notifyDataSetChanged();
            }
        });

    }

    private void resetDataBiClick(ArrayList<FilterBean> arrayList, String name) {
        for (FilterBean bean : arrayList) {
            if (!TextUtils.isEmpty(bean.getName()) && bean.getName().equals(name)) {
                bean.setSelected(!bean.isSelected());
            }
        }
    }

    @OnClick({R.id.tvReset, R.id.tvSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvReset: // 重置
                resetData(firstList);
                resetData(secList);
                resetData(thdList);
                firstList.get(0).setSelected(true);
                firAdapter.notifyDataSetChanged();
                secAdapter.notifyDataSetChanged();
                thdAdapter.notifyDataSetChanged();
                break;
            case R.id.tvSure: // 确定，需要把获取的数据传出去
                dismiss();
                break;
        }
    }
    private void  resetData(ArrayList<FilterBean> arrayList){
        for(FilterBean bean : arrayList){
            bean.setSelected(false);
        }
    }
}
