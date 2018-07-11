package item.com.demo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class GankModel implements Serializable {
    private static final long serialVersionUID = 6753210234564872868L;

    public String _id;
    public Date createdAt;
    public String desc;
    public List<String> images;
    public Date publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;
}
