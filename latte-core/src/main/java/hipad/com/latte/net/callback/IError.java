package hipad.com.latte.net.callback;

/**
 * Created by tangshanqiang on 2017/12/5.
 */

/**
 * 请求错误的回调
 */
public interface IError {
    void onError(int code,String msg);
}
