package retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
  *  @author lixiaonan
  *  功能描述: retrofit demo 测试的
  *  时 间： 2020/9/2 8:05 PM
  */
public class MainDemo {

    public static void main(String[] args){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:18080/")
                .build();

        GitHubService service = retrofit.create(GitHubService.class);


        Call<ResponseBody> repos = service.getDemo();

        //异步请求的
        System.out.println("开始的的get==="+Thread.currentThread().getName());
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("异步的get=end=="+Thread.currentThread().getName());
                System.out.println("异步的response==="+response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
