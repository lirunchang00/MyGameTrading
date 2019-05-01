package com.example.administrator.gametrading.AdminPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.gametrading.Adapter.AdminIndexAdapter;
import com.example.administrator.gametrading.Adapter.IllustrationAdapter;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.IndexPage.GirlsSearchActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.GirlsService;
import com.example.administrator.gametrading.Service.ShopService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class DeleteIndexActivity extends AppCompatActivity {
    private ArrayList<Girls> indexList = new ArrayList<>();
    public RecyclerView mRecyclerView;
    private AdminIndexAdapter adminIndexAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_index);
        initView();
        initData();
    }

    private void initView() {
        back = (Button)findViewById(R.id.admin_delete_back);
        swipeRefreshLayout  =(SwipeRefreshLayout)findViewById(R.id.admin_index_refresh);
        mRecyclerView = (RecyclerView)findViewById(R.id.admin_index_recyclerview);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);

        layoutManager.setOrientation(OrientationHelper.VERTICAL);//设置为垂直布局，这也是默认的

        mRecyclerView.setLayoutManager(layoutManager);//设置布局管理器
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        adminIndexAdapter = new AdminIndexAdapter(DeleteIndexActivity.this,indexList);

        mRecyclerView.setAdapter(adminIndexAdapter);//设置Adapter

    }

    private void initData() {
        new GirlsService().show(DeleteIndexActivity.this,indexList,adminIndexAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        indexList.clear();
                        mRecyclerView.removeAllViews();
                        new GirlsService().show(DeleteIndexActivity.this,indexList,adminIndexAdapter);
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
}
