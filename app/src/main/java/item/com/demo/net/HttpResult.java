package item.com.demo.net;

import java.io.Serializable;

/**
 * Created by wuzongjie on 2018/7/4
 *  网络请求回调的统一实体
 */
public class HttpResult<T> implements Serializable{

    private int code; // 返回的状态码
    private String message; // 服务器返回的信息
    private T data; // 返回的数据内容，类型不确定，使用泛型T表示

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
