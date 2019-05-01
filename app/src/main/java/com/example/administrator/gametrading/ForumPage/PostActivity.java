package com.example.administrator.gametrading.ForumPage;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Adapter.ForumViewAdapter;
import com.example.administrator.gametrading.Adapter.PostAdapter;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.PostCollection;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.CollectionService;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.DateUtil;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class PostActivity extends AppCompatActivity {

    private View view;
    private ArrayList<Forum> mForumList = new ArrayList<>();
    private PostAdapter postAdapter;
    private RecyclerView mRecyclerView;
    private ImageView head,back;
    private Intent intent;
    private Forum forum =new Forum();
    private PostCollection postCollection;
    private FloatingActionButton repeatBtn;
    private LinearLayout linearLayout;
    private TextView post_title_activity;
    private ToggleButton post_collection_btn;
    int postid;
    String author,title;
    boolean essence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        initView();
        initData();

        repeatBtn = (FloatingActionButton )findViewById(R.id.post_repeat_btn);
        repeatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this,RepeatPostActivity.class);
                Forum last;
                last = mForumList.get(mForumList.size()-1);
                last.setPostid(forum.getPostid());
                intent.putExtra("last",last);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        intent = getIntent();
        if (intent.getParcelableExtra("forum")!=null) {
            forum = (Forum) intent.getParcelableExtra("forum");
        }else if (intent.getParcelableExtra("postCollection")!=null){
            postCollection = (PostCollection)intent.getParcelableExtra("postCollection");
            postid = postCollection.getPostId();
            author = postCollection.getAuthor();
            title = postCollection.getTitle();
            essence = postCollection.isEssence();
            Log.e("asdas",postCollection.toString()+"postid"+postid+"author"+author);
            forum.setPostid(postid);
            forum.setAuthor(author);
            forum.setTitle(title);
            forum.setEssence(essence);
        }
        boolean essence  = forum.isEssence();
        if (essence){
            post_title_activity.setTextColor(Color.parseColor("#00CD00"));
        }
        post_title_activity.setText(forum.getTitle());
        new PostService().showPost(PostActivity.this,mForumList,postAdapter,forum);

        try {
            Thread.sleep(500);
            new PostService().showRepeatPost(PostActivity.this,mForumList,postAdapter,forum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        post_collection_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String postId = null;
                if (intent.getParcelableExtra("forum")!=null) {
                    forum = (Forum) intent.getParcelableExtra("forum");
                    postid = forum.getPostid();
                    postId = String.valueOf(postid);
                }else if (intent.getParcelableExtra("postCollection")!=null){
                    postCollection = (PostCollection)intent.getParcelableExtra("postCollection");
                    postid = postCollection.getPostId();
                    postId = String.valueOf(postid);
                }
                if (isChecked){
                    new CollectionService().addComCollection(PostActivity.this,postId);
                    post_collection_btn.setTextOn("取消收藏");
                }else {
                    new CollectionService().deleteComCollection(PostActivity.this,postId);
                    post_collection_btn.setTextOff("添加收藏");
                }
            }
        });

    }

    private void initView() {
        post_collection_btn = (ToggleButton)findViewById(R.id.post_collection_btn);
        post_title_activity = (TextView) findViewById(R.id.post_title_activity);
        back = (ImageView)findViewById(R.id.post_back);
        mRecyclerView = (RecyclerView)findViewById(R.id.forum_post_rview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PostActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        postAdapter = new PostAdapter(PostActivity.this,mForumList);
        Collections.sort(mForumList, new Comparator<Forum>() {
            @Override
            public int compare(Forum o1, Forum o2) {
                Date date1 = DateUtil.stringToDate(o1.getTime());
                Date date2 = DateUtil.stringToDate(o2.getTime());
                if (date1.before(date2)){
                    return 1;
                }
                return -1;
            }
        });
        mRecyclerView.setAdapter(postAdapter);

    }
}
