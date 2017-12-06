package hipad.com.fastdian.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import hipad.com.fastdian.R;
import hipad.com.latte.delegates.LatteDelegate;
import hipad.com.latte.net.RestClient;
import hipad.com.latte.net.callback.IError;
import hipad.com.latte.net.callback.IFailure;
import hipad.com.latte.net.callback.ISuccess;

/**
 * Created by tangshanqiang on 2017/12/4.
 */

public class ExampleDelegate extends LatteDelegate{
    @Override
    public LatteDelegate setRootDelegate() {
        return null;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                       // Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(),"失败",Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(),"错误",Toast.LENGTH_LONG).show();

                    }
                })
                .build()
                .get();
    }
}
