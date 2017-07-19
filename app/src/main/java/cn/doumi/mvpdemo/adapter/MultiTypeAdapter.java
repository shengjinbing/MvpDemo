package cn.doumi.mvpdemo.adapter;

import android.view.View;

import java.util.List;

import cn.doumi.mvpdemo.base.BaseRecyclerViewAdapter;
import cn.doumi.mvpdemo.base.BaseViewHolder;
import cn.doumi.mvpdemo.factory.TypeFactory;
import cn.doumi.mvpdemo.factory.TypeFactoryForList;
import cn.doumi.mvpdemo.intface.Visitable;

/**
 * Created by yq05481 on 2016/12/30.
 */

public class MultiTypeAdapter extends BaseRecyclerViewAdapter<Visitable> {
    private TypeFactory typeFactory;

    public MultiTypeAdapter(List<Visitable> models) {
        mDataList = models;
        this.typeFactory = new TypeFactoryForList();

    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int viewType) {
        return typeFactory.createViewHolder(viewType,itemView);
    }

    @Override
    public void BindViewHolder(BaseViewHolder holder, int position) {
        holder.setUpView(mDataList.get(position),position,this);
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).type(typeFactory);
    }


    @Override
    public int getItemCount() {
        if(null == mDataList){
            return  0;
        }
        return mDataList.size();
    }


}
