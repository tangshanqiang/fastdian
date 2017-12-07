package hipad.com.latte.net;


import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import hipad.com.latte.app.Config_Type;
import hipad.com.latte.app.Latte;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tangshanqiang on 2017/12/5.
 */

public class RestCreator {


    private static final class ParamsHolder{//懒加载
        public static final WeakHashMap<String,Object> PARAMS =new WeakHashMap<String,Object>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    //建造者模式 符合配置参数较多的类
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfigurations().get(Config_Type.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder{
        private  static final int TIME_OUT =60;
        private  static OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(Config_Type.INTERCEPTOR);
        private static OkHttpClient.Builder addIntercepter(){
            if(INTERCEPTORS !=null && !INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor : INTERCEPTORS){
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT =addIntercepter()//new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }

    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

    }
}
