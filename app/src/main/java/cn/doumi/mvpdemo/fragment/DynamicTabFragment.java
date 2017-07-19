package cn.doumi.mvpdemo.fragment;


import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cn.doumi.mvpdemo.MainActivity;
import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.adapter.BaseRecyclerAdapter;
import cn.doumi.mvpdemo.adapter.TestAdapter;
import cn.doumi.mvpdemo.base.BaseGeneralRecyclerAdapter;
import cn.doumi.mvpdemo.base.BaseTitleFragment;
import cn.doumi.mvpdemo.bean.ZhuangbiImage;
import cn.doumi.mvpdemo.intface.OnTabReselectListener;
import cn.doumi.mvpdemo.network.HttpMethods;
import cn.doumi.mvpdemo.retrofitrxjava.HttpResultSubscriber;
import cn.doumi.mvpdemo.utils.NetUtils;
import cn.doumi.mvpdemo.utils.TDevice;
import cn.doumi.mvpdemo.widget.RecyclerRefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicTabFragment extends BaseTitleFragment implements
        OnTabReselectListener,
        BaseGeneralRecyclerAdapter.Callback,
        RecyclerRefreshLayout.SuperRefreshLayoutListener,
        BaseRecyclerAdapter.OnItemClickListener,
        BaseRecyclerAdapter.OnItemLongClickListener, BaseRecyclerAdapter.OnItemClickNetErrorListener {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    RecyclerRefreshLayout mRefreshLayout;


    private android.support.v4.app.Fragment mCurFragment;
    protected BaseRecyclerAdapter mAdapter;
    private boolean isShowIdentityView;
    protected boolean isRefreshing;
    private List<ZhuangbiImage> mMdata;
    private boolean isLoadMore;

    public DynamicTabFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_dynamic_tab;
    }

    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_news;
    }

    @Override
    public void onTabReselect() {
        Toast.makeText(mContext, "点击了综合", Toast.LENGTH_SHORT).show();

       /* if (mCurFragment != null && mCurFragment instanceof OnTabReselectListener) {
            //用于处理Fragment里面套用的情况
            ((OnTabReselectListener) mCurFragment).onTabReselect();

        }*/
    }

    @Override
    protected void initData() {
        MemorySizeCalculator calculator = new MemorySizeCalculator(getContext());
        int defaultMemoryCacheSize  = calculator.getMemoryCacheSize() / 1024 /1024;
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize() / 1024 /1024;
        Log.d("BBBBB","defaultMemoryCacheSize ="+  defaultMemoryCacheSize + "defaultBitmapPoolSize="+defaultBitmapPoolSize);

        mMdata = new ArrayList<>();

        mAdapter = getRecyclerAdapter();
        mAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, false);
        mRecyclerView.setAdapter(mAdapter);
       /* mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);*/
        mAdapter.setOnNetErrorCallBack(this);
        mRefreshLayout.setSuperRefreshLayoutListener(this);
        mRecyclerView.setLayoutManager(getLayoutManager());

        //滚动监听去影藏软键盘
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState && getActivity() != null
                        && getActivity().getCurrentFocus() != null) {
                    TDevice.hideSoftKeyboard(getActivity().getCurrentFocus());
                }
            }
        });

        mRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);


        mRefreshLayout.setVisibility(View.VISIBLE);

        //强制刷新
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
                onRefreshing();
            }
        });


    }

    protected BaseRecyclerAdapter<ZhuangbiImage> getRecyclerAdapter() {
        TestAdapter adapter = new TestAdapter(this);
        adapter.setShowIdentityView(isShowIdentityView);
        return adapter;
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public RequestManager getImgLoader() {
        return null;
    }

    @Override
    public Date getSystemTime() {
        return null;
    }

    @Override
    public void onRefreshing() {
        Log.d("CCCCC", "开始刷新");
        //刷新
        isRefreshing = true;
        //请求网络数据
        requestData();
    }

    private void requestData() {
        HttpMethods.getinstance().getZhuangBiData((MainActivity) getActivity(), "装逼", new HttpResultSubscriber<List<ZhuangbiImage>>() {
            @Override
            public void onSuccess(List<ZhuangbiImage> data) {

                Log.d("BBBBB", data.size() + "77777777777777777");
                mMdata.clear();
                if (isLoadMore) {
                    isLoadMore = false;
                    mMdata.addAll(data);
                    mAdapter.addAll(mMdata);

                } else {
                    mMdata.addAll(data);
                    mAdapter.clear();
                    mAdapter.addAll(mMdata);

                }
                onRequestFinish();
            }

            @Override
            public void _onError(Throwable e) {
                Log.d("BBBBB", "_onError77777777777777777");
                if (isLoadMore) {
                    mAdapter.setState(BaseRecyclerAdapter.STATE_LOAD_ERROR, true);
                }
                onRequestFinish();
            }
        });
    }

    @Override
    public void onLoadMore() {
        Log.d("CCCCC", "开始加载更多");

        isLoadMore = true;

        //加载更多
        mAdapter.setState(isRefreshing ? BaseRecyclerAdapter.STATE_HIDE : BaseRecyclerAdapter.STATE_LOADING, true);
        requestData();
    }


    protected void onRequestFinish() {
        onComplete();
        Log.d("CCCCC", "刷新完成");
    }

    protected void onComplete() {
        mRefreshLayout.onComplete();
        isRefreshing = false;
    }

    @Override
    public void onItemClick(int position, long itemId) {
        //每一项的点击效果

        Toast.makeText(mContext, position + "woaini", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int position, long itemId) {
        Toast.makeText(mContext, position + "woaini", Toast.LENGTH_SHORT).show();
    }


    /**
     * 加载更多的时候的网络错误回调
     */
    @Override
    public void onItemClickNetError() {
        //没有网络的时候不加载更多
        if (!NetUtils.isConnected(getContext()) && !NetUtils.isWifi(getContext())) {
            return;
        }
        mAdapter.setState(BaseRecyclerAdapter.STATE_LOADING, true);
        requestData();
    }
}
