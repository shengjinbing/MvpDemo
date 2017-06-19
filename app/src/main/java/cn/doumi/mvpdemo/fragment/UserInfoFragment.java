package cn.doumi.mvpdemo.fragment;


import android.support.v4.app.Fragment;
import android.widget.Toast;

import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.base.BaseTitleFragment;
import cn.doumi.mvpdemo.intface.OnTabReselectListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends BaseTitleFragment implements OnTabReselectListener {


    public UserInfoFragment() {
        // Required empty public constructor
    }




    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_user_info;
    }

    @Override
    protected int getTitleRes() {
        return R.string.str_team_user_info;
    }

    @Override
    public void onTabReselect() {
        Toast.makeText(mContext, "点击了用户主页", Toast.LENGTH_SHORT).show();
    }

}
