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

import com.example.administrator.gametrading.Adapter.MyOrderAdapter;
import com.example.administrator.gametrading.Adapter.MyRepeatAdapter;
import com.example.administrator.gametrading.Adapter.MySolderAdapter;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.OrderBean;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.OrderService;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class MySolderActivity extends AppCompatActivity {
    private Button back;
    private View view;
    private MySolderAdapter mySolderAdapter;
    private Commodity commodity;
    private ArrayList<Commodity> mCommodityList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_solder);
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
        new OrderService().mySolder(MySolderActivity.this,mCommodityList,mySolderAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCommodityList.clear();
                        recyclerView.removeAllViews();
                        new OrderService().mySolder(MySolderActivity.this,mCommodityList,mySolderAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.my_solder_refresh);
        back = (Button)findViewById(R.id.my_solder_back);
        recyclerView = (RecyclerView)findViewById(R.id.my_solder_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MySolderActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        mySolderAdapter = new MySolderAdapter(MySolderActivity.this,mCommodityList);
        recyclerView.setAdapter(mySolderAdapter);
    }
}
