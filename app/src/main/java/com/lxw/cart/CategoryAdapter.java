package com.lxw.cart;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxw.cart.bean.CategoryBean;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/11/10.
 * desc:
 */

public class CategoryAdapter extends BaseQuickAdapter<CategoryBean, BaseViewHolder> {
    private List<CategoryBean> data;
    private OnItemSelectListener onItemSelectListener;
    private int selectPos;

    public CategoryAdapter(@LayoutRes int layoutResId, @Nullable List<CategoryBean> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean item) {
        helper.setText(R.id.tv_part_menu, item.getName());
        helper.getView(R.id.tv_part_menu).setSelected(helper.getLayoutPosition() == selectPos);
    }

    public void setSelection(int pos) {
        this.selectPos = pos;
        notifyDataSetChanged();
    }


    public interface OnItemSelectListener {
        void onSelect(int position, List<CategoryBean> data);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }
}
