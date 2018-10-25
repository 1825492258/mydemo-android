package item.com.demo.bean.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuzongjie on 2018/8/10
 * 定义你的表格
 */
@Table(database = AppDataBase.class)
public class Group extends BaseModel implements Serializable{
    /*
    对类添加@Table注解
    声明所连接的数据库类，AppDataBase。
    定义至少一个主键。
    这个类和这个类中数据库相关列的修饰符必须是包内私有或者public。
    这样生成的_Adapter类能够访问到它
    列（Column）属性可以是private，但这样就必须指定公有public的getter和setter方法
     */
    @PrimaryKey(autoincrement = true)
    private long id; // ID
    @Column
    private String name; // 名称
    @Column
    private int notifyLeval; // 级别
    @Column
    @ForeignKey(tableClass = User.class,saveForeignKeyModel = true)
    private User user; // 定义一个外键
    //这里是设置为one-one的形式，切记不要加stubbedRelationship = true，要不然后期只能查询id，其他值查不出来

   private List<Ant> ants;

    public List<Ant> getAnts() {
        return ants;
    }

    public void setAnts(List<Ant> ants) {
        this.ants = ants;
    }

    @OneToMany(methods = {OneToMany.Method.SAVE}, variableName = "ants")
    public List<Ant> getMyAnts() {
        if (ants == null || ants.isEmpty()) {
            ants = SQLite.select()
                    .from(Ant.class)
                 //   .where(Ant_Table.id.eq(id))
                    .queryList();
        }
        return ants;
    }


    public Group() {
    }

    public Group(String name, int notifyLeval, User user) {
        this.name = name;
        this.notifyLeval = notifyLeval;
        this.user = user;
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

    public int getNotifyLeval() {
        return notifyLeval;
    }

    public void setNotifyLeval(int notifyLeval) {
        this.notifyLeval = notifyLeval;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", notifyLeval=" + notifyLeval +
                ", user=" + user +
                ", ants=" + ants +
                '}';
    }
}
