package com.example.administrator.gametrading.MyCenterPage;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.gametrading.Adapter.MyPostAdapter;
import com.example.administrator.gametrading.Adapter.MyRepeatAdapter;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class MyRepeatActivity extends AppCompatActivity {
    private Button back;
    private View view;
    private MyRepeatAdapter myRepeatAdapter;
    private CollectionBean collectionBean;
    private ArrayList<Forum> mForumList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_repeat);
        initView();
        initData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initData() {
        new PostService().myRepeat(MyRepeatActivity.this,mForumList,myRepeatAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mForumList.clear();
                        recyclerView.removeAllViews();
                        new PostService().myRepeat(MyRepeatActivity.this,mForumList,myRepeatAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.my_repeat_refresh);
        back = (Button)findViewById(R.id.my_repeat_back);
        recyclerView = (RecyclerView)findViewById(R.id.my_repeat_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyRepeatActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        myRepeatAdapter = new MyRepeatAdapter(MyRepeatActivity.this,mForumList);
        recyclerView.setAdapter(myRepeatAdapter);
    }
}
