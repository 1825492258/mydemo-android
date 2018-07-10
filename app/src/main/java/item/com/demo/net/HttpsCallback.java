package item.com.demo.net;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by wuzongjie on 2018/7/9
 */
public abstract class HttpsCallback<T> extends AbsCallback<T> {

    /**
     * 子线程，可以做耗时操作
     * 根据传递进来的response对象，把数据解析成需要的ServerModel类型并返回
     * 可以根据自己的需要抛出异常，在onError中处理
     */
    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        // 以下代码是通过泛型解析实际参数，泛型必须传
        //以下代码是通过泛型解析实际参数,泛型必须传
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");
        assert response.body() != null;
        JsonReader jsonReader = new JsonReader(response.body().charStream());
        Type rawType = ((ParameterizedType) type).getRawType();
        if (rawType == HttpResult.class) {
            HttpResult httpResult = new Gson().fromJson(jsonReader, type);
            if (httpResult.getCode() == 4000) {
                response.close();
                throw new IllegalStateException("会话失效");
            } else {
                response.close();
                // noinspection unchecked
                return (T) httpResult;
            }
        } else {
            response.close();
            throw new IllegalStateException("基类错误无法解析!");
        }

    }

//    /**
//     * UI 线程，请求成功后的回调
//     */
//    @Override
//    public void onSuccess(Response<T> response) {
//
//    }

    /**
     * UI 线程，请求失败的回调
     */
    @Override
    public void onError(Response<T> response) {
        Throwable exception = response.getException();
        if (exception != null) exception.printStackTrace();
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            onError("网络连接失败，请连接网络");
        } else if (exception instanceof SocketTimeoutException) {
            onError("网络请求超时");
        } else if (exception instanceof HttpException) {
            onError("服务器端异常，返回404或500");
        } else if (exception instanceof StorageException) {
            onError("sd卡不存在或者没有权限");
        } else if (exception instanceof IllegalStateException) {
            String message = exception.getMessage();
            if ("会话失效".equals(message)) {
                // 这里应该做会话失效的处理
                onError("会话失效");
            } else {
                onError(message);
            }
        } else {
            onError("未知错误");
        }
    }

    public abstract void onError(String errMessage);
}
