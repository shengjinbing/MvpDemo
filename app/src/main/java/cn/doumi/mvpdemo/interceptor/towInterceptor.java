package cn.doumi.mvpdemo.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.doumi.mvpdemo.base.BaseApplication;
import cn.doumi.mvpdemo.utils.NetUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/23 0023.
 */


/**
 * 1.no-cache :无缓存指令，即每次请求直接从服务器获取。”Cache-Control”： “no-cache
 * 2.max-age :代表缓存的有效时间，如果缓存只是用来和服务器做验证，可是设置更有效的”Cache-Control”：”max-age=0”。
 * 3.only-if-cached :先使用用缓存的数据，如果客户端有缓存，会立即显示客户端的缓存，
 * 这样你的应用程序可以在等待最新的数据下载的时候显示一些东西， 重定向request到本地缓存资源，
 * 添加”Cache-Control”：”only-if-cached”。
 * 4.max-stale :即使缓存已过期，也可先展示出来。有时候过期的response比没有response更好，
 * 设置最长过期时间来允许过期的response响应： int maxStale = 60 * 60 * 24 * 28; //tolerate 4-weeks
 * stale “Cache-Control”：”max-stale=” + maxStale。
 */

public class towInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
        cacheBuilder.maxStale(365,TimeUnit.DAYS);//这个是控制缓存的过时时间
        CacheControl cacheControl = cacheBuilder.build();

        Request request = chain.request();//获取请求
        //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，
        // 要是没有网络的话我么就去缓存里面取数据
        if (!NetUtils.isConnected(BaseApplication.getInstance().getApplicationContext())) {
            Log.d("BBBBB","无网络1");
            request = request.newBuilder()
                    //这个的话内容有点多啊，大家记住这么写就是只从缓存取，想要了解这个东西我等下在
                    // 给大家写连接吧。大家可以去看下，获取大家去找拦截器资料的时候就可以看到这个方面的东西反正也就是缓存策略。
                    .cacheControl(cacheControl)
                    .build();
            Log.d("CacheInterceptor", "no network");
        }
        Response originalResponse = chain.proceed(request);
        if (NetUtils.isConnected(BaseApplication.getInstance().getApplicationContext())) {
            Log.d("BBBBB","有网络");
            //这里大家看点开源码看看.header .removeHeader做了什么操作很简答，就是的加字段和减字段的。
            //String cacheControl = request.cacheControl().toString();
            int maxage = 60 * 60 * 24;
            return originalResponse.newBuilder()
                    //这里设置的为0就是说不进行缓存，我们也可以设置缓存时间
                    .header("Cache-Control", "public, max-age=" + maxage)
                    .removeHeader("Pragma")
                    .build();
        } else {
            Log.d("BBBBB","无网络2");
            int maxTime = 4 * 24 * 60 * 60;
            return originalResponse.newBuilder()
                    //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxTime)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
