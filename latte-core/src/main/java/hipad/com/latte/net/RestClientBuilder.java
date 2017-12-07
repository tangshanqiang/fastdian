package hipad.com.latte.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import hipad.com.latte.net.callback.IError;
import hipad.com.latte.net.callback.IFailure;
import hipad.com.latte.net.callback.IRequest;
import hipad.com.latte.net.callback.ISuccess;
import hipad.com.latte.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.R.attr.value;

/**
 * Created by tangshanqiang on 2017/12/5.
 */

/**
 * RestClient建造者模式 传值
 */
public class RestClientBuilder {
    private  String mUrl = null;
    private  WeakHashMap<String ,Object> mParams =RestCreator.getParams();
    private  IRequest mRequest = null;
    private  ISuccess mSuccess = null;
    private  IFailure mFailure = null;
    private  IError mError = null;
    private  RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

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

    public final RestClientBuilder file(String key, Object value){
        this.mParams.put(key,value);
        return this;
    }
    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }


    private Map<String,Object> checkParams(){
        if(mParams == null){
            return new WeakHashMap<String,Object>();
        }
        return mParams;
    }
    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }
    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public final  RestClient build(){
        return new RestClient(mUrl,mParams,mDownloadDir,mExtension, mName,mRequest,mSuccess,mFailure,mError,mBody,mFile,mContext,mLoaderStyle);
    }

}
