package com.item.sdk.base.activity;

import com.item.sdk.base.IBaseView;

/**
 * Created by wuzongjie on 2018/7/5
 */
public abstract class BaseMVPCompatActivity extends BaseCompatActivity implements IBaseView{

    @Override
    public void showWaitDialog(String waitMsg) {
        showProgressDialog(waitMsg);
    }

    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }
}
