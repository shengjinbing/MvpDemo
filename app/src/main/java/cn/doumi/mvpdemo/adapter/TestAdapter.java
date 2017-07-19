package cn.doumi.mvpdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.base.BaseGeneralRecyclerAdapter;
import cn.doumi.mvpdemo.bean.ZhuangbiImage;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class TestAdapter extends BaseGeneralRecyclerAdapter<ZhuangbiImage> {
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
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder h, ZhuangbiImage item, int position) {
       ViewHolder holder = (ViewHolder) h;
        holder.mItemTestTextview.setText(item.getDescription());
        Glide.with(mContext)
                .load(item.getImageUrl())
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImageView);
    }

    public void setShowIdentityView(boolean showIdentityView) {
        isShowIdentityView = showIdentityView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mItemTestTextview;
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mItemTestTextview= (TextView) itemView.findViewById(R.id.item_test_textview);
            mImageView= (ImageView) itemView.findViewById(R.id.item_image);
        }
    }



}
