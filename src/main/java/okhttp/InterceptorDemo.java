package okhttp;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

/**
 * 拦截器demo
 */
public class InterceptorDemo implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
