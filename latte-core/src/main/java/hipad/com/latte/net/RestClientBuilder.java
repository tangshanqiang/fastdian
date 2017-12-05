package hipad.com.latte.net;

import java.util.Map;
import java.util.WeakHashMap;

import hipad.com.latte.net.callback.IError;
import hipad.com.latte.net.callback.IFailure;
import hipad.com.latte.net.callback.IRequest;
import hipad.com.latte.net.callback.ISuccess;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by tangshanqiang on 2017/12/5.
 */

/**
 * RestClient建造者模式 传值
 */
public class RestClientBuilder {
    private  String mUrl;
    private  WeakHashMap<String ,Object> mParams =RestCreator.getParams();
    private  IRequest mRequest;
    private  ISuccess mSuccess;
    private  IFailure mFailure;
    private  IError mError;
    private  RequestBody mBody;

    RestClientBuilder(){}//不允许外部的修改

    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }
    public final RestClientBuilder param(WeakHashMap<String,Object> params){
        this.mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder param(String key,Object value){
        this.mParams.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody =RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuilder onRequest(IRequest request){
        this.mRequest=request;
        return this;
    }

    public final RestClientBuilder success(ISuccess success){
        this.mSuccess=success;
        return this;
    }
    public final RestClientBuilder failure(IFailure failure){
        this.mFailure=failure;
        return this;
    }
    public final RestClientBuilder error(IError error){
        this.mError=error;
        return this;
    }

    private Map<String,Object> checkParams(){
        if(mParams == null){
            return new WeakHashMap<String,Object>();
        }
        return mParams;
    }

    public final  RestClient build(){
        return new RestClient(mUrl,mParams,mRequest,mSuccess,mFailure,mError,mBody);
    }

}
