package com.item.sdk.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.item.sdk.R;

/**
 * Created by wuzongjie on 2018/7/5
 * 选择头像的dialog
 */
public class SelectPortraitDialog extends Dialog {

    private DisplayMetrics dm; // 屏幕分辨率
    private IPortraitInterface mInterface;

    public IPortraitInterface getInterface() {
        return mInterface;
    }

    public void setInterface(IPortraitInterface mInterface) {
        this.mInterface = mInterface;
    }

    public SelectPortraitDialog(@NonNull Context context) {
        super(context, R.style.SheetDialogStyles);
        dm = context.getResources().getDisplayMetrics();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_portrait_layout);
        initView();
    }

    private void initView() {
        // 设置这个Dialog从底部弹出
        Window dialogWindow = getWindow();
        assert dialogWindow != null;
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = dm.widthPixels;
        dialogWindow.setAttributes(lp);

        TextView tvOne = findViewById(R.id.dialog_portrait_one);
        TextView tvTwo = findViewById(R.id.dialog_portrait_two);
        TextView tvCancel = findViewById(R.id.dialog_portrait_cancel);
        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterface != null) mInterface.onTakeOne();
                dismiss();
            }
        });
        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterface != null) mInterface.onTakeTwo();
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
//        tvTwo.setOnClickListener(this);
//        tvCancel.setOnClickListener(this);
    }


    public interface IPortraitInterface {
        void onTakeOne();

        void onTakeTwo();
    }
}
