package cn.doumi.mvpdemo.fragment;


import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.adapter.MultiTypeAdapter;
import cn.doumi.mvpdemo.base.BaseTitleFragment;
import cn.doumi.mvpdemo.bean.Normal;
import cn.doumi.mvpdemo.bean.One;
import cn.doumi.mvpdemo.bean.Three;
import cn.doumi.mvpdemo.bean.Two;
import cn.doumi.mvpdemo.intface.OnTabReselectListener;
import cn.doumi.mvpdemo.intface.Visitable;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetViewPagerFragment extends BaseTitleFragment implements OnTabReselectListener {


    @BindView(R.id.tweet_recyclerview)
    RecyclerView mTweetRecyclerview;

    public TweetViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_tweet_view_pager;
    }

    @Override
    protected int getTitleRes() {
        return R.string.tweet_publish_title;
    }

    @Override
    public void onTabReselect() {
        Toast.makeText(mContext, "点击了发布动态", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        mTweetRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Visitable> list = getData();
        list.add(0,new One("Type One 0"));
        list.add(1,new Two("Type Two 0"));
        list.add(2,new Three("Type Three 0"));
        list.add(new One("Type One 1"));

        mTweetRecyclerview.setAdapter(new MultiTypeAdapter(list));
    }
    private List<Visitable> getData(){
        List<Visitable> models = new ArrayList<>();

        for (int index = 0; index < 50; index++ ){
            models.add(new Normal("Type normal "+ index));
        }

        return models;
    }

}
