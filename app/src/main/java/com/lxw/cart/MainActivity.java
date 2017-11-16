package com.lxw.cart;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.flipboard.bottomsheet.BottomSheetLayout;
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
    private BottomSheetLayout bottomSheetLayout;
    private ListView lv;
    private View bottomSheet;
    private String[] lvData = new String[]{"测试1", "测试2", "测试3", "测试13", "测试14"};
    private ImageView ivShoppingCart;
    private List<Fragment> fragmentList;
    private RelativeLayout mainLayout;
    //private final String[] lvData = new String[]{"测试1", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7", "测试8", "测试9", "测试10", "测试11", "测试12", "测试13", "测试14"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initView();

        initFragment();
        OrderVpAdapter orderVpAdapter = new OrderVpAdapter(getSupportFragmentManager(), fragmentList, tabTitles);
        vpOrder.setAdapter(orderVpAdapter);
        tabLayout.setupWithViewPager(vpOrder);
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        DowPartsFragment first = new DowPartsFragment();
        OutsidePartsFragment second = new OutsidePartsFragment();
        ProjectFragment third = new ProjectFragment();
        fragmentList.add(first);
        fragmentList.add(second);
        fragmentList.add(third);
        first.setOnConvertInter(new DowPartsFragment.ConvertInter() {
            @Override
            public void add(final View view, int position) {
                //贝塞尔起始数据点
                int[] startPosition = new int[2];
                //贝塞尔结束数据点
                int[] endPosition = new int[2];
                //控制点
                int[] recyclerPosition = new int[2];

                view.getLocationInWindow(startPosition);
                ivShoppingCart.getLocationInWindow(endPosition);
                view.getLocationInWindow(recyclerPosition);

                PointF startF = new PointF();
                PointF endF = new PointF();
                PointF controllF = new PointF();

                startF.x = startPosition[0];
                startF.y = startPosition[1] - recyclerPosition[1];
                endF.x = endPosition[0];
                endF.y = endPosition[1] - recyclerPosition[1];
                controllF.x = endF.x;
                controllF.y = startF.y;

                final ImageView imageView = new ImageView(MainActivity.this);
                mainLayout.addView(imageView);
                imageView.setImageResource(R.mipmap.ic_add_circle_blue_700_36dp);
                imageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
                imageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
                imageView.setVisibility(View.VISIBLE);
                imageView.setX(startF.x);
                imageView.setY(startF.y);

                ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierTypeEvaluator(controllF), startF, endF);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF pointF = (PointF) animation.getAnimatedValue();
                        imageView.setX(pointF.x);
                        imageView.setY(pointF.y);
                        Log.i("wangjtiao", "viewF:" + view.getX() + "," + view.getY());
                    }
                });


                ObjectAnimator objectAnimatorX = new ObjectAnimator().ofFloat(ivShoppingCart, "scaleX", 0.6f, 1.0f);
                ObjectAnimator objectAnimatorY = new ObjectAnimator().ofFloat(ivShoppingCart, "scaleY", 0.6f, 1.0f);
                objectAnimatorX.setInterpolator(new AccelerateInterpolator());
                objectAnimatorY.setInterpolator(new AccelerateInterpolator());
                AnimatorSet set = new AnimatorSet();
                set.play(objectAnimatorX).with(objectAnimatorY).after(valueAnimator);
                set.setDuration(800);
                set.start();
            }

            @Override
            public void remove(View view, int position) {

            }
        });
    }

    private void initView() {
        findView();
        final View carContainer = findViewById(R.id.bottom_container);
        carContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
    }

    //创建购物车view
    private void showBottomSheet() {
        bottomSheet = createBottomSheetView();
        if (bottomSheetLayout.isSheetShowing()) {
            bottomSheetLayout.dismissSheet();
        } else {
            bottomSheetLayout.showWithSheetView(bottomSheet);
           /* if (selectedList.size() != 0) {
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }*/
        }
    }

    //查看购物车布局
    private View createBottomSheetView() {
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, (ViewGroup) getWindow().getDecorView(), false);
        ListView lv_product = (ListView) view.findViewById(R.id.lv_bottom_sheet);
        /*TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });*/
        lv_product.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lvData));
        return view;
    }

    private void findView() {
        vpOrder = (ViewPager) findViewById(R.id.vp_order);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        lv = (ListView) findViewById(R.id.lv_bottom_sheet);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);
        ivShoppingCart = (ImageView) findViewById(R.id.shopping_cart);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
    }
}
