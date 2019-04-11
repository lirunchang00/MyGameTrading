package com.example.administrator.gametrading.ForumPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SessionUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RepeatPostActivity extends AppCompatActivity {
    private Intent intent;
    private String Cookie="";
    private Context context;
    private ImageView back,send_repeat;
    private EditText send_repeat_content;
    private String content;
    private Forum  forum,myForum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_post);





        initView();
        initData();

    }

    private void initData() {


        send_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRepeat();
                new PostService().repeatPost(RepeatPostActivity.this,myForum);
            }
        });
    }

    private void sendRepeat() {
        intent=getIntent();
        content=send_repeat_content.getText().toString();
        Cookie = new SessionUtil(getApplicationContext()).GetSession();
        Log.e("Cookie",""+Cookie);
        forum = (Forum)intent.getParcelableExtra("last");

        int postid = forum.getPostid();
        int floor = Integer.valueOf(forum.getFloor())+1;
        String title = forum.getTitle();
        Log.e("postid",postid+"");
        Log.e("floor",floor+"");
        String floor1 = String.valueOf(floor);

        myForum = new Forum();
        Date date = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date.getTime());

        myForum.setPostid(postid);
        myForum.setFloor(floor1);
        myForum.setContent(content);
        myForum.setTime(time);
        String cookie = Cookie.substring(5);
        myForum.setAuthor(cookie);
        myForum.setTitle(title);
        Log.e("forum",myForum.toString());
    }

    private void initView() {
        back = (ImageView)findViewById(R.id.post_repeat_back);
        send_repeat = (ImageView)findViewById(R.id.send_repeat_post);
        send_repeat_content = (EditText)findViewById(R.id.send_repeat_post_content);

    }
}
