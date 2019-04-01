package com.example.administrator.gametrading.MainPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.gametrading.Adapter.TrandingAdapter;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.ShopService;

import java.util.ArrayList;

public class ShopFragment extends LazyLoadBaseFragment {

    private ArrayList<Commodity> commodityList = new ArrayList<>();
    private FloatingActionButton fb_button;
    private static final int CHANGE_UI = 1;
    private TrandingAdapter adapter;
    private View view;

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

    }

    private void init() {

    }



    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.shop_recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TrandingAdapter(getContext(),commodityList);
        recyclerView.setAdapter(adapter);


    }

}
