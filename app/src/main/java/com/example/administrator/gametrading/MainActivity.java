package com.example.administrator.gametrading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.gametrading.Adapter.ViewPageAdapter;
import com.example.administrator.gametrading.util.myViewPager;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{

    private RadioGroup main_tab;
    private RadioButton main_illustration;
    private RadioButton main_forum;
    private RadioButton main_shop;
    private RadioButton main_shop_car;
    private RadioButton main_my;
    private myViewPager vpager;
    private ViewPageAdapter viewPageAdapter;
    public static MainActivity instance3;
    public static final int PAGE_ONE =   0;
    public static final int PAGE_TWO =   1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR =  3;
    public static final int PAGE_FIVE =  4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance3=this;
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        bindViews();
        main_illustration.setChecked(true);
    }

    private void bindViews() {
        main_tab = (RadioGroup)findViewById(R.id.navigation_bar);
        main_illustration = (RadioButton)findViewById(R.id.main_illustration);
        main_forum = (RadioButton)findViewById(R.id.main_forum);
        main_shop = (RadioButton)findViewById(R.id.main_shop);
        main_shop_car = (RadioButton)findViewById(R.id.main_shop_car);
        main_my = (RadioButton)findViewById(R.id.main_my);

        vpager = (myViewPager)findViewById(R.id.vpager);
        vpager.setAdapter(viewPageAdapter);
        vpager.setCurrentItem(0);

        main_illustration.setOnClickListener(this);
        main_forum.setOnClickListener(this);
        main_shop.setOnClickListener(this);
        main_shop_car.setOnClickListener(this);
        main_my.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_illustration:
                resetSelected();
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.main_forum:
                resetSelected();
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.main_shop:
                resetSelected();
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.main_shop_car:
                resetSelected();
                vpager.setCurrentItem(PAGE_FOUR);
                break;
            case R.id.main_my:
                resetSelected();
                vpager.setCurrentItem(PAGE_FIVE);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_illustration:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.main_forum:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.main_shop:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.main_shop_car:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
            case R.id.main_my:
                vpager.setCurrentItem(PAGE_FIVE);
                break;
        }
    }
    private void resetSelected(){
        main_illustration.setSelected(false);
        main_forum.setSelected(false);
        main_shop.setSelected(false);
        main_shop_car.setSelected(false);
        main_my.setSelected(false);
    }
}
