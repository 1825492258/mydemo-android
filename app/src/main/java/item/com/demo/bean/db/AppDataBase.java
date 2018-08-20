package item.com.demo.bean.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by wuzongjie on 2018/8/10
 * 配置数据库相关参数
 */
@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION)
public class AppDataBase {
    // 数据库的名称
    public static final String NAME = "AppDatabase";
    // 数据库的版本号
    public static final int VERSION = 1;
}
