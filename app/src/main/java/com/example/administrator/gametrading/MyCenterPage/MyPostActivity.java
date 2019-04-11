package com.example.administrator.gametrading.MyCenterPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.gametrading.Adapter.ComCollectionAdapter;
import com.example.administrator.gametrading.Adapter.MyPostAdapter;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;

public class MyPostActivity extends AppCompatActivity {
    private Button back;
    private View view;
    private MyPostAdapter myPostAdapter;
    private CollectionBean collectionBean;
    private ArrayList<Forum> mForumList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

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
        new PostService().myPost(MyPostActivity.this,mForumList,myPostAdapter);
    }

    private void initView() {
        back = (Button)findViewById(R.id.my_post_back);
        recyclerView = (RecyclerView)findViewById(R.id.my_post_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyPostActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        myPostAdapter = new MyPostAdapter(MyPostActivity.this,mForumList);
        recyclerView.setAdapter(myPostAdapter);
    }
}
