package cn.doumi.mvpdemo.factory;

import android.view.View;

import cn.doumi.mvpdemo.base.BaseViewHolder;
import cn.doumi.mvpdemo.bean.Normal;
import cn.doumi.mvpdemo.bean.One;
import cn.doumi.mvpdemo.bean.Three;
import cn.doumi.mvpdemo.bean.Two;


/**
 * Created by yq05481 on 2016/12/30.
 */

public interface TypeFactory {
    int type(One one);

    int type(Two two);

    int type(Three three);

    int type(Normal normal);

    BaseViewHolder createViewHolder(int type, View itemView);
}
