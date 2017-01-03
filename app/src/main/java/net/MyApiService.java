package net;

import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/27 0027.
 * Author by RuiGan
 */

public interface MyApiService {

    public String apiHost = "http://120.25.237.16:8011/";   //正式
//	public String apiHost = "http://192.168.1.132:8011/";   //测试
//  public String apiHost = "http://120.25.121.111:8011/";  //测试


    @POST
    Observable<ResponseBody> getJSON(@Url String url);
}
