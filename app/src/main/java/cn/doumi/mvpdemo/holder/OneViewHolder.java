package cn.doumi.mvpdemo.holder;

import android.view.View;
import android.widget.TextView;

import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.adapter.MultiTypeAdapter;
import cn.doumi.mvpdemo.base.BaseViewHolder;
import cn.doumi.mvpdemo.bean.One;


/**
 * Created by yq05481 on 2017/1/3.
 */

public class OneViewHolder extends BaseViewHolder<One> {
    public OneViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(One model, int position, MultiTypeAdapter adapter) {
        TextView textView = (TextView) getView(R.id.one_title);
        textView.setText(model.getText());
    }
}
