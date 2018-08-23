package item.com.demo.bean;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class Msg {
    private String message;

    public Msg(){}

    public Msg(String msg){
        message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
