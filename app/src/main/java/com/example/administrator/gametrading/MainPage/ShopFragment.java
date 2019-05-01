package com.example.administrator.gametrading.MainPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.gametrading.Adapter.TrandingAdapter;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.ShopService;
import com.example.administrator.gametrading.ShoppingCarPage.AddComActivity;
import com.example.administrator.gametrading.ShoppingCarPage.ComSearchActivity;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class ShopFragment extends LazyLoadBaseFragment {

    private ArrayList<Commodity> commodityList = new ArrayList<>();
    private FloatingActionButton fb_button;
    private static final int CHANGE_UI = 1;
    private TrandingAdapter adapter;
    private View view;
    private Button to_add_com;
    private LinearLayout top_shop;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    public ShopFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.shop_fragment,container,false);
        Log.e("ShopFragment", "ShopFragment");
        initView(view);
        init();
        initData();
        return view;
    }

    private void initData() {
        new ShopService().allCom(getActivity(),commodityList,adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commodityList.clear();
                        recyclerView.removeAllViews();
                        new ShopService().allCom(getActivity(),commodityList,adapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });

    }

    private void init() {
        to_add_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddComActivity.class);
                startActivity(intent);
            }
        });
        top_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ComSearchActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.shop_refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        to_add_com = (Button)view.findViewById(R.id.to_add_com);
        top_shop = (LinearLayout)view.findViewById(R.id.top_shop);
        recyclerView = (RecyclerView)view.findViewById(R.id.shop_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        adapter = new TrandingAdapter(getActivity(),commodityList);
        recyclerView.setAdapter(adapter);
    }

}
