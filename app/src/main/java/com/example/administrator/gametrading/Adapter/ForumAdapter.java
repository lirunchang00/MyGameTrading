package com.example.administrator.gametrading.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.administrator.gametrading.ForumPage.AllFragment;
import com.example.administrator.gametrading.ForumPage.EssenceFragment;
import com.example.administrator.gametrading.MainActivity;
import com.example.administrator.gametrading.MainPage.ForumFragment;

import java.util.List;


public class ForumAdapter extends FragmentPagerAdapter{

    private List<Fragment> list;

    public ForumAdapter(FragmentManager fm) {
        super(fm);
    }

    public ForumAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
