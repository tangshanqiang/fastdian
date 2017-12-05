package hipad.com.fastdian.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import hipad.com.fastdian.R;
import hipad.com.latte.delegates.LatteDelegate;

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

    }
}
