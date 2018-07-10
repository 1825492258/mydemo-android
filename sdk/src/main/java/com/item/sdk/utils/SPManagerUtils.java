package com.item.sdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

/**
 * SharedPreferences工具类封装
 */
public class SPManagerUtils {
    
    private static SharedPreferences sp = null;

    /**
     * 默认的文件名
     */
    private static String mPreferencesName = "share_preference_default";

    @SuppressLint("CommitPrefEdits")
    private SPManagerUtils(){
        sp = AppUtils.getContext().getSharedPreferences(mPreferencesName, Context.MODE_PRIVATE);

    }
    private static SPManagerUtils mInstance  = null;

    public static SPManagerUtils getInstance(){
        if(mInstance == null || sp == null) {
            synchronized (SPManagerUtils.class){
                if(mInstance==null || sp == null) {
                    mInstance = new SPManagerUtils();
                }
            }
        }
        return mInstance;
    }
    /**
     * 设置preferencesName
     *
     * @param preferencesName preferencesName
     */
    private void setPreferencesName(String preferencesName) {
        mPreferencesName = preferencesName;
    }

    /**
     *
     * @param key 存储节点名称
     * @param value 存储节点的值
     */
    public void putBoolean(String key,boolean value){
        sp.edit().putBoolean(key, value).apply();
    }


    /**
     * 读取boolean标示从sp中
     *
     * @param key      存储节点名称
     * @param defValue 没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    public  boolean getBoolean( String key, boolean defValue) {
        //(存储节点文件名称,读写方式)
        return sp.getBoolean(key, defValue);
    }


    /**
     * 写入String变量至sp中
     *
     * @param key   存储节点名称
     * @param value 存储节点的值
     */
    public  void putString( String key, String value) {
        //(存储节点文件名称,读写方式)
        sp.edit().putString(key, value).apply();
    }

    /**
     * 读取String标示从sp中
     *
     * @param key      存储节点名称
     * @param defValue 没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    public  String getString(String key, String defValue) {
        //(存储节点文件名称,读写方式)
        return sp.getString(key, defValue);
    }


    /**
     * 写入int变量至sp中
     *
     * @param key   存储节点名称
     * @param value 存储节点的值
     */
    public  void putInt( String key, int value) {
        //(存储节点文件名称,读写方式)

        sp.edit().putInt(key, value).apply();
    }

    /**
     * 读取int标示从sp中
     *
     * @param key      存储节点名称
     * @param defValue 没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    public  int getInt( String key, int defValue) {
        //(存储节点文件名称,读写方式)
        return sp.getInt(key, defValue);
    }


    /**
     * 从sp中移除指定节点
     *
     * @param key 需要移除节点的名称
     */
    public  void remove( String key) {
        sp.edit().remove(key).apply();
    }

    /**
     * 保存List
     *
     * @param key      sp key值
     * @param datalist list
     * @param <T>      item 类型
     */
    public  <T> void setDataList(String key, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        SPManagerUtils.getInstance().putString(key,strJson);
    }

    /**
     * 获取List
     *
     * @param key sp key值
     * @param <T> item 类型
     * @return list
     */
    public static <T> List<T> getDataList(String key, Class<T> cls) {
        List<T> datalist = new ArrayList<T>();
        String strJson = SPManagerUtils.getInstance().getString( key, null);

        if (null == strJson) {
            return datalist;
        }

        try {
            Gson gson = new Gson();
            //        datalist = gson.fromJson(strJson, new TypeToken<List<T>>(){}.getType());
            JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
            for (final JsonElement elem : array) {
                datalist.add(gson.fromJson(elem, cls));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return datalist;
    }

    public static int getThemeIndex(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt("ThemeIndex", 0);
    }

    public static void setThemeIndex(Context context, int index) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt("ThemeIndex", index).apply();
    }

    public static boolean getNightModel(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pNightMode", false);
    }

    public static void setNightModel(Context context, boolean nightModel) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("pNightMode", nightModel).apply();
    }
}
