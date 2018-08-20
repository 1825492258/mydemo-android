package item.com.demo.bean.db;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import java.util.Arrays;

/**
 * Created by wuzongjie on 2018/8/10
 * 数据库的辅助工具类
 */
public class DbHelper {

    private static final DbHelper instance;

    static {
        instance = new DbHelper();
    }
    private DbHelper(){}

    public static  <Model extends BaseModel> void save (final Class<Model> tClass, final Model... models){
        if(models == null || models.length == 0) return;
        // 当前数据库的一个管理者
        DatabaseDefinition definition = FlowManager.getDatabase(AppDataBase.class);
        // 提交一个书屋
        definition.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                // 执行
                ModelAdapter<Model> adapter = FlowManager.getModelAdapter(tClass);
                // 保存
                adapter.saveAll(Arrays.asList(models));
            }
        }).build().execute();
    }
}
