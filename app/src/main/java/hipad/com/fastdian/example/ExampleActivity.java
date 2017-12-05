package hipad.com.fastdian.example;

import hipad.com.latte.activities.ProxyActivity;
import hipad.com.latte.delegates.LatteDelegate;

/**
 * Created by tangshanqiang on 2017/12/4.
 */

public class ExampleActivity extends ProxyActivity{
    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
