package item.com.demo.bean.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by wuzongjie on 2018/8/10
 */
@Table(database = AppDataBase.class)
public class Ant  extends BaseModel implements Serializable {
    // 主键
    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    private String name;

    public Ant() {
    }

    public Ant(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
