package com.example.administrator.gametrading.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.gametrading.Adapter.GirlsAdapter;
import com.example.administrator.gametrading.Adapter.IllustrationAdapter;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.Bean.Index;
import com.example.administrator.gametrading.IndexPage.GirlsActivity;
import com.example.administrator.gametrading.IndexPage.GirlsSearchActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.GirlsService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IllustrationFragment extends LazyLoadBaseFragment{
    public IllustrationFragment(){}

    private View view;
    private ArrayList indexList = new ArrayList<>();
    public RecyclerView mRecyclerView;
    private IllustrationAdapter illustrationAdapter;
    private LinearLayout linearLayout,search;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.illustration_fragment,container,false);
        initView(view);
        initData();

        Log.e("IllustrationFragment", "IllustrationFragment");
        return view;
    }

    private void initData() {
        new GirlsService().show(getContext(),indexList,illustrationAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GirlsSearchActivity.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        indexList.clear();
                        mRecyclerView.removeAllViews();
                        new GirlsService().show(getContext(),indexList,illustrationAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });

    }


    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.index_refresh);
        linearLayout = (LinearLayout)view.findViewById(R.id.father_layout) ;
        linearLayout.getBackground().setAlpha(200);
        search = (LinearLayout)view.findViewById(R.id.to_girls_search_layout) ;
        mRecyclerView = (RecyclerView)view.findViewById(R.id.index_ill);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);

        layoutManager.setOrientation(OrientationHelper.VERTICAL);//设置为垂直布局，这也是默认的

        mRecyclerView.setLayoutManager(layoutManager);//设置布局管理器
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        illustrationAdapter = new IllustrationAdapter(getActivity(),indexList);

        mRecyclerView.setAdapter(illustrationAdapter);//设置Adapter


    }

}
