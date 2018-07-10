package com.item.sdk.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;

import com.item.sdk.R;

/**
 * Created by wuzongjie on 2018/7/5
 * Theme 的选择
 */
public class ThemeUtils {

    public static int[][] themeArr = {
            {R.style.AppThemeLight_Red, R.style.AppThemeDark_Red} ,
            {R.style.AppThemeLight_LightBlue, R.style.AppThemeDark_LightBlue},
            {R.style.AppThemeLight_Grey, R.style.AppThemeDark_Grey}
    };


     private static int[][] themeColorArr = {
            {R.color.md_red_500, R.color.md_red_700},
//            {R.color.md_pink_500, R.color.md_pink_700},
//            {R.color.md_purple_500, R.color.md_purple_700},
//            {R.color.md_deep_purple_500, R.color.md_deep_purple_700},
//            {R.color.md_indigo_500, R.color.md_indigo_700},
//            {R.color.md_blue_500, R.color.md_blue_700},
            {R.color.md_light_blue_500, R.color.md_light_blue_700},
//            {R.color.md_cyan_500, R.color.md_cyan_700},
//            {R.color.md_teal_500, R.color.md_teal_500},
//            {R.color.md_green_500, R.color.md_green_500},
//            {R.color.md_light_green_500, R.color.md_light_green_500},
//            {R.color.md_lime_500, R.color.md_lime_700},
//            {R.color.md_yellow_500, R.color.md_yellow_700},
//            {R.color.md_amber_500, R.color.md_amber_700},
//            {R.color.md_orange_500, R.color.md_orange_700},
//            {R.color.md_deep_orange_500, R.color.md_deep_orange_700},
//            {R.color.md_brown_500, R.color.md_brown_700},
            {R.color.md_grey_500, R.color.md_grey_700},
//            {R.color.md_blue_grey_500, R.color.md_blue_grey_700}
    };

    public static int getTheme(Context context) {
        return context.getResources()
                .getColor(themeColorArr[SPManagerUtils.getThemeIndex(context)][0]);
    }

    public static int getThemeColor(@NonNull Context context) {
        return getThemeAttrColor(context, R.attr.colorPrimary);
    }

    private static int getThemeAttrColor(@NonNull Context context, @AttrRes int attr) {
        TypedArray a = context.obtainStyledAttributes(null, new int[]{attr});
        try {
            return a.getColor(0, 0);
        } finally {
            a.recycle();
        }
    }
}
