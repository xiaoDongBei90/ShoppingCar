package com.lxw.cart;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lxw.cart.fragment.DowPartsFragment;
import com.lxw.cart.fragment.OutsidePartsFragment;
import com.lxw.cart.fragment.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpOrder;
    private TabLayout tabLayout;
    private String[] tabTitles = new String[]{"达欧配件", "外采件", "工项"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initView();
        OrderVpAdapter orderVpAdapter = new OrderVpAdapter(getSupportFragmentManager(), createFragmentList(), tabTitles);
        vpOrder.setAdapter(orderVpAdapter);
        tabLayout.setupWithViewPager(vpOrder);
        /*tabLayout.addTab(tabLayout.newTab().setText(tabTitles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(tabTitles[1]));
        tabLayout.addTab(tabLayout.newTab().setText(tabTitles[2]));*/
    }

    private void initView() {
        vpOrder = (ViewPager) findViewById(R.id.vp_order);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
    }

    private List<Fragment> createFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        DowPartsFragment first = new DowPartsFragment();
        OutsidePartsFragment second = new OutsidePartsFragment();
        ProjectFragment third = new ProjectFragment();
        fragmentList.add(first);
        fragmentList.add(second);
        fragmentList.add(third);
        return fragmentList;
    }
}
