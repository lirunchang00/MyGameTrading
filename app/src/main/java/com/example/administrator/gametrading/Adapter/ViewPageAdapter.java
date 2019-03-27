package com.example.administrator.gametrading.Adapter;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.administrator.gametrading.MainActivity;
import com.example.administrator.gametrading.MainPage.ForumFragment;
import com.example.administrator.gametrading.MainPage.IllustrationFragment;
import com.example.administrator.gametrading.MainPage.MyFragment;
import com.example.administrator.gametrading.MainPage.ShopCarFragment;
import com.example.administrator.gametrading.MainPage.ShopFragment;

public class ViewPageAdapter extends FragmentPagerAdapter{

    private final int PAGER_COUNT = 5;
    private ForumFragment forumFragment =null;
    private IllustrationFragment illustrationFragment = null;
    private ShopFragment shopFragment = null;
    private ShopCarFragment shopCarFragment = null;
    private MyFragment myFragment = null;


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        forumFragment = new ForumFragment();
        illustrationFragment = new IllustrationFragment();
        shopCarFragment = new ShopCarFragment();
        shopFragment = new ShopFragment();
        myFragment = new MyFragment();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        System.out.print("position Destory"+position);
       // super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment =null;
        switch (i){
            case MainActivity.PAGE_ONE:
                fragment=illustrationFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment=forumFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment=shopFragment;
                break;
            case MainActivity.PAGE_FOUR:
                fragment=shopCarFragment;
                break;
            case MainActivity.PAGE_FIVE:
                fragment=myFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
