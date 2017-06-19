package cn.doumi.mvpdemo.factory;

import android.view.View;

import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.base.BaseViewHolder;
import cn.doumi.mvpdemo.bean.Normal;
import cn.doumi.mvpdemo.bean.One;
import cn.doumi.mvpdemo.bean.Three;
import cn.doumi.mvpdemo.bean.Two;
import cn.doumi.mvpdemo.holder.NormalViewHolder;
import cn.doumi.mvpdemo.holder.OneViewHolder;
import cn.doumi.mvpdemo.holder.ThreeViewHolder;
import cn.doumi.mvpdemo.holder.TwoViewHolder;


/**
 * Created by yq05481 on 2016/12/30.
 */

public class TypeFactoryForList implements TypeFactory {
    private final int TYPE_RESOURCE_ONE = R.layout.layout_item_one;
    private final int TYPE_RESOURCE_TWO = R.layout.layout_item_two;
    private final int TYPE_RESOURCE_THREE = R.layout.layout_item_three;
    private final int TYPE_RESOURCE_NORMAL = R.layout.layout_item_normal;
    @Override
    public int type(One one) {
        return TYPE_RESOURCE_ONE;
    }

    @Override
    public int type(Two one) {
        return TYPE_RESOURCE_TWO;
    }

    @Override
    public int type(Three one) {
        return TYPE_RESOURCE_THREE;
    }

    @Override
    public int type(Normal normal) {
        return TYPE_RESOURCE_NORMAL;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {

        if(TYPE_RESOURCE_ONE == type){
            return new OneViewHolder(itemView);
        }else if (TYPE_RESOURCE_TWO == type){
            return new TwoViewHolder(itemView);
        }else if (TYPE_RESOURCE_THREE == type){
            return new ThreeViewHolder(itemView);
        }else if (TYPE_RESOURCE_NORMAL == type){
            return new NormalViewHolder(itemView);
        }

        return null;
    }
}
