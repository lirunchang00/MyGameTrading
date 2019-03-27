package com.example.administrator.gametrading.MainPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.gametrading.Adapter.ForumAdapter;
import com.example.administrator.gametrading.Adapter.ViewPageAdapter;
import com.example.administrator.gametrading.ForumPage.AllFragment;
import com.example.administrator.gametrading.ForumPage.EssenceFragment;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.util.myViewPager;

import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends LazyLoadBaseFragment implements ViewPager.OnPageChangeListener,View.OnClickListener{

    private List<Fragment> list;
    private View view;
    private ViewPager viewPager;
    private Button all_forum,essence_forum;

    public static final int PAGE_ONE =   0;
    public static final int PAGE_TWO =   1;


    public ForumFragment(){}
    private static  final String TAG = ForumFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.forum_fragment,container,false);
        initView(view);
        Log.e("FORUM", "FORUM");
        return view;
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        viewPager = (ViewPager)view.findViewById(R.id.forum_viewpager);
        list = new ArrayList<>();
        all_forum = (Button)view.findViewById(R.id.all_forum);
        essence_forum = (Button)view.findViewById(R.id.essence_forum);

        all_forum.setOnClickListener(this);
        essence_forum.setOnClickListener(this);

        list.add(new AllFragment());
        list.add(new EssenceFragment());

        viewPager.setAdapter(new ForumAdapter(getChildFragmentManager(),list));
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
        /*
        forum_detail = (RadioGroup)view.findViewById(R.id.forum_detail_bar);
        all_forum = (RadioButton)view.findViewById(R.id.all_forum);
        essence_forum = (RadioButton)view.findViewById(R.id.essence_forum);

        viewPager = new ForumAdapter(getFragmentManager());

        pager = (myViewPager)view.findViewById(R.id.forum_viewpager);
        pager.setAdapter(viewPager);
        pager.setCurrentItem(0);

        all_forum.setOnClickListener(this);
        essence_forum.setOnClickListener(this);*/
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        resetSelected();
        switch (i){
            case 0:
                all_forum.setBackgroundResource(R.drawable.all_forum_light);
                break;
            case 1:
                essence_forum.setBackgroundResource(R.drawable.all_forum_light);
                break;
        }
    }

    private void resetSelected() {
       all_forum.setBackgroundResource(R.drawable.all_forum_unlight);
       essence_forum.setBackgroundResource(R.drawable.all_forum_unlight);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        resetSelected();
        switch (v.getId()){
            case R.id.all_forum:
                all_forum.setBackgroundResource(R.drawable.all_forum_light);
                viewPager.setCurrentItem(0);
                break;
            case R.id.essence_forum:
                essence_forum.setBackgroundResource(R.drawable.all_forum_light);
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
