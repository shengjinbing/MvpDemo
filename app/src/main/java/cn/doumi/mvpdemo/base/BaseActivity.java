package cn.doumi.mvpdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import cn.doumi.mvpdemo.MainActivity;
import cn.doumi.mvpdemo.rxlife.RxAppCompatActivity;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    // 共同属性
    // 共同的方法
    private List<RxAppCompatActivity> activities = new LinkedList<RxAppCompatActivity>();
    private long mPreTime;
    private Activity mCurActivity;
    private Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        activities.add(this);

        initActionBar();
        initView();
        initData();
        initListener();

    }




    @Override
    protected void onDestroy() {
        activities.remove(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        mCurActivity = this;// 最上层的一个activity
        super.onResume();
    }


    //统一获取布局参数
    public abstract int getLayout();

    public void initActionBar() {
        // TODO

    }

    /**
     * 视图
     */
    protected  void initView(){
        //TOD
    }

    /**
     * 数据
     */
    protected abstract void initData();

    /**
     * 监听
     */
    protected abstract void initListener();



    /**
     * 完全退出，结束所以Activity
     */
    public void exit() {
        for (RxAppCompatActivity activity : activities) {
            activity.finish();
        }
    }

    /**
     * 统一退出控制
     */
    @Override
    public void onBackPressed() {
        if (this instanceof MainActivity) {
            if (System.currentTimeMillis() - mPreTime > 2000) {// 两次点击间隔大于2s
                Toast.makeText(getApplicationContext(), "再按一次,退出应用", Toast.LENGTH_SHORT).show();
                mPreTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressed();// finish();
    }


    /**
     * 添加Fragment
     *
     * @param frameLayoutId
     * @param fragment
     */
    protected void addFragment(int frameLayoutId, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                if (mFragment != null) {
                    transaction.hide(mFragment).show(fragment);
                } else {
                    transaction.show(fragment);
                }
            } else {
                if (mFragment != null) {
                    transaction.hide(mFragment).add(frameLayoutId, fragment);
                } else {
                    transaction.add(frameLayoutId, fragment);
                }
            }
            mFragment = fragment;
            transaction.commit();
        }
    }

    /**
     * 替换Fragment
     *
     * @param frameLayoutId
     * @param fragment
     */
    protected void replaceFragment(int frameLayoutId, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(frameLayoutId, fragment);
            transaction.commit();
        }
    }
}
