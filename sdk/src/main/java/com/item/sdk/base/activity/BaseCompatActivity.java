package com.item.sdk.base.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.inputmethod.InputMethodManager;

import com.item.sdk.AppManager;
import com.item.sdk.base.IBaseView;
import com.item.sdk.global.GlobalApplication;
import com.item.sdk.utils.AppUtils;
import com.item.sdk.utils.SPManagerUtils;
import com.item.sdk.utils.ThemeUtils;
import com.item.sdk.widgets.WaitProgressDialog;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by wuzongjie on 2018/7/3
 * BaseActivity
 */
public abstract class BaseCompatActivity extends SupportActivity {

    protected WaitProgressDialog mWaitDialog;
    protected Context mContext;
    protected boolean isTransAnim;

    static {
        //5.0以下兼容vector
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * Activity创建
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    /**
     * Activity销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // fragment 切换使用默认Vertical动画
        return new DefaultVerticalAnimator();
    }

    private void init(Bundle savedInstanceState) {
        // 可以设置Theme(也可以不用)
        setTheme(ThemeUtils.themeArr[SPManagerUtils.getThemeIndex(this)]
                [SPManagerUtils.getNightModel(this) ? 1 : 0]);
        // 设置布局
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 强制为竖屏
        initData();
        initView(savedInstanceState);
        // 将Activity添加到栈
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 获取当前layouty的布局ID,用于设置当前布局
     * <p>
     * 交由子类实现
     *
     * @return layout Id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     * <p>
     * 子类实现 控件绑定、视图初始化等内容
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     * 子类可以复写此方法初始化子类数据
     */
    protected void initData() {
        mContext = AppUtils.getContext();
        isTransAnim = true;
    }

    /**
     * 显示提示框
     *
     * @param msg 提示框内容字符串
     */
    protected void showProgressDialog(String msg) {
        if (mWaitDialog == null) mWaitDialog = new WaitProgressDialog(this);
        mWaitDialog.setMessage(msg);
        mWaitDialog.show();
    }

    /**
     * 隐藏提示框
     */
    protected void hideProgressDialog() {
        if (mWaitDialog != null && mWaitDialog.isShowing()) mWaitDialog.dismiss();
    }

    /**
     * 隐藏键盘
     *
     * @return 隐藏键盘结果
     * <p>
     * true:隐藏成功
     * <p>
     * false:隐藏失败
     */
    protected boolean hiddenKeyboard() {
        //点击空白位置 隐藏软键盘
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);
        assert mInputMethodManager != null;
        return mInputMethodManager.hideSoftInputFromWindow(this
                .getCurrentFocus().getWindowToken(), 0);
    }
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
