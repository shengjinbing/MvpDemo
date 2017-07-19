package cn.doumi.mvpdemo.ui;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }

    //表示resource的数据模型
    public interface MyDataModel {
        public String buildUrl(int width, int height);
    }

    public class MyUrlLoader extends BaseGlideUrlLoader<MyDataModel> {
        public MyUrlLoader(Context context) {
            super(context);
        }

        @Override
        protected String getUrl(MyDataModel model, int width, int height) {
            // 根据不同尺寸构造不同的url
            return model.buildUrl(width, height);
        }
    }
}
