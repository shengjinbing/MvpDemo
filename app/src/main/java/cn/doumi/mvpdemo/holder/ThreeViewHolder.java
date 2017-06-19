package cn.doumi.mvpdemo.holder;

import android.view.View;
import android.widget.TextView;

import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.adapter.MultiTypeAdapter;
import cn.doumi.mvpdemo.base.BaseViewHolder;
import cn.doumi.mvpdemo.bean.Three;

/**
 * Created by yq05481 on 2017/1/3.
 */

public class ThreeViewHolder extends BaseViewHolder<Three> {
    public ThreeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(Three model, int position, MultiTypeAdapter adapter) {
        TextView textView = (TextView) getView(R.id.three_title);
        textView.setText(model.getText());
    }
}
