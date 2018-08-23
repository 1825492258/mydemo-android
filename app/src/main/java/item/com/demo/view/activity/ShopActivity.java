package item.com.demo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import item.com.demo.R;
import item.com.demo.adapter.ShopAdater;
import item.com.demo.bean.Item;

public class ShopActivity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder> {

    public static void show(Activity activity){
        Intent intent = new Intent(activity,ShopActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.item_name)
    TextView itemName;
    @BindView(R.id.item_price)
    TextView itemPrice;
    @BindView(R.id.item_picker)
    DiscreteScrollView itemPicker;
    @BindView(R.id.btn_smooth_scroll)
    Button btnSmoothScroll;
    @BindView(R.id.btn_transition_time)
    Button btnTransitionTime;
    private InfiniteScrollAdapter infiniteScrollAdapter;
    private List<Item> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        data = getData();
        // 设置为水平方向
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        infiniteScrollAdapter = InfiniteScrollAdapter.wrap(new ShopAdater(data));
        itemPicker.setAdapter(infiniteScrollAdapter);
        itemPicker.setItemTransitionTimeMillis(250);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
        .setMinScale(0.8f)
        .build());
    }

    @OnClick({R.id.btn_smooth_scroll, R.id.btn_transition_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_smooth_scroll:
                break;
            case R.id.btn_transition_time:
                break;
        }
    }

    public List<Item> getData() {
        return Arrays.asList(
                new Item(1, "Everyday Candle", "$12.00 USD", R.drawable.shop1),
                new Item(2, "Small Porcelain Bowl", "$50.00 USD", R.drawable.shop2),
                new Item(3, "Favourite Board", "$265.00 USD", R.drawable.shop3),
                new Item(4, "Earthenware Bowl", "$18.00 USD", R.drawable.shop4),
                new Item(5, "Porcelain Dessert Plate", "$36.00 USD", R.drawable.shop5),
                new Item(6, "Detailed Rolling Pin", "$145.00 USD", R.drawable.shop6));
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }
}
