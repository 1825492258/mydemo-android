package com.item.sdk.widgets;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by wuzongjie on 2018/7/3
 * 等待的Dialog
 */
public class WaitProgressDialog extends ProgressDialog{

    public WaitProgressDialog(Context context) {
        this(context,0);
    }

    public WaitProgressDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
    }
}
