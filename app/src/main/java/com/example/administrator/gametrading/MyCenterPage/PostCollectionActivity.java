package com.example.administrator.gametrading.MyCenterPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.gametrading.Adapter.MyPostAdapter;
import com.example.administrator.gametrading.Adapter.MySolderAdapter;
import com.example.administrator.gametrading.Adapter.PostCollectionAdapter;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.PostCollection;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.CollectionService;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class PostCollectionActivity extends AppCompatActivity {
    private Button back;
    private View view;
    private PostCollectionAdapter postCollectionAdapter;
    private PostCollection postCollection;
    private ArrayList<PostCollection> mForumList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_collection);
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
        new CollectionService().postCollection(PostCollectionActivity.this,mForumList,postCollectionAdapter);
    }

    private void initView() {
        back = (Button)findViewById(R.id.post_collection_back);
        recyclerView = (RecyclerView)findViewById(R.id.post_collection_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PostCollectionActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        postCollectionAdapter = new PostCollectionAdapter(PostCollectionActivity.this,mForumList);
        recyclerView.setAdapter(postCollectionAdapter);
    }
}
