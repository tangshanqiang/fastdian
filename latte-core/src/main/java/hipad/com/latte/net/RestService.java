package hipad.com.latte.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by tangshanqiang on 2017/12/5.
 */

/**
 * retrofit 发送请求接口服务
 */
public interface RestService {
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);


    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url,@FieldMap Map<String,Object> params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url,@FieldMap Map<String,Object> params);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> params);

    @Streaming//下载会一次性写到内存里再一次性写到文件中，如果文件比较大内存会溢出，使用此注解可以边下载边缓存到本地
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);//下载返回的是一个body

    @Multipart
    @POST
    Call<String> upload(@Url String url,@Part MultipartBody.Part file);

}
