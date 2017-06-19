package cn.doumi.mvpdemo.network.api;

import java.util.List;

import cn.doumi.mvpdemo.bean.ZhuangbiImage;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public interface NetApiService {

    @GET("search")
    Observable<List<ZhuangbiImage>> search(@Query("q") String query);
}
