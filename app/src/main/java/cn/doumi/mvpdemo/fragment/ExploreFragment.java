package cn.doumi.mvpdemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.base.BaseTitleFragment;
import cn.doumi.mvpdemo.intface.OnTabReselectListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends BaseTitleFragment implements OnTabReselectListener {


    public ExploreFragment() {
        // Required empty public constructor
    }




    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_explore;
    }

    @Override
    public void onTabReselect() {
        Toast.makeText(mContext, "点击了发现", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("BBBBB","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BBBBB","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("BBBBB","onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("BBBBB","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("BBBBB","onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("BBBBB","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("BBBBB","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("BBBBB","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BBBBB","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("BBBBB","onDetach");
    }

}
