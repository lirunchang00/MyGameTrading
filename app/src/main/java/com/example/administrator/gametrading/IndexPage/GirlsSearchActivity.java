package com.example.administrator.gametrading.IndexPage;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Adapter.IllustrationAdapter;
import com.example.administrator.gametrading.Adapter.TrandingAdapter;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.Handler.GirlsHandler;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.GirlsService;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;
import com.example.administrator.gametrading.util.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GirlsSearchActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private LinearLayout type,start;
    private ImageView search_enter,search_delete;
    private EditText girls_search_editText;
    private ImageView search_back;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private IllustrationAdapter illustrationAdapter;
    private ArrayList<Girls> arrayList = new ArrayList<>();
    private ArrayList<Girls> old =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_search);

        search_delete = (ImageView)findViewById(R.id.girls_search_delete);
        girls_search_editText = (EditText)findViewById(R.id.girls_search_editText);
        type = (LinearLayout)this.findViewById(R.id.girls_search_type);
        start = (LinearLayout)this.findViewById(R.id.girls_search_start);
        search_back = (ImageView) findViewById(R.id.girls_search_back);
        search_enter = (ImageView) findViewById(R.id.girls_search_enter);
        recyclerView = (RecyclerView)this.findViewById(R.id.girls_search_recyclerView);


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);//设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        illustrationAdapter = new IllustrationAdapter(GirlsSearchActivity.this,arrayList);
        recyclerView.setAdapter(illustrationAdapter);//设置Adapter

        girls_search_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    type.setVisibility(View.VISIBLE);
                    //start.setVisibility(View.VISIBLE);
                }
            }
        });
        girls_search_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    search_delete.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    search_delete.setVisibility(View.VISIBLE);
                    //显示ListView
                    // showListView();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        search_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girls_search_editText.setText("");
            }
        });


        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        search_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                recyclerView.removeAllViews();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        String typeSearch = "";
                        String startSearch = "";
                        int typeNum = type.getChildCount();
                        for (int o =0;o<typeNum;o++){
                            CheckBox cb = (CheckBox)type.getChildAt(o);
                            if(cb.isChecked()){
                                //把被选中的文字添加到StringBuffer中
                                typeSearch = cb.getText().toString();
                                initType(typeSearch);
                            }
                        }
                        Log.e("中文",arrayList.toString());
                        int StartNum = start.getChildCount();
                        for (int o =0;o<StartNum;o++){
                            CheckBox cb = (CheckBox)start.getChildAt(o);
                            if(cb.isChecked()){
                                //把被选中的文字添加到StringBuffer中
                                startSearch = cb.getText().toString();
                                initStart(startSearch);
                            }
                        }
                        if (TextUtils.isEmpty(girls_search_editText.getText().toString().trim())){
                            Toast.makeText(GirlsSearchActivity.this,"请输入搜索内容",Toast.LENGTH_LONG).show();
                        }else {

                            String name = girls_search_editText.getText().toString();
                            new GirlsService().searchGirls(name,GirlsSearchActivity.this,arrayList,illustrationAdapter);
                        }
                    }
                },1000);
            }
        });
    }

    private void initType(final String typeSearch) {
        RequestQueue requestQueue = Volley.newRequestQueue(GirlsSearchActivity.this);

        final String url = Tools.searchGirlsByType;

        final MyHandler girlsHandler = new MyHandler();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                Message message = new Message();
                message.obj = response;
                message.arg1 = 1;
                girlsHandler.handleMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String ,String > map = new HashMap<String,String>();
               map.put("typeSearch",typeSearch);
               return  map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void initStart(final String startSearch) {
        RequestQueue requestQueue = Volley.newRequestQueue(GirlsSearchActivity.this);

        final String url = Tools.searchGirlsByStart;

        final MyHandler girlsHandler = new MyHandler();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                if (response=="[]"){
                    Toast.makeText(GirlsSearchActivity.this,"无此关键词",Toast.LENGTH_SHORT).show();
                }
                Message message = new Message();
                message.obj = response;
                message.arg1 = 1;
                girlsHandler.handleMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String > map = new HashMap<String,String>();
                map.put("startSearch",startSearch);
                return  map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @SuppressLint("HandlerLeak")
    public class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.arg1;
            switch (i){
                case 1:
                    old.clear();
                    Log.e("message",msg.obj.toString());
                    try{
                        //封装成jsonarray对象
                        JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                        //String cookie=new ConnectViaSession(context).GetSession();
                        //Log.e("cookie",cookie+"");
                        //循环遍历我们的jsonobject
                        for(int index=0; index< jsonArray.length();index++){
                            JSONObject jsonObject = jsonArray.getJSONObject(index);
                            Girls girls = new Girls();
                            girls.setNo(jsonObject.getString("no"));
                            girls.setName(jsonObject.getString("name"));
                            girls.setTypePic(jsonObject.getString("typePic"));
                            girls.setHead(jsonObject.getString("head"));
                            old.add(girls);

                            //arrayList.add(girls);
                        }
                        Log.e("old",old.toString());
                        Set<Girls> set = new HashSet<Girls>();
                        for (Girls cd : old) {
                            boolean add = set.add(cd);
                            if (add){
                                arrayList.add(cd);
                            }
                        }
                        Log.e("set",set.toString());
                        if(illustrationAdapter != null) {
                            illustrationAdapter.notifyDataSetChanged();
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    break;
            }
        }
    }

}
