package com.lxw.cart.bean;

/**
 * @ author  LiXiaoWei
 * @ date  2017/11/10.
 * desc:
 */

public class PartContent {
    private String name;
    private String price;
    private int contentId;

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }
}
