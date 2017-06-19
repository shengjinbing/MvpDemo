package cn.doumi.mvpdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.doumi.mvpdemo.base.BaseActivity;
import cn.doumi.mvpdemo.config.AppContext;
import cn.doumi.mvpdemo.dagger2.component.DaggerMainComponent;
import cn.doumi.mvpdemo.dagger2.module.MainModule;
import cn.doumi.mvpdemo.intface.OnTabReselectListener;
import cn.doumi.mvpdemo.mvp.contract.MainContract;
import cn.doumi.mvpdemo.mvp.presenter.MainPresenter;
import cn.doumi.mvpdemo.nav.NavFragment;
import cn.doumi.mvpdemo.nav.NavigationButton;
import cn.doumi.mvpdemo.widget.RippleIntroView;

public class MainActivity extends BaseActivity implements
        NavFragment.OnNavigationReselectListener,
        MainContract.View{

    @BindView(R.id.main_container)
    FrameLayout mMainContainer;
    @BindView(R.id.activity_main_ui)
    LinearLayout mActivityMainUi;
    @BindView(R.id.iv_plus)
    ImageView mIvPlus;
    @BindView(R.id.tv_title)
    ImageView mTvTitle;
    @BindView(R.id.layout_ripple)
    RippleIntroView mLayoutRipple;


    private NavFragment mNavBar;
    @Inject
    MainPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        //初始化Dagger进行MainPresenter获取
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);


        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this, manager, R.id.main_container, this);

        //显示RippleIntroView
          if (true) {
            View view = findViewById(R.id.layout_ripple);
            view.setVisibility(View.VISIBLE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ViewGroup) v.getParent()).removeView(v);
                    AppContext.set("isFirstComing", false);
                }
            });
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    /**
     * 传递导航按钮的监听到每个Fragment
     * @param navigationButton
     */
    @Override
    public void onReselect(NavigationButton navigationButton) {
        Fragment fragment = navigationButton.getFragment();
        if (fragment != null && fragment instanceof OnTabReselectListener) {
            OnTabReselectListener listener = (OnTabReselectListener) fragment;
            listener.onTabReselect();
        }
    }
}
