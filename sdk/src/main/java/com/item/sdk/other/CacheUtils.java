package com.item.sdk.other;

import android.content.Context;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by wuzongjie on 2018/7/5
 */
public class CacheUtils {
   private Context context;

    public CacheUtils(Context context) {
        this.context = context;
    }

    /**
     * 获取缓存
     */
    public   String getCacheSizeString() {
        long size = getFolderSize(new File(context.getCacheDir().getAbsolutePath()));
        return formatFileSize(size);
    }

    /**
     * 清楚缓存
     */
    private boolean clearCache() {
        return deleteDir(new File(context.getCacheDir().getAbsolutePath()));
    }

    private boolean deleteDir(File dir) {
        if (dir == null || !dir.exists())
            return false;

        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    private long getFolderSize(File file) {
        if (!file.exists()) {
            return 0;
        }
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    private String formatFileSize(double size) {
        double kByte = size / 1024;
        if (kByte < 1) {
            return size + "B";
        }
        double mByte = kByte / 1024;
        if (mByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gByte = mByte / 1024;
        if (gByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(mByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double tByte = gByte / 1024;
        if (tByte < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(tByte);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
