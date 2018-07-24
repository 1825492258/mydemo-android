package item.com.demo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by wuzongjie on 2018/7/23
 * 多布局的实体类必须实现MultiItemEntity，在设置数据的时候，需要给每一个数据设置itemType
 */
public class MyTextItem implements MultiItemEntity {

    private int itemType;
    private String name;
    private List<String> item;
    public static final int ONE = 1;
    public static final int TWO = 2;

    public MyTextItem(int itemType, String name) {
        this.itemType = itemType;
        this.name = name;
    }

    public MyTextItem(int itemType, String name, List<String> item) {
        this.itemType = itemType;
        this.name = name;
        this.item = item;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
