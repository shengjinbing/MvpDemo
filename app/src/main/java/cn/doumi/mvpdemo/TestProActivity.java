package cn.doumi.mvpdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.doumi.mvpdemo.rxlife.TakeUntilUtils;
import cn.doumi.mvpdemo.utils.DialogHelper;
import rx.Observable;
import rx.functions.Action1;

public class TestProActivity extends AppCompatActivity {


    private final static int REQUEST_CODE_CAMERA = 101;
    @BindView(R.id.activity_main_Edit_username)
    EditText mActivityMainEditUsername;
    @BindView(R.id.activity_main_Edit_pass)
    EditText mActivityMainEditPass;
    @BindView(R.id.activity_main_login)
    Button mActivityMainLogin;
    @BindView(R.id.activity_main_clear)
    Button mActivityMainClear;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        initLisenter();
    }

    private void initView() {
        Observable.interval(2, TimeUnit.SECONDS).compose(TakeUntilUtils.bindUntilDelay(5)).subscribe(new Action1<Long>() {
            @Override
            public void call(Long num) {
                Log.d("TestProActivity", "num:" + num);
            }
        });

        //条用的示例
       /* ApiFactory.getWXApi().getWXHot(AppConstant.KEY_WX, getPageSize(), mCurrentPage + 1).compose(this.bindUntilEvent(FragmentEvent.STOP))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);*/
    }


    private void initData() {


    }

    private void initLisenter() {
    }

    protected ProgressDialog showFocusWaitDialog() {

        String message = "正在提交…";
        if (mDialog == null) {
            mDialog = DialogHelper.getProgressDialog(this, message, false);//DialogHelp.getWaitDialog(this, message);
        }
        mDialog.show();

        return mDialog;
    }

    public void clivk(View view) {
        showFocusWaitDialog();
    }

    public void dajian(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void rxjava(View view) {
        Intent intent = new Intent(this,TestRxjavaActivity.class);
        startActivity(intent);
    }
}
