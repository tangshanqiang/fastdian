package hipad.com.latte.app;

/**
 * Created by tangshanqiang on 2017/12/4.
 */

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * 全局的配置文件
 */
public class Configurator {
    //WeakHashMap当存储满的时候会自动释放
    private static final WeakHashMap<String,Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private Configurator(){
        //初始化
        LATTE_CONFIGS.put(Config_Type.CONFIG_READY.name(),false);//未初始化
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }
    private static class Holder {
        //单例模式 懒汉模式
        private static final Configurator INSTANCE = new Configurator();
    }
        /**
         * 配置完成
         */
        public void configure(){
            initIcons();
            LATTE_CONFIGS.put(Config_Type.CONFIG_READY.name(),true);//初始化 配置完成
        }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    

    /**
         * 配置API host
         * @param host
         * @return
         */
        public final Configurator withAPIHost(String host){
            LATTE_CONFIGS.put(Config_Type.API_HOST.name(),host);
            return Configurator.getInstance();
        }

    public final Configurator withInterceptor(Interceptor interceptor){
        LATTE_CONFIGS.put(Config_Type.INTERCEPTOR.name(),interceptor);
        INTERCEPTORS.add(interceptor);

        return Configurator.getInstance();
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(Config_Type.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

        /**
         * 做一些配置检查
         */
        public void checkConfiguration(){
            final boolean isReady =(boolean)LATTE_CONFIGS.get(Config_Type.CONFIG_READY.name());
            if(!isReady){
                throw new RuntimeException("Configuration is not ready ,call Configuration ");
            }

        }

        /**
         * 获取配置
         * @param key
         * @param <T>
         */
        final <T> T getConfiguration(Enum<Config_Type> key){
            checkConfiguration();
            return (T) LATTE_CONFIGS.get(key.name());
        }
}
