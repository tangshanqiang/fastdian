package hipad.com.latte.net;

/**
 * Created by tangshanqiang on 2017/12/5.
 */

import java.util.Map;
import java.util.WeakHashMap;

import hipad.com.latte.net.callback.IError;
import hipad.com.latte.net.callback.IFailure;
import hipad.com.latte.net.callback.IRequest;
import hipad.com.latte.net.callback.ISuccess;
import hipad.com.latte.net.callback.RequestCallbacks;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 网络请求的实现类
 */
public class RestClient {
    private final String URL;
    private final WeakHashMap<String ,Object> PARAMS =RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public RestClient(String URL,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = URL;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }
    public static RestClientBuilder builder(){//建造者模式
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service =RestCreator.getRestService();
        Call<String> call =null;
        if(REQUEST!=null){
            REQUEST.onResquestStart();
        }

        switch (method){
            case GET:
                call= service.get(URL,PARAMS);
                break;
            case POST:
                call= service.put(URL,PARAMS);
                break;
            case PUT:
                call= service.put(URL,PARAMS);
                break;
            case DELETE:
                call=  service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        if(call!=null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(REQUEST,SUCCESS,FAILURE,ERROR);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
