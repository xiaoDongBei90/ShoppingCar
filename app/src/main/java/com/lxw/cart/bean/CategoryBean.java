package com.lxw.cart.bean;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/11/10.
 * desc:
 */

public class CategoryBean {
    private String name;
    private int menuId;
    private int count;
    private List<PartContent> partDetailList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getMenuId() {
        return menuId;
    }

    public List<PartContent> getPartDetailList() {
        return partDetailList;
    }

    public void setPartDetailList(List<PartContent> partDetailList) {
        this.partDetailList = partDetailList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
