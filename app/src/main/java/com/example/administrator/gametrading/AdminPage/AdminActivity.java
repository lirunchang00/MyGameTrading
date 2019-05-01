package com.example.administrator.gametrading.AdminPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.gametrading.R;

public class AdminActivity extends AppCompatActivity {
    private RelativeLayout admin_com,admin_post,add_index,delete_index;
    private TextView reback_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admin_com = (RelativeLayout)findViewById(R.id.admin_com);
        admin_post = (RelativeLayout)findViewById(R.id.admin_post);
        add_index = (RelativeLayout)findViewById(R.id.add_index);
        delete_index = (RelativeLayout)findViewById(R.id.delete_index);
        reback_main = (TextView)findViewById(R.id.reback_main);

        reback_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        admin_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,AdminComActivity.class);
                startActivity(intent);
            }
        });
        admin_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,AdminPostActivity.class);
                startActivity(intent);
            }
        });
        add_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,AddIndexActivity.class);
                startActivity(intent);
            }
        });
        delete_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,DeleteIndexActivity.class);
                startActivity(intent);
            }
        });
    }
}
