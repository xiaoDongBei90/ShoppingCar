package com.lxw.cart;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxw.cart.bean.PartContent;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/11/10.
 * desc:
 */

public class PartContentAdapter extends BaseQuickAdapter<PartContent, BaseViewHolder> {

    public PartContentAdapter(@LayoutRes int layoutResId, @Nullable List<PartContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PartContent item) {
        helper.setText(R.id.tv_title, item.getName());
        helper.addOnClickListener(R.id.iv_add).addOnClickListener(R.id.iv_remove);
    }
}
