package cn.doumi.mvpdemo.retrofitrxjava;

import android.text.TextUtils;

import com.jakewharton.rxbinding.internal.Preconditions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.doumi.mvpdemo.base.BaseApplication;
import cn.doumi.mvpdemo.interceptor.towInterceptor;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class OkHttpProvider {

    private final static long DEFAULT_CONNECT_TIMEOUT = 10;
    private final static long DEFAULT_WRITE_TIMEOUT = 30;
    private final static long DEFAULT_READ_TIMEOUT = 30;

    public static OkHttpClient getDefaultOkHttpClient() {
        return getOkHttpClient(new CacheControlInterceptor());
    }


    public static OkHttpClient getOkHttpClient() {
        return getOkHttpClient(new towInterceptor());
    }

    /*这里就说到了我们的两种缓存：

    一、无论有无网络我们都去获取缓存的数据（我们会设置一个缓存时间，在某一段时间内（例如60S）
    去获取缓存数据。超过60S我们就去网络重新请求数据）

    二、有网络的时候我们就去直接获取网络上面的数据。当没有网络的时候我们就去缓存获取数据。*/

    private static OkHttpClient getOkHttpClient(Interceptor cacheControl) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //失败后是否重新连接
        httpClientBuilder.retryOnConnectionFailure(true);
        //设置拦截器
       /* httpClientBuilder.addInterceptor(new UserAgentInterceptor("Android Device"));
        httpClientBuilder.addInterceptor(new LoggingInterceptor());*/
        httpClientBuilder.addInterceptor(cacheControl);
        //httpClientBuilder.addNetworkInterceptor(cacheControl);
        //设置缓存
        File httpCacheDirectory = new File(BaseApplication.getInstance().getCacheDirFile(), "OkHttpCache");
        httpClientBuilder.cache(new Cache(httpCacheDirectory, 100 * 1024 * 1024));
        return httpClientBuilder.build();
    }


    /**
     * 没有网络的情况下就从缓存中取
     * 有网络的情况则从网络获取
     */
    private static class CacheControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtil.isConnected(SolidApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (NetworkUtil.isConnected(SolidApplication.getInstance())) {
                int maxAge = 60 * 60 * 2;//默认缓存两个小时
                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + maxAge;
                }
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", cacheControl)
                        .build();

            } else {
                int maxStale = 60 * 60 * 24 * 30;
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }

    /**
     * 强制从网络获取数据
     */
    private static class FromNetWorkControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();

            Response response = chain.proceed(request);

            return response;
        }
    }


    private static class UserAgentInterceptor implements Interceptor {
        private static final String USER_AGENT_HEADER_NAME = "User-Agent";
        private final String userAgentHeaderValue;

        UserAgentInterceptor(String userAgentHeaderValue) {
            this.userAgentHeaderValue = Preconditions.checkNotNull(userAgentHeaderValue, "userAgentHeaderValue = null");
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            final Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader(USER_AGENT_HEADER_NAME)
                    .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            //SLog.i(this, String.format(Locale.CHINA, "Sending request %s on %s%n%s",
            //      request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            //SLog.i(this, String.format(Locale.CHINA, "Received response for %s in %.1fms%n%s",
            //      response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
}
