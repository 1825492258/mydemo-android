package item.com.demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.item.sdk.base.activity.BaseCompatActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import item.com.demo.bean.CoinBean;
import item.com.demo.bean.LoginBean;
import item.com.demo.bean.TextBean;
import item.com.demo.net.HttpResult;
import item.com.demo.net.HttpsCallback;
import item.com.demo.net.UrlFactory;
import item.com.demo.view.fragment.FourFragment;
import item.com.demo.view.fragment.OneFragment;
import item.com.demo.view.fragment.ThreeFragment;
import item.com.demo.view.fragment.TwoFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 这个是主界面
 */
public class MainActivity extends BaseCompatActivity {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    private SupportFragment[] mFragments = new SupportFragment[4];
    @BindView(R.id.home_tab_rg)
    RadioGroup mHomeGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getIntent().getExtras();

        if (savedInstanceState == null) {
           // 正常
            mFragments[FIRST] = OneFragment.newInstance();
            mFragments[SECOND] = TwoFragment.newInstance();
            mFragments[THIRD] = ThreeFragment.newInstance();
            mFragments[FOURTH] = FourFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {  // 内存重启时调用
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = findFragment(OneFragment.class);
            mFragments[SECOND] = findFragment(TwoFragment.class);
            mFragments[THIRD] = findFragment(ThreeFragment.class);
            mFragments[FOURTH] = findFragment(FourFragment.class);
        }

        /*
         * 点击下面的tab
         */
        mHomeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.home_tab_one:
                        showHideFragment(mFragments[FIRST]);
                        break;
                    case R.id.home_tab_two:
                        showHideFragment(mFragments[SECOND]);
                        break;
                    case R.id.home_tab_three:
                        showHideFragment(mFragments[THIRD]);
                        break;
                    case R.id.home_tab_four:
                        showHideFragment(mFragments[FOURTH]);
                        break;
                }
            }
        });
    }

    /**
     * 测试登录
     */
    private void getHttp() {
        OkGo.<HttpResult<LoginBean>>post(UrlFactory.LOGIN_URL)
                .params("username", "18356025758")
                .params("password", "123456q")
                .execute(new HttpsCallback<HttpResult<LoginBean>>() {
                    @Override
                    public void onError(String errMessage) {
                        Log.d("jiejie", "---" + errMessage);
                    }

                    @Override
                    public void onSuccess(Response<HttpResult<LoginBean>> response) {
                        if (response.body().getData() != null) {
                            Log.d("jiejie", response.body().getData().getToken());
                            getCoin(response.body().getData().getToken());
                        } else {
                            Log.d("jiejie", response.body().getCode() + "");
                        }
                    }
                });
    }

    /**
     * 获取钱包
     */
    private void getCoin(String token) {
        OkGo.<HttpResult<CoinBean>>post(UrlFactory.COIN_URL)
                .headers("x-auth-token", token)
                .execute(new HttpsCallback<HttpResult<CoinBean>>() {
                    @Override
                    public void onError(String errMessage) {
                        Log.d("jiejie", "---" + errMessage);
                    }

                    @Override
                    public void onSuccess(Response<HttpResult<CoinBean>> response) {
                    //    Log.d("jiejie", "----" + response.body().getData().getAddress());
                    }
                });
    }
}
