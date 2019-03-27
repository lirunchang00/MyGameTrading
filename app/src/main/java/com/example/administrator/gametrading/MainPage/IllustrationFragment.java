package com.example.administrator.gametrading.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.gametrading.Adapter.IllustrationAdapter;
import com.example.administrator.gametrading.Bean.Index;
import com.example.administrator.gametrading.IndexPage.GirlsActivity;
import com.example.administrator.gametrading.R;

import java.util.ArrayList;
import java.util.List;

public class IllustrationFragment extends LazyLoadBaseFragment{
    public IllustrationFragment(){}

    private View view;
    private List<Index> indexList = new ArrayList<>();
    public RecyclerView mRecyclerView;
    private IllustrationAdapter illustrationAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.illustration_fragment,container,false);
        initView(view);
        initRecyclerView();
        initData();

        Log.e("IllustrationFragment", "IllustrationFragment");
        return view;
    }

    private void initData() {
        Intent intent = new Intent(getContext(),GirlsActivity.class);
        Index doll = new Index("doll",R.mipmap.forum_yellow,intent);
        indexList.add(doll);
        Index fairy = new Index("fairy",R.mipmap.forum_blank,intent);
        indexList.add(fairy);
        Index reload = new Index("reload",R.mipmap.illustration_blank,intent);
        indexList.add(reload);
        Index linkage = new Index("linkage",R.mipmap.illustration_yellow,intent);
        indexList.add(linkage);
    }

    private void initRecyclerView() {


    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.index_ill);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        layoutManager.setOrientation(OrientationHelper.VERTICAL);//设置为垂直布局，这也是默认的

        mRecyclerView.setLayoutManager(layoutManager);//设置布局管理器

        illustrationAdapter = new IllustrationAdapter(getActivity(),indexList);

        mRecyclerView.setAdapter(illustrationAdapter);//设置Adapter
    }
}
