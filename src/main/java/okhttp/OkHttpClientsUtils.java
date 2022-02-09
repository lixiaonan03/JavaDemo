package okhttp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.LoggingEventListener;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 作者：李晓楠
 * okhttpcilent的封装
 * 时间：2017/3/10 14:31
 */
public class OkHttpClientsUtils {
    private static OkHttpClient client;

    public static OkHttpClient getClient() {
        if (client == null) {
            synchronized (OkHttpClientsUtils.class) {
                if (client == null) {
                    //添加公共参数和头
                    Map<String, String> map = new HashMap<>();
                    //2 是安卓
                    map.put("clientType", "2");
//                    map.put("Connection", "close");
                    BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor
                            .Builder()
//                            .addHeaderParamsMap(map)
//                            .addHeaderParam("token",PreferencesSetGetUtil.getToken())
//                            .addParam("token",PreferencesSetGetUtil.getToken())
                            .addParamsMap(map)
                            .addQueryParamsMap(map)
                            .build();

                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            System.out.println(message);
                        }
                    });
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);



                    client = new OkHttpClient.Builder()
                            //错误重连
//                            .retryOnConnectionFailure(false)
                            //连接超时时间
                            .connectTimeout(160, TimeUnit.SECONDS)
                            .readTimeout(160, TimeUnit.SECONDS)
                            .writeTimeout(160, TimeUnit.SECONDS)
//                            .addInterceptor(new CommonInterceptor())
                            .addInterceptor(basicParamsInterceptor)
                            //addnetWorkIntercceptor 打印不出来接口返回数据
                            //需要改成addinterceptor
                            .addInterceptor(logging)
                            .eventListener(new PrintingEventListener())
//                            .eventListenerFactory(HttpEventListener.FACTORY)
//                            .eventListenerFactory(new LoggingEventListener.Factory())
                            .hostnameVerifier(new HostnameVerifier() {
                                @Override
                                public boolean verify(String hostname, SSLSession session) {
                                    //认证证书的
                                    return true;
                                }
                            })
                            .dns(new DnsDemo())
                            .build();
                }
            }
        }
        return client;
    }

    /**
     * 制空client对象重新创立
     */
    public static void removeClient() {
        client = null;
    }
}
