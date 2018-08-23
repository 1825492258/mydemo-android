package item.com.demo.bean;

import java.io.Serializable;

/**
 * Created by wuzongjie on 2018/7/11
 */
public class GankResponse<T> implements Serializable {
    private static final long serialVersionUID = -686453405647539973L;

    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
