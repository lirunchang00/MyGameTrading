package com.example.administrator.gametrading.ForumPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.gametrading.Adapter.ForumViewAdapter;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.MainPage.LazyLoadBaseFragment;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends LazyLoadBaseFragment {

    private FloatingActionButton zhiding,to_send_post;
    private View view;
    private ArrayList<Forum> mForumList = new ArrayList<>();
    private ForumViewAdapter forumViewAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    public AllFragment(){}
    private static  final String TAG = AllFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_forum_all,container,false);
        initView(view);
        Log.e("AllFragment", "AllFragment");
        initData();



        return view;
    }

    private void initData() {
        new PostService().allPost(getActivity(),mForumList,forumViewAdapter);

      /*  floating_btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),SendPostActivity.class);
                startActivity(intent);
            }
        });*/
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mForumList.clear();
                        recyclerView.removeAllViews();
                        new PostService().allPost(getActivity(),mForumList,forumViewAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });
        to_send_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SendPostActivity.class);
                startActivity(intent);
            }
        });
        zhiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.forum_all_refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        recyclerView = (RecyclerView)view.findViewById(R.id.forum_all_rview);
        zhiding = (FloatingActionButton)view.findViewById(R.id.zhiding);
        to_send_post = (FloatingActionButton)view.findViewById(R.id.to_send_post);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        forumViewAdapter = new ForumViewAdapter(getActivity(),mForumList);
        recyclerView.setAdapter(forumViewAdapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(),1);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;//只能返回1
            }
        });


    }
}
