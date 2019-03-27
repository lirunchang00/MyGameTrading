package com.example.administrator.gametrading.ForumPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.util.SessionUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendPostActivity extends AppCompatActivity {
    private Button send_post_back,send_post,send_post_sle_pic;
    private EditText send_post_content,send_post_title;
    private PostService postService;
    private ImageView send_post_pic;
    private String title,content;
    private Context context;
    private String Cookie="";
    private String image;
    private Forum forum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_post);
        Cookie = "lrc";//new SessionUtil(context).GetSession();
        Log.e("Cookie",""+Cookie);

        initView();
        initData();
    }

    private void initView() {

        send_post = (Button)findViewById(R.id.send_post);
        send_post_back = (Button)findViewById(R.id.send_post_back);
        send_post_title =(EditText)findViewById(R.id.send_post_title);
        send_post_content = (EditText)findViewById(R.id.send_post_content);
        send_post_sle_pic = (Button)findViewById(R.id.send_post_sle_pic);
        send_post_pic = (ImageView)findViewById(R.id.send_post_pic);

    }

    private void initData() {

        send_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
                postService.sendPost(forum,SendPostActivity.this);
            }
        });

    }

    private void input() {

        title = send_post_title.getText().toString();
        content = send_post_content.getText().toString();
        Log.e("title+content",title+content);

        forum = new Forum();
        postService = new PostService();

        Date date = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date.getTime());

        forum.setTitle(title);
        forum.setContent(content);
        forum.setTime(time);
        forum.setAuthor(Cookie);
    }
}
