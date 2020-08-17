package okhttp;

import okhttp3.*;

import java.io.IOException;

/**
  *  @author lixiaonan
  *  功能描述: OKHttp使用的Demo
  *  时 间： 2020/8/14 6:12 PM 
  */
public class OkHttpDemo {

    public static void main(String[] args){
        System.out.println("测试的");
        String response = null;
        try {
            //异步get
            getRequestForEnqueue();
              //get请求的
//            response = getRequest();
            //post 请求的
//            response = postRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("response==="+response);
    }

    /**
     * get请求的
     * @return
     */
    private static String getRequest() throws Exception {
        String url="http://localhost:18080/bat/hello2";
        //这种默认请求的是get
        OkHttpClient client = OkHttpClientsUtils.getClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        //
        Response response = call.execute();
        return response.body().string();
    }

    /**
     * 异步get请求的
     * @throws Exception
     */
    private static void getRequestForEnqueue() throws Exception {
        String url="http://localhost:18080/bat/hello2";
        //这种默认请求的是get
        OkHttpClient client = OkHttpClientsUtils.getClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        System.out.println("异步的get=Start=="+Thread.currentThread().getName());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("异步的get=end=="+Thread.currentThread().getName());
                System.out.println("异步的response==="+response.body().string());
            }
        });
    }


    /**
     * 同步post
     */
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static String postRequest() throws Exception {
        String url="http://localhost:18080/bat/post";
        String json ="{\"id\":\"12\"}";
        //这种默认请求的是get
        OkHttpClient client = OkHttpClientsUtils.getClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        //同步请求的
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
}
