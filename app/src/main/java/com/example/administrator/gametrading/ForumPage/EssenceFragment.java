package com.example.administrator.gametrading.ForumPage;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.gametrading.Adapter.ForumViewAdapter;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.MainPage.LazyLoadBaseFragment;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class EssenceFragment extends LazyLoadBaseFragment{
    private View view;
    private ArrayList<Forum> mForumList = new ArrayList<>();
    private ForumViewAdapter forumViewAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    public EssenceFragment(){}
    private static  final String TAG = EssenceFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_forum_essence,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        new PostService().essencePost(getActivity(),mForumList,forumViewAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mForumList.clear();
                        recyclerView.removeAllViews();
                        new PostService().essencePost(getActivity(),mForumList,forumViewAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
                forumViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.forum_essence_refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        recyclerView = (RecyclerView)view.findViewById(R.id.forum_essence_rview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        forumViewAdapter = new ForumViewAdapter(getActivity(),mForumList);
        recyclerView.setAdapter(forumViewAdapter);
    }
}
