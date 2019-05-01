package com.example.administrator.gametrading.ShoppingCarPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Collection;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Handler.ShopHandler;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.CollectionService;
import com.example.administrator.gametrading.Service.ShopService;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComDetailsActivity extends AppCompatActivity {
    private ImageView com_detail_image;
    private Button back;
    private TextView com_detail_name,com_detail_content,com_detail_num,com_detail_price,com_detail_special;
    private TextView com_detail_server,com_detail_operating,com_detail_method,com_detail_solder,com_detail_active,com_detail_type;
    private Commodity commodity;
    private Button to_buy;
    private ToggleButton add_to_collection;
    private CollectionBean collectionBean;
    private ArrayList<Commodity> list = new ArrayList<>();
    private Map<String,String> map;
    //private String id;
    //private ArrayList<Commodity> arrayList= new ArrayList<>();
    private Intent intent;
    private String comId;
    private String TAG = "comdetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_details);
        intent = getIntent();
        //Log.e("intent",intent.getSerializableExtra("collectionBean").toString());
        initview();
        if (intent.getSerializableExtra("commodity")!=null) {
            initData();
        }
        if (intent.getSerializableExtra("collectionBean")!=null){
            initCollection();
        }
        if (intent.getSerializableExtra("solder")!=null){
            add_to_collection.setVisibility(View.INVISIBLE);
            to_buy.setVisibility(View.INVISIBLE);
            initSolder();
        }

        to_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComDetailsActivity.this,OrderActivity.class);
                intent.putExtra("makeOrder",commodity);
                startActivity(intent);
            }
        });


        add_to_collection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(collectionBean!=null){
               comId = String.valueOf(collectionBean.getComId());
                }
                if (commodity!=null){
                    comId = String.valueOf(commodity.getComId());
                }
                if (isChecked){
                    new CollectionService().addComCollection(ComDetailsActivity.this,comId);
                    add_to_collection.setTextOn("取消收藏");
                }else {
                    new CollectionService().deleteComCollection(ComDetailsActivity.this,comId);
                    add_to_collection.setTextOff("添加收藏");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initSolder() {
        commodity = (Commodity)intent.getSerializableExtra("solder");
        final String comId = String.valueOf(commodity.getComId());
       // String status = collectionBean.getStatus();

        /* new ShopService().showDetail(ComDetailsActivity.this,comId,list);*/


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //创建一个请求地址
        String url = Tools.showDetail;

        final MyHandler shopHandler = new MyHandler();
        Log.e("PSerivece","PSerivece");
        /**
         * Request.Method.POST : 请求方式
         */
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 1;
                message.obj =response;
                shopHandler.handleMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error+"");
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                //实例一个map对象
                Map<String, String> map = new HashMap<String, String>();
                //组装我们的请求信息
                map.put("id", comId);
                return map;
            }
        };

        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);
    }

    private void initCollection() {
        collectionBean = (CollectionBean)intent.getSerializableExtra("collectionBean");

        final String comId = String.valueOf(collectionBean.getComId());
        String status = collectionBean.getStatus();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //创建一个请求地址
        String url = Tools.showDetail;

        final MyHandler shopHandler = new MyHandler();
        Log.e("PSerivece","PSerivece");
        /**
         * Request.Method.POST : 请求方式
         */
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 1;
                message.obj =response;
                shopHandler.handleMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error+"");
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                //实例一个map对象
                Map<String, String> map = new HashMap<String, String>();
                //组装我们的请求信息
                map.put("id", comId);
                return map;
            }
        };

        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);

    }

    private void initData() {
        commodity = (Commodity) intent.getSerializableExtra("commodity");

        Log.e("commodity",commodity.getComName());
        com_detail_name.setText(commodity.getComName());
        com_detail_content.setText(commodity.getComContent());
        com_detail_num.setText(commodity.getComNum());
        com_detail_price.setText(commodity.getComPrice());
        com_detail_special.setText(commodity.getComSpecial());
        com_detail_server.setText(commodity.getComServer());
        com_detail_operating.setText(commodity.getOperating());
        com_detail_method.setText(commodity.getComMethod());
        com_detail_solder.setText(commodity.getSolder());
        com_detail_type.setText(commodity.getType());
        com_detail_active.setText(commodity.getActive()+"");
        String pic = Tools.headUrl+"a/"+commodity.getComImage();
        Glide.with(ComDetailsActivity.this).load(pic).placeholder(R.mipmap.picc).into(com_detail_image);



     }


    private void initview() {
        com_detail_name = (TextView)findViewById(R.id.com_details_name);
        com_detail_content = (TextView)findViewById(R.id.com_details_content);
        com_detail_num = (TextView)findViewById(R.id.com_details_num);
        com_detail_price = (TextView)findViewById(R.id.com_details_price);
        com_detail_special = (TextView)findViewById(R.id.com_details_special);
        com_detail_server = (TextView)findViewById(R.id.com_details_server);
        com_detail_operating = (TextView)findViewById(R.id.com_details_operating);
        com_detail_method = (TextView)findViewById(R.id.com_details_method);
        com_detail_solder = (TextView)findViewById(R.id.com_details_solder);
        com_detail_active = (TextView)findViewById(R.id.com_details_active);
        com_detail_type = (TextView)findViewById(R.id.com_details_type);
        com_detail_image = (ImageView)findViewById(R.id.com_details_image);
        add_to_collection = (ToggleButton)findViewById(R.id.add_to_collection);
        to_buy = (Button)findViewById(R.id.to_buy);
        back = (Button) findViewById(R.id.com_details_back);


    }

    @SuppressLint("HandlerLeak")
    public class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.arg1;
            switch (i){
                case 1:
                    Log.e("message",msg.obj.toString());
                    try{
                        //封装成jsonarray对象
                        JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                        String cookie=new SessionUtil(ComDetailsActivity.this).GetSession();
                        Log.e("cookie",cookie+"");
                        //循环遍历我们的jsonobject
                        for(int index=0; index< jsonArray.length();index++){
                            JSONObject jsonObject = jsonArray.getJSONObject(index);
                            Commodity commodity = new Commodity();
                            commodity.setComId(jsonObject.getInt("comId"));
                            commodity.setComName(jsonObject.getString("comName"));
                            commodity.setComContent(jsonObject.getString("comContent"));
                            commodity.setComNum(jsonObject.getString("comNum"));
                            commodity.setComPrice(jsonObject.getString("comPrice"));
                            commodity.setComSpecial(jsonObject.getString("comSpecial"));
                            commodity.setSolder(jsonObject.getString("solder"));
                            commodity.setActive(jsonObject.getInt("active"));
                            commodity.setType(jsonObject.getString("type"));
                            commodity.setComImage(jsonObject.getString("comImage"));
                            commodity.setComMethod(jsonObject.getString("comMethod"));
                            commodity.setComServer(jsonObject.getString("comServer"));
                            commodity.setOperating(jsonObject.getString("operating"));
                            Log.e("commodity",commodity.toString()+"");
                            Log.e("commodity",commodity.getComName());
                            com_detail_name.setText(commodity.getComName());
                            com_detail_content.setText(commodity.getComContent());
                            com_detail_num.setText(commodity.getComNum());
                            com_detail_price.setText(commodity.getComPrice());
                            com_detail_special.setText(commodity.getComSpecial());
                            com_detail_server.setText(commodity.getComServer());
                            com_detail_operating.setText(commodity.getOperating());
                            com_detail_method.setText(commodity.getComMethod());
                            com_detail_solder.setText(commodity.getSolder());
                            com_detail_active.setText(commodity.getActive()+"");
                            com_detail_type.setText(commodity.getType());
                            String pic = Tools.headUrl+"a/"+commodity.getComImage();
                            Glide.with(ComDetailsActivity.this).load(pic).placeholder(R.mipmap.picc).into(com_detail_image);
                        }

                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    break;
            }
        }
    }
}
