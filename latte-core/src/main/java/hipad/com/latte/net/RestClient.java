package hipad.com.latte.net;

/**
 * Created by tangshanqiang on 2017/12/5.
 */

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import hipad.com.latte.net.callback.IError;
import hipad.com.latte.net.callback.IFailure;
import hipad.com.latte.net.callback.IRequest;
import hipad.com.latte.net.callback.ISuccess;
import hipad.com.latte.net.callback.RequestCallbacks;
import hipad.com.latte.net.download.DownloadHandler;
import hipad.com.latte.ui.LatteLoader;
import hipad.com.latte.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private final String DOWNLOADDIR  ;
    private final String EXTENSION ;
    private final String NAME ;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;


    public RestClient(String URL,
                      Map<String, Object> params,
                      String downloaddir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = URL;
        this.PARAMS.putAll(params);
        this.DOWNLOADDIR = downloaddir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
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

        if(LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method){
            case GET:
                call= service.get(URL,PARAMS);
                break;
            case POST:
                call= service.put(URL,PARAMS);
                break;
            case POST_RAW:
                call= service.postRaw(URL,BODY);
                break;
            case PUT:
                call= service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call= service.putRaw(URL,PARAMS);
                break;
            case DELETE:
                call=  service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =MultipartBody.Part.createFormData("file",FILE.getName());
                call = RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;
        }
        if(call!=null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(REQUEST,SUCCESS,FAILURE,ERROR,LOADER_STYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        if(BODY == null){
            request(HttpMethod.POST);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must null");
            }
        }
        request(HttpMethod.POST_RAW);

    }
    public final void put(){
        if(BODY == null){
            request(HttpMethod.PUT);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must null");
            }
        }
        request(HttpMethod.PUT_RAW);

    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void download(){
        new DownloadHandler(URL,REQUEST,DOWNLOADDIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR)
        .handleDownload();

    }

}
