package com.example.administrator.gametrading.MyCenterPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.gametrading.Adapter.ComCollectionAdapter;
import com.example.administrator.gametrading.Adapter.MyOrderAdapter;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Order;
import com.example.administrator.gametrading.Bean.OrderBean;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.OrderService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {
    private Button back;
    private View view;
    private MyOrderAdapter myOrderAdapter;
    private OrderBean order;
    private ArrayList<OrderBean> mOrderList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
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
        new OrderService().showMyOrder(MyOrderActivity.this,mOrderList,myOrderAdapter);
    }

    private void initView() {
        back = (Button)findViewById(R.id.my_order_back);
        recyclerView = (RecyclerView)findViewById(R.id.my_order_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyOrderActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        myOrderAdapter = new MyOrderAdapter(MyOrderActivity.this,mOrderList);
        recyclerView.setAdapter(myOrderAdapter);
    }
}
