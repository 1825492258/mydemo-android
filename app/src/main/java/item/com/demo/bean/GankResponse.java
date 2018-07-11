package item.com.demo.bean;

import java.io.Serializable;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class GankResponse<T> implements Serializable {
    private static final long serialVersionUID = -686453405647539973L;

    public boolean error;
    public T results;
}
