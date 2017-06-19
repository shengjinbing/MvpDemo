package cn.doumi.mvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.doumi.mvpdemo.mvp.contract.MainContract;
import cn.doumi.mvpdemo.mvp.presenter.MainPresenter;

public class TestRxjavaActivity extends AppCompatActivity implements MainContract.View {


    @Inject
    MainPresenter mRx1Presenter;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.btn_one)
    Button mBtnOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initView();
        //initData();
    }


    private void initView() {
        ButterKnife.bind(this);


    }

    private void initData() {
       /* mRx1Presenter.LoadData(this, "装逼", new HttpResultSubscriber<String>() {

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void _onError(Throwable e) {

            }
        });*/
    }
}
