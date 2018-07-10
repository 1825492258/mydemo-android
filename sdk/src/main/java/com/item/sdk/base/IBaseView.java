package com.item.sdk.base;

/**
 * Created by wuzongjie on 2018/7/5
 */
public interface IBaseView {
    /**
     * 显示等待dialog
     *
     * @param waitMsg 等待消息字符串
     */
    void showWaitDialog(String waitMsg);

    /**
     * 隐藏等待dialog
     */
    void hideWaitDialog();
}
