package cn.doumi.mvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lx.ItemDecoration.DividerDecoration;
import com.github.lx.interfaces.OnLoadMoreListener;
import com.github.lx.interfaces.OnRefreshListener;
import com.github.lx.recyclerview.LRecyclerView;
import com.github.lx.recyclerview.LRecyclerViewAdapter;
import com.github.lx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.doumi.mvpdemo.adapter.MultiTypeAdapter;
import cn.doumi.mvpdemo.bean.Normal;
import cn.doumi.mvpdemo.bean.One;
import cn.doumi.mvpdemo.bean.Three;
import cn.doumi.mvpdemo.bean.Two;
import cn.doumi.mvpdemo.intface.Visitable;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class LrecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.list)
    LRecyclerView mRecyclerView;

    private static final String TAG = "lzx";

    /**服务器端一共多少条数据*/
    private static final int TOTAL_COUNTER = 34;//如果服务器没有返回总数据或者总页数，这里设置为最大值比如10000，什么时候没有数据了根据接口返回判断

    /**每一页展示多少条数据*/
    private static final int REQUEST_COUNT = 10;

    /**已经获取到多少条数据了*/
    private static int mCurrentCounter = 0;


    private MultiTypeAdapter mDataAdapter = null;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<Visitable> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        initData();
        initView();
        initListener();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDataAdapter = new MultiTypeAdapter(mList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.split)
                .build();

        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //mRecyclerView.setRefreshProgressStyle(AVLoadingIndicatorView.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
        //mRecyclerView.setRefreshProgressStyle(AVLoadingIndicatorView.ACCESSIBILITY_LIVE_REGION_NONE);
        //mRecyclerView.setRefreshProgressStyle(AVLoadingIndicatorView.ACCESSIBILITY_LIVE_REGION_POLITE);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.SysProgress);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //add a HeaderView
        final View header = LayoutInflater.from(this).inflate(R.layout.sample_header,(ViewGroup)findViewById(android.R.id.content), false);
        mLRecyclerViewAdapter.addHeaderView(header);

        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.dark ,android.R.color.white);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.dark ,android.R.color.white);
        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

        mRecyclerView.refresh();
    }

    private void initData() {
        mList = getData();
        mList.add(0,new One("Type One 0"));
        mList.add(1,new Two("Type Two 0"));
        mList.add(2,new Three("Type Three 0"));
        mList.add(new One("Type One 1"));
    }

    private List<Visitable> getData(){
        List<Visitable> models = new ArrayList<>();
        for (int index = 0; index < 50; index++ ){
            models.add(new Normal("Type normal "+ index));
        }
        return models;
    }

    private void initListener() {
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });

        //禁用自动加载更多功能
        mRecyclerView.setLoadMoreEnabled(false);

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
            }
        });

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }


            @Override
            public void onScrolled(int distanceX, int distanceY) {
            }

            @Override
            public void onScrollStateChanged(int state) {

            }

        });

    }
}
