package cn.doumi.mvpdemo.retrofitrxjava;

import android.app.Application;
import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class SolidApplication extends Application{
    private static SolidApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //ToastUtils.init(this);
    }

    public static SolidApplication getInstance() {
        return mInstance;
    }

    @Override
    public File getCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return super.getCacheDir();
    }
}
