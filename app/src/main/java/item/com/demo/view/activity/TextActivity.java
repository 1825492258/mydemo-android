package item.com.demo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.item.sdk.base.activity.BaseCompatActivity;
import com.item.sdk.utils.StatusBarUtil;
import butterknife.BindView;
import item.com.demo.R;

public class TextActivity extends BaseCompatActivity {
   // @BindView(R.id.toolbar)
 //   Toolbar toolbar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textTitle)
    TextView textTitle;
    public static void show(Activity activity) {
        Intent intent = new Intent(activity,TextActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_text;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this,textTitle);
        MovieDetailActivity.displayBlurImg(this,R.drawable.icon_xing_bg,imageView);
    }
}
