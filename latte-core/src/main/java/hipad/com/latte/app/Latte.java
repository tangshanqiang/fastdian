package hipad.com.latte.app;

import android.app.Application;
import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by tangshanqiang on 2017/12/4.
 */

public class Latte {
    public static Configurator init(Context context){
        getConfigurations().put(Config_Type.APPLICATION_CONTEXT.name(),context.getApplicationContext());//保存application实例
        return Configurator.getInstance();
    }

    /**
     * 获取全部的配置
     * @return
     */
    private static WeakHashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Application)getConfigurations().get(Config_Type.APPLICATION_CONTEXT.name());
    }
}
