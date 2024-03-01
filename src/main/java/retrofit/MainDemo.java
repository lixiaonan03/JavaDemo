package retrofit;

import okhttp.PrintingEventListener;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

/**
  *  @author lixiaonan
  *  功能描述: retrofit demo 测试的
  *  时 间： 2020/9/2 8:05 PM
  */
public class MainDemo {

    public static void main(String[] args){
        setSSLTest();
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


    private static void setSSLTest(){
        String url = "https://mexico-gateway.carteracredito.mx/";
        //这种默认请求的是get
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.eventListener(new PrintingEventListener());
        // 日志拦截器，用来记录所有的网络请求和响应
        // 这个拦截器应该放到所有拦截器的最后，用来监听真正的请求/响应
        // FIXME 在传入一个错误的公钥的时候 访问日志能打印出来 异常信息并携带出来争取的sha256 值 类似这样的
        //            Peer certificate chain:   //域名证书、中间证书、根证书的值
        //            sha256/K29eS30xtD6XoGqBrCbYYlgnC4vA8iLN6Ozwi0cuycI=: CN=*.wanwustore.cn,O=安鼎睿科技（北京）有限公司,ST=北京市,C=CN
        //            sha256/B2o6KjBMvs+b5LG04FOX1d+Dw82KgV4aPdu+Q6Wq764=: CN=WoTrus OV Server CA  [Run by the Issuer],O=WoTrus CA Limited,C=CN
        //            sha256/x4QzPSC810K5/cMjb05Qm4k3Bw5zBn4lTdO/nEW/Td4=: CN=USERTrust RSA Certification Authority,O=The USERTRUST Network,L=Jersey City,ST=New Jersey,C=US
        //            Pinned certificates for pic.wanwustore.cn:
        //            sha256/K19eS30xtD6XoGqBrCbYYlgnC4vA8iLN6Ozwi0cuycI=     //传入错误的
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> {
            System.out.println("lxnNet==="+message);
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        builder.certificatePinner(new CertificatePinner.Builder()
                //注意这个地方只能 加入 sha256 或者 sha1 算法的摘要代码里面有判断    并且如果 network_security_config 这个里面配置对了,这个地方配置错误也会访问错误
                .add("mexico-gateway.carteracredito.mx", "sha256/K19eS30xtD6XoGqBrCbYYlgnC4vA8iLN6Ozwi0cuycI=").build());
        OkHttpClient client = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("net==="+Thread.currentThread().getName() + "失败的====" + call.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                System.out.println("lxn123==="+"当期的线程的===" + Thread.currentThread().getName());
                System.out.println("lxn123==="+Thread.currentThread().getName() + "==成功的====" + response.toString());
            }
        });

    }
}
