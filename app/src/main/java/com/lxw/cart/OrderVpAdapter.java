package com.lxw.cart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/11/9.
 * desc:
 */

public class OrderVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private String[] titleList;

    public OrderVpAdapter(FragmentManager fm, List<Fragment> fragments, String[] titleList) {
        super(fm);
        this.fragmentList = fragments;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }
}
