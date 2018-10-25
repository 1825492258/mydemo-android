package com.item.sdk.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.item.sdk.R;

/**
 * Created by wuzongjie on 2018/10/11
 * 这只是个弹窗
 */
public class ToastTipUtil {

    /**
     * 显示成功图标
     */
    public static final int ICON_TYPE_SUCCESS = 1;
    /**
     * 显示失败图标
     */
    public static final int ICON_TYPE_FAIL = 2;
    /**
     * 显示信息图标
     */
    public static final int ICON_TYPE_INFO = 3;
    /**
     * toast状态
     */

    private static Toast toast;
    public static void showTip(String msg,int type){
        showTip(msg,Toast.LENGTH_SHORT,type);
    }
    public static void showTip(String msg){
        showTip(msg,0);
    }
    /**
     * @param msg      提示msg
     * @param duration toast时间 单位毫秒
     * @param type     toast 类型
     */
    public static void showTip(String msg, int duration, int type) {
        Context appContext = AppUtils.getContext();
        if(toast == null) {
            toast = new Toast(appContext);
        }
        toast.setDuration(duration);
        View contentView = View.inflate(appContext, R.layout.tip_view_vertical, null);
        TextView infoTv = (TextView) contentView.findViewById(R.id.info);
        ImageView img = (ImageView) contentView.findViewById(R.id.img);
        infoTv.setText(msg);
        if (type == ICON_TYPE_SUCCESS) {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_done);
        } else if (type == ICON_TYPE_FAIL) {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_error);
        } else if (type == ICON_TYPE_INFO) {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_info);
        }else {
            img.setVisibility(View.GONE);
        }
        toast.setView(contentView);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
