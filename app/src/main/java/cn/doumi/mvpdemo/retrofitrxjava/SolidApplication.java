package cn.doumi.mvpdemo.retrofitrxjava;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

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


    public File getCacheDir() {
        File mFile = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d("BBBBB","sdcard挂载");
            mFile = getExternalCacheDir();
        }
      return mFile;
    }
}
