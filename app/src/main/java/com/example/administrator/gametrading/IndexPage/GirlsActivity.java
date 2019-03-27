package com.example.administrator.gametrading.IndexPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.gametrading.Adapter.GirlsAdapter;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.GirlsService;

import java.util.ArrayList;

public class GirlsActivity extends AppCompatActivity {
    private GirlsAdapter girlsAdapter;
    private ArrayList arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls);

        arrayList = new ArrayList();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.girls);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        girlsAdapter = new GirlsAdapter(GirlsActivity.this,arrayList);
        recyclerView.setAdapter(girlsAdapter);
        initData();

    }

    private void initData() {
        new GirlsService().show(GirlsActivity.this,arrayList,girlsAdapter);
    }
}
