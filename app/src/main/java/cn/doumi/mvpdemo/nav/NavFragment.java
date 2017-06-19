package cn.doumi.mvpdemo.nav;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.fragment.DynamicTabFragment;
import cn.doumi.mvpdemo.fragment.ExploreFragment;
import cn.doumi.mvpdemo.fragment.TweetViewPagerFragment;
import cn.doumi.mvpdemo.fragment.UserInfoFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragment extends Fragment implements View.OnClickListener {


    NavigationButton mNavItemNews;
    NavigationButton mNavItemTweet;
    ImageView mNavItemTweetPub;
    NavigationButton mNavItemExplore;
    NavigationButton mNavItemMe;
    private Context mContext;
    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;

    public NavFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav, container, false);

        mNavItemNews = (NavigationButton) view.findViewById(R.id.nav_item_news);
        mNavItemTweet = (NavigationButton) view.findViewById(R.id.nav_item_tweet);
        mNavItemExplore = (NavigationButton) view.findViewById(R.id.nav_item_explore);
        mNavItemMe = (NavigationButton) view.findViewById(R.id.nav_item_me);
        mNavItemTweetPub = (ImageView) view.findViewById(R.id.nav_item_tweet_pub);
        initWidget();
        initListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @SuppressWarnings("deprecation")
    protected void initWidget() {

        mNavItemNews.init(R.drawable.tab_icon_new,
                R.string.main_tab_name_news,
                DynamicTabFragment.class);

        mNavItemTweet.init(R.drawable.tab_icon_tweet,
                R.string.main_tab_name_tweet,
                TweetViewPagerFragment.class);

        mNavItemExplore.init(R.drawable.tab_icon_explore,
                R.string.main_tab_name_explore,
                ExploreFragment.class);

        mNavItemMe.init(R.drawable.tab_icon_me,
                R.string.main_tab_name_my,
                UserInfoFragment.class);

    }

    private void initListener() {
        mNavItemNews.setOnClickListener(this);
        mNavItemTweet.setOnClickListener(this);
        mNavItemExplore.setOnClickListener(this);
        mNavItemMe.setOnClickListener(this);
        mNavItemTweetPub.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_item_news:
                doSelect(mNavItemNews);
                break;
            case R.id.nav_item_tweet:
                doSelect(mNavItemTweet);
                break;
            case R.id.nav_item_explore:
                doSelect(mNavItemExplore);
                break;
            case R.id.nav_item_me:
                doSelect(mNavItemMe);
                break;
            case R.id.nav_item_tweet_pub:
                //点击中间
                break;
        }
    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(mNavItemNews);
    }

    public void select(int index) {
        if (mNavItemMe != null)
            doSelect(mNavItemMe);
    }

    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void doSelect(NavigationButton newNavButton) {

        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

   /* 使用add()加入fragment时将触发onAttach(),使用attach()不会触发onAttach()

    使用replace()替换后会将之前的fragment的view从viewtree中删除

    触发顺序:

    detach()->onPause()->onStop()->onDestroyView()

    attach()->onCreateView()->onActivityCreated()->onStart()->onResume()

    使用hide()方法只是隐藏了fragment的view并没有将view从viewtree中删除,随后可用show()方法将view设置为显示

    而使用detach()会将view从viewtree中删除,和remove()不同,
    此时fragment的状态依然保持着,在使用attach()时会再次调用onCreateView()来重绘视图,
    注意使用detach()后fragment.isAdded()方法将返回false,
    在使用attach()还原fragment后isAdded()会依然返回false(需要再次确认)
x
    执行detach()和replace()后要还原视图的话, 可以在相应的fragment中保持相应的view,
    并在onCreateView()方法中通过view的parent的removeView()方法将view和parent的关联删除后返回*/
    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        Log.d("BBBBB","wiaini");
        Log.d("BBBBB",newNavButton.getClx().getName());
        Log.d("BBBBB",newNavButton.getTag());
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                //利用反射实现,但是性能较低，这里主要是做个判断基本不会发生
                Fragment fragment = Fragment.instantiate(mContext,
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                //使用add()加入fragment时将触发onAttach(),使用attach()不会触发onAttach()
                //调用该方法时，Fragment会被连接到它的父Activity上；获取对父Activity的引用。
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }


    /**
     * 导航按钮的回调
     * @param navigationButton
     */
    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }


    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }
}
