package cn.doumi.mvpdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.base.BaseGeneralRecyclerAdapter;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class TestAdapter extends BaseGeneralRecyclerAdapter<String> {
    private boolean isShowIdentityView;


    public TestAdapter(Callback callback) {
        super(callback, ONLY_FOOTER);
        isShowIdentityView = true;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test, parent, false));

    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder h, String item, int position) {
       ViewHolder holder = (ViewHolder) h;
        holder.mItemTestTextview.setText(item);
    }

    public void setShowIdentityView(boolean showIdentityView) {
        isShowIdentityView = showIdentityView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mItemTestTextview;
        public ViewHolder(View itemView) {
            super(itemView);
            mItemTestTextview= (TextView) itemView.findViewById(R.id.item_test_textview);
        }
    }



}
