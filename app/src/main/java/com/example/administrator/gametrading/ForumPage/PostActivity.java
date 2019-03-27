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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Adapter.ForumViewAdapter;
import com.example.administrator.gametrading.Adapter.PostAdapter;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.DateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class PostActivity extends AppCompatActivity {

    private View view;
    private ArrayList<Forum> mForumList = new ArrayList<>();
    private PostAdapter postAdapter;
    private RecyclerView mRecyclerView;
    private TextView title,author,content,active,floor,like,time;
    private ImageView head;
    private Intent intent;
    private Forum forum ;
    private FloatingActionButton repeatBtn;
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
                Forum last = new Forum();
                last = mForumList.get(mForumList.size()-1);
                intent.putExtra("last",last);
                startActivity(intent);
            }
        });

    }

    private void initData() {
        new PostService().showPost(PostActivity.this,mForumList,postAdapter,forum);

        try {
            Thread.sleep(1000);
            new PostService().showRepeatPost(PostActivity.this,mForumList,postAdapter,forum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void initView() {

        /*title = (TextView)findViewById(R.id.post_title);
        author= (TextView)findViewById(R.id.post_author);
        content= (TextView)findViewById(R.id.post_content);
        active= (TextView)findViewById(R.id.post_active);
        floor = (TextView)findViewById(R.id.post_floor);
        like= (TextView)findViewById(R.id.post_like1);
        time= (TextView)findViewById(R.id.post_time);
        head = (ImageView)findViewById(R.id.post_head);





        String title1 = forum.getTitle();
        title.setText(title1);
        author.setText(forum.getAuthor());
        content.setText(forum.getContent());
        floor.setText(forum.getFloor());
        time.setText(forum.getTime());
        active.setText(forum.getActive());
        like.setText(forum.getLike()+"");

        String headurl = Tools.headUrl+"a/"+forum.getHead();
        Glide.with(getApplication()).load(headurl).placeholder(R.drawable.main_my).into(head);
        boolean essence  = forum.isEssence();
        if (essence){
            title.setTextColor(Color.parseColor("#00CD00"));
        }*/


        intent = getIntent();
        forum = (Forum) intent.getParcelableExtra("forum");
        mRecyclerView = (RecyclerView)findViewById(R.id.forum_post_rview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PostActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
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
