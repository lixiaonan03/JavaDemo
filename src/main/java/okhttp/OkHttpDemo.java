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
//            response = postRequestForForm();
//            //异步get
//            for(int i=0;i<9;i++){
//                getRequestForEnqueue();
////                Thread.sleep(6000L);
////                getRequest();
//            }

              //get请求的
            response = postRequest();
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
        String url="https://paas-sdbx.arcusfi.com/v1/customers";
        String json ="{\n" +
                "    \"customer_attributes\": {\n" +
                "        \"date_of_birth\": \"1996-09-09\",\n" +
                "        \"email\": \"hetianqi486@gmail.com\",\n" +
                "        \"first_name\": \"he2\",\n" +
                "        \"last_name_1\": \"he2\",\n" +
                "        \"last_name_2\": \"he2\",\n" +
                "        \"middle_name\": \"tianqi2\"\n" +
                "    },\n" +
                "    \"customer_type\": \"user\",\n" +
                "    \"identifications\": [\n" +
                "        {\n" +
                "            \"country\": \"MEX\",\n" +
                "            \"expiration_date\": \"2096-09-09\",\n" +
                "            \"identification_type\": \"RFC\",\n" +
                "            \"issue_date\": \"1996-09-09\",\n" +
                "            \"number\": \"FME190412997\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        //这种默认请求的是get
        OkHttpClient client = OkHttpClientsUtils.getClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization","Bearer eyJraWQiOiJBVTRoVDdFSllxTERTRWs4S0pVa3FJVU9NS0RXMzBMQ1FuYld3RWZaMGg0PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJlYTFhZTVjZC04OWJkLTQxZmMtYTBmNC1hODY5NmQyNjBiMWEiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMV9VcWduTE1uSmciLCJjbGllbnRfaWQiOiIxMGRmbmdzaDlvNW9jOTJ0azA0b2NzYjJndiIsIm9yaWdpbl9qdGkiOiJmYjY0YzVkOC02MDI1LTQ1MzEtYTRiNy1iY2Y3YmZkNTYwZTIiLCJldmVudF9pZCI6ImI3ZGZiNWQ1LTJjYTUtNDQzNi04Yjg1LTA2ZjAyODQxNjNkMCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2OTgzOTU3MTUsImV4cCI6MTY5ODM5NjkxNCwiaWF0IjoxNjk4Mzk1NzE1LCJqdGkiOiI4YjE4MTU4OC1iNGQzLTRhYzQtYWRlMi0yYmEzZGQwMWRlY2UiLCJ1c2VybmFtZSI6IkNMSV9iYTNlMzAzODcxMTAifQ.sGInkE4jv40M_Be7IfYKQmSN5rR4mpzWgUq3l0cwrafZvwRUMi37dST6K2SPcuAVUJlrrPqfWDSNyH6seGB-mVl1RLpsyljYQKsQCuRU-r5o2tEy2U474o-Ua7xotazqUk8q5wcgK-YaDrXViQUcimu_q5D4WHCr__UvOigqlmsuUaTF6Zc2h9MjE_6biqNa3FvgHry3opKR-wSvv3IfCeo7s3pLLf1GVO3W5s5_S2wGEQgMLazXbaRSXjw-TR-0f72KGpg2yOS5O9eraLsXIHomorkVNNFEvIewo7uyylKQaMZk9ZXEcOneccWAVoZ5gDbIlx0PU6i4iBFp-p8bAw")
                .build();
        //同步请求的
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
}
