package hipad.com.latte.app;

import android.app.Application;

/**
 * Created by tangshanqiang on 2017/12/4.
 */

public class CoreApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withAPIHost("http://127.0.0.1")
                .configure();
    }
}
