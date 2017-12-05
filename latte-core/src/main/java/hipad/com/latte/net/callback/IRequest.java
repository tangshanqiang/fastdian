package hipad.com.latte.net.callback;

/**
 * Created by tangshanqiang on 2017/12/5.
 */

/**
 * 请求开始结束的回调
 */
public interface IRequest {
    void onResquestStart();//请求开始（一般会做一些给用户看到旋转的图片，正在请求的）
    void onResquestEnd();//请求结束，让旋转的图片消失的一些操作
}
