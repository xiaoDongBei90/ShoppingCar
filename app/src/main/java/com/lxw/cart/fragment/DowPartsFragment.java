package com.lxw.cart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxw.cart.PartContentAdapter;
import com.lxw.cart.CategoryAdapter;
import com.lxw.cart.R;
import com.lxw.cart.bean.CategoryBean;
import com.lxw.cart.bean.PartContent;

import java.util.ArrayList;
import java.util.List;

public class DowPartsFragment extends Fragment {

    private RecyclerView rvMenu, rvContent;
    private String[] menuList = new String[]{"润滑油", "离合器", "雨刷器", "活塞", "连杆", "气门", "散热器", "刹车片", "配电盒", "节温器", "分动器", "万向节", "取力器"};
    private List<CategoryBean> categoryList = new ArrayList<>();
    private List<PartContent> partList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dow_parts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initData();
    }

    private void initView(View view) {
        rvMenu = (RecyclerView) view.findViewById(R.id.rv_menu);
        rvContent = (RecyclerView) view.findViewById(R.id.rv_content);

    }

    private void initData() {
        getCategory();
        getPartContent();
        final CategoryAdapter categoryAdapter = new CategoryAdapter(R.layout.item_part_menu, categoryList);
        rvMenu.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvMenu.setAdapter(categoryAdapter);

        final PartContentAdapter partContentAdapter = new PartContentAdapter(R.layout.item_part_content, partList);
        rvContent.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvContent.setAdapter(partContentAdapter);
        categoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int sum = 0;
                for (int i = 0; i < position; i++) {
                    sum += categoryList.get(i).getCount();
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvContent.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(sum, 0);
            }
        });
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                PartContent partContent = partList.get(firstVisibleItemPosition);
                int contentId = partContent.getContentId();
                int pos = 0;
                for (int i = 0; i < categoryList.size(); i++) {
                    int menuId = categoryList.get(i).getMenuId();
                    if (contentId == menuId) {
                        pos = i;
                    }
                }
                categoryAdapter.setSelection(pos);
                rvMenu.scrollToPosition(pos);
            }
        });
    }

    public List<PartContent> getPartDetail(int id, int count) {
        List<PartContent> partDetailList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            PartContent partContent = new PartContent();
            partContent.setName("id" + id + "→离合器液" + i);
            partContent.setPrice("￥40" + i);
            partContent.setContentId(id);
            partDetailList.add(partContent);
        }
        return partDetailList;
    }

    public void getCategory() {
        for (int i = 0; i < 5; i++) {
            CategoryBean categoryBean = new CategoryBean();
            categoryBean.setName("离合器" + i);
            categoryBean.setMenuId(i);
            int c = (int) (Math.random() * 10 + 1);
            categoryBean.setCount(c);
            categoryBean.setPartDetailList(getPartDetail(i, c));
            categoryList.add(categoryBean);
        }
    }

    public void getPartContent() {
        for (int i = 0; i < categoryList.size(); i++) {
            partList.addAll(categoryList.get(i).getPartDetailList());
        }
    }


}
