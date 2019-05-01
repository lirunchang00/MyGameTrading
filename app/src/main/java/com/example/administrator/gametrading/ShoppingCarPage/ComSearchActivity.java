
package com.example.administrator.gametrading.ShoppingCarPage;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.gametrading.Adapter.TrandingAdapter;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.ForumPage.PostSearchActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.OrderService;
import com.example.administrator.gametrading.Service.PostService;
import com.example.administrator.gametrading.Service.ShopService;

import java.util.ArrayList;

public class ComSearchActivity extends AppCompatActivity {
    private ImageView com_search_back,com_search_delete,com_search_enter;
    private EditText com_search_editText;
    private RecyclerView recyclerView;
    private TrandingAdapter trandingAdapter;
    private ProgressBar progressBar;
    private ArrayList<Commodity> arrayList= new ArrayList<>();
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_search);

        initData();
    }

    private void initData() {
        com_search_back = (ImageView)this.findViewById(R.id.com_search_back);
        com_search_delete = (ImageView)this.findViewById(R.id.com_search_delete);
        com_search_enter = (ImageView)this.findViewById(R.id.com_search_enter);
        com_search_editText = (EditText) this.findViewById(R.id.com_search_editText);
        progressBar = (ProgressBar)this.findViewById(R.id.com_search_bar);
        String name = "";
        intent = getIntent();


        if (intent.getStringExtra("name")!=null) {

            name = intent.getStringExtra("name");
            Log.e("name",name);
            com_search_editText.setText(name);
        }
        recyclerView = (RecyclerView)this.findViewById(R.id.com_search_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ComSearchActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        trandingAdapter = new TrandingAdapter(ComSearchActivity.this,arrayList);
        recyclerView.setAdapter(trandingAdapter);

        com_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        com_search_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com_search_editText.setText("");
            }
        });

        com_search_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    com_search_delete.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    com_search_delete.setVisibility(View.VISIBLE);
                    //显示ListView
                    // showListView();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        com_search_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrayList.clear();
                        recyclerView.removeAllViews();
                        if (TextUtils.isEmpty(com_search_editText.getText().toString().trim())){
                            Toast.makeText(ComSearchActivity.this,"请输入搜索内容",Toast.LENGTH_LONG).show();
                        }else {

                            String a = com_search_editText.getText().toString();
                            new ShopService().searchCom(a,ComSearchActivity.this,arrayList,trandingAdapter);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },1000);
            }
        });
    }
}
