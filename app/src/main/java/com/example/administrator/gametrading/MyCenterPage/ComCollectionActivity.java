package com.example.administrator.gametrading.MyCenterPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.example.administrator.gametrading.Adapter.ComCollectionAdapter;
import com.example.administrator.gametrading.Adapter.ForumViewAdapter;
import com.example.administrator.gametrading.Adapter.TrandingAdapter;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.CollectionService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class ComCollectionActivity extends AppCompatActivity {
    private Button back;
    private View view;
    private ComCollectionAdapter comCollectionAdapter;
    private CollectionBean collectionBean;
    private ArrayList<CollectionBean> mCollectionBeanList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_collection);
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
        new CollectionService().comCollection(ComCollectionActivity.this,mCollectionBeanList,comCollectionAdapter);
    }

    private void initView() {
        back = (Button)findViewById(R.id.com_collection_back);
        recyclerView = (RecyclerView)findViewById(R.id.com_collection_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ComCollectionActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        comCollectionAdapter = new ComCollectionAdapter(ComCollectionActivity.this,mCollectionBeanList);
        recyclerView.setAdapter(comCollectionAdapter);

    }
}
