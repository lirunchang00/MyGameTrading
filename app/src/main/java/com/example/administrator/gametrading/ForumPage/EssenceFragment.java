package com.example.administrator.gametrading.ForumPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;

public class EssenceFragment extends LazyLoadBaseFragment{
    private View view;
    private ArrayList<Forum> mForumList = new ArrayList<>();
    private ForumViewAdapter forumViewAdapter;
    private RecyclerView recyclerView;
    public EssenceFragment(){}
    private static  final String TAG = EssenceFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_forum_essence,container,false);
        initView(view);
        initData();
        Log.e("EssenceFragment", "EssenceFragment");
        return view;
    }

    private void initData() {
        new PostService().essencePost(getActivity(),mForumList,forumViewAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        recyclerView = (RecyclerView)view.findViewById(R.id.forum_essence_rview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        forumViewAdapter = new ForumViewAdapter(getActivity(),mForumList);
        recyclerView.setAdapter(forumViewAdapter);
    }
}