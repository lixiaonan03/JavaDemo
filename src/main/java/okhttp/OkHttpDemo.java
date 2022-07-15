package okhttp;

import com.alibaba.fastjson.JSON;
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
            //异步调用请求的
            getRequestForEnqueue();

//            response = postRequestForForm();
//            //异步get
//            for(int i=0;i<9;i++){
//                getRequestForEnqueue();
////                Thread.sleep(6000L);
////                getRequest();
//            }

              //get请求的
            response = getRequest();
            System.out.println("response==="+response);
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
        String url="https://11test.wanwustore.cn/debug.html?id=12341";
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

    private static String postRequestForForm() throws Exception {
        String url="http://172.16.3.228:8081/test1/post";
        //这种默认请求的是get
        OkHttpClient client = OkHttpClientsUtils.getClient();
        Book book =new Book();
        book.setId("123");
        book.setName("测试李晓楠");
        RequestBody body = RequestBody.create(JSON, com.alibaba.fastjson.JSON.toJSONString(book));
        Request request;
        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        //同步请求的
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }


    /**
     * 异步get请求的
     * @throws Exception
     */
    private static void getRequestForEnqueue() throws Exception {
//        String url="http://172.16.3.228:18080/bat/hello2";
//        String url="https://wwdev.bangbangtown.cn/o/ub/5.0/uiww/myUserCenter";
        String url="https://pic.wanwustore.cn/db7f94f6eb5ff3009036d3e047c7ba88";
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
