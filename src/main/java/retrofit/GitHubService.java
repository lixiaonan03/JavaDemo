package retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
  *  @author lixiaonan
  *  功能描述: 测试demo
  *  时 间： 2020/9/2 8:04 PM 
  */
public interface GitHubService {
    @GET("bat/hello2")
    Call<ResponseBody> getDemo();
}

