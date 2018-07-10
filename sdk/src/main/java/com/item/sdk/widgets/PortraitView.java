package com.item.sdk.widgets;

import android.content.Context;
import android.util.AttributeSet;
import com.item.sdk.R;
import com.item.sdk.global.GlideRequests;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wuzongjie on 2018/7/4
 * 头像的圆形View
 */
public class PortraitView extends CircleImageView{
    public PortraitView(Context context) {
        super(context);
    }

    public PortraitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PortraitView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setup(GlideRequests manager,String url){
        setup(manager,R.drawable.default_portrait,url);
    }

    public void setup(GlideRequests manager, int resourceId, String url){
        if(url == null) url = "";
        manager.load(url)
                .centerCrop()
                .placeholder(resourceId)
                .dontAnimate() // CircleImageView 控件中不能使用渐变动画，会导致显示延迟
                .into(this);
    }
}
