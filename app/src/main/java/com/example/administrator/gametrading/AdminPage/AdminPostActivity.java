package com.example.administrator.gametrading.AdminPage;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.gametrading.Adapter.AdminPostAdapter;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.GirlsService;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class AdminPostActivity extends AppCompatActivity {
    private AdminPostAdapter adminPostAdapter;
    private ArrayList<Forum> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_post);
        initView();
        initData();


    }

    private void initData() {
        new PostService().allPost(AdminPostActivity.this,arrayList,adminPostAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrayList.clear();
                        recyclerView.removeAllViews();
                        new PostService().allPost(AdminPostActivity.this,arrayList,adminPostAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        back = (Button)findViewById(R.id.admin_post_back);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.admin_post_refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        recyclerView = (RecyclerView)findViewById(R.id.admin_post_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AdminPostActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        adminPostAdapter = new AdminPostAdapter(AdminPostActivity.this,arrayList);
        recyclerView.setAdapter(adminPostAdapter);
    }
}
