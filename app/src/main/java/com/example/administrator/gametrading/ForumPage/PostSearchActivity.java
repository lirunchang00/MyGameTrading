package com.example.administrator.gametrading.ForumPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.gametrading.Adapter.ForumViewAdapter;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.PostService;

import java.util.ArrayList;

public class PostSearchActivity extends AppCompatActivity {
    private ImageView post_search_enter,post_search_delete,post_search_back;
    private EditText post_search_editText;
    private ArrayList<Forum> mForumList = new ArrayList<>();
    private ForumViewAdapter forumViewAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_search);

        initView();
    }

    private void initView() {
        post_search_back =(ImageView)this.findViewById(R.id.post_search_back);
        post_search_enter =(ImageView)this.findViewById(R.id.post_search_enter);
        post_search_delete =(ImageView)this.findViewById(R.id.post_search_delete);
        post_search_editText =(EditText)this.findViewById(R.id.post_search_editText);


        recyclerView = (RecyclerView)this.findViewById(R.id.post_search_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PostSearchActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        forumViewAdapter = new ForumViewAdapter(PostSearchActivity.this,mForumList);
        recyclerView.setAdapter(forumViewAdapter);

        post_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        post_search_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_search_editText.setText("");
            }
        });

        post_search_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    post_search_delete.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    post_search_delete.setVisibility(View.VISIBLE);
                    //显示ListView
                   // showListView();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        post_search_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(post_search_editText.getText().toString().trim())){
                    Toast.makeText(PostSearchActivity.this,"请输入搜索内容",Toast.LENGTH_LONG).show();
                }else {
                    String a = post_search_editText.getText().toString();
                    Log.e("a",a);
                    new PostService().searchPost(a,PostSearchActivity.this,mForumList,forumViewAdapter);
                }
            }
        });
    }
}
