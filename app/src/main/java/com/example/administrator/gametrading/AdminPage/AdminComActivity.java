package com.example.administrator.gametrading.AdminPage;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.gametrading.Adapter.AdminComAdapter;
import com.example.administrator.gametrading.Adapter.TrandingAdapter;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.ShopService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class AdminComActivity extends AppCompatActivity {
    private ArrayList<Commodity> commodityList = new ArrayList<>();
    private AdminComAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_com);
        initView();
        initData();
    }
    private void initData() {
        new ShopService().waitCom(AdminComActivity.this,commodityList,adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commodityList.clear();
                        recyclerView.removeAllViews();
                        new ShopService().waitCom(AdminComActivity.this,commodityList,adapter);
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
        back = (Button)findViewById(R.id.admin_com_back) ;
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.admin_com_refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        recyclerView = (RecyclerView)findViewById(R.id.admin_com_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AdminComActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        adapter = new AdminComAdapter(AdminComActivity.this,commodityList);
        recyclerView.setAdapter(adapter);
    }
}
