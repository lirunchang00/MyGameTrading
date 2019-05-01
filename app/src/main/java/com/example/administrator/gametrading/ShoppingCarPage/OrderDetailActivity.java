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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.OrderBean;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView order_id,order_detail_name,order_detail_num,order_detail_one_price,order_detail_pay;
    private TextView order_detail_pay_status,order_detail_operating,order_detail_type,order_detail_server;
    private TextView order_detail_content,order_detail_method,order_detail_special,order_detail_email,order_detail_phone,order_detail_qq;
    private ImageView com_detail_image,com_details_back;
    private Intent intent;
    private Commodity commodity;
    private OrderBean orderBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initView();
        initData();
        com_details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        intent = getIntent();
        if (intent.getSerializableExtra("commodity")!=null){
            commodity = (Commodity) intent.getSerializableExtra("commodity");
            Log.e("qq",commodity.getQq());
            Log.e("emial",commodity.getEmail());
            order_detail_qq.setText(commodity.getQq());
            order_detail_type.setText(commodity.getType());
            order_detail_email.setText(commodity.getEmail());
            order_detail_phone.setText(commodity.getClientPhone());
            order_detail_operating.setText(commodity.getOperating());
            order_detail_method.setText(commodity.getComMethod());
            order_detail_server.setText(commodity.getComServer());
            order_detail_content.setText(commodity.getComContent());
            order_detail_special.setText(commodity.getComSpecial());
            commodityData();
        }else if (intent.getSerializableExtra("orderBean")!=null){
            orderBean = (OrderBean)intent.getSerializableExtra("orderBean");
            order_id.setText(orderBean.getOrderID()+"");
            orderBeanData();
        }


    }

    private void orderBeanData() {
        RequestQueue requestQueue = Volley.newRequestQueue(OrderDetailActivity.this);

        String url = Tools.getCom;

        final OrderDetailHandler orderDetailHandler = new OrderDetailHandler();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Message message = new Message();
                        message.obj = response;
                        message.arg1 = 2;
                        orderDetailHandler.handleMessage(message);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("comName",orderBean.getComName());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void commodityData() {
        RequestQueue requestQueue = Volley.newRequestQueue(OrderDetailActivity.this);

        String url = Tools.getOrder;

        final OrderDetailHandler orderDetailHandler = new OrderDetailHandler();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Message message = new Message();
                        message.obj = response;
                        message.arg1 = 1;
                        orderDetailHandler.handleMessage(message);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            public Map<String,String> getHeaders()throws AuthFailureError {
                String cookie = new SessionUtil(OrderDetailActivity.this).GetSession();
                Log.e("cookie", cookie + "");
                if (!cookie.equals("")) {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("cookie", cookie);
                    return headers;
                } else {
                    return super.getHeaders();
                }
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("comName",commodity.getComName());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void initView() {
        com_details_back = (ImageView) findViewById(R.id.com_details_back);
        order_id = (TextView) findViewById(R.id.order_id);
        order_detail_name = (TextView) findViewById(R.id.order_detail_name);
        order_detail_num = (TextView) findViewById(R.id.order_detail_num);
        order_detail_one_price = (TextView) findViewById(R.id.order_detail_one_price);
        order_detail_pay = (TextView) findViewById(R.id.order_detail_pay);
        order_detail_pay_status = (TextView) findViewById(R.id.order_detail_pay_status);
        order_detail_operating = (TextView) findViewById(R.id.order_detail_operating);
        order_detail_type = (TextView) findViewById(R.id.order_detail_type);
        order_detail_server = (TextView) findViewById(R.id.order_detail_server);
        order_detail_content = (TextView) findViewById(R.id.order_detail_content);
        order_detail_method = (TextView) findViewById(R.id.order_detail_method);
        order_detail_special = (TextView) findViewById(R.id.order_detail_special);
        order_detail_email = (TextView) findViewById(R.id.order_detail_email);
        order_detail_phone = (TextView) findViewById(R.id.order_detail_phone);
        order_detail_qq = (TextView) findViewById(R.id.order_detail_qq);
    }
    @SuppressLint("HandlerLeak")
    public class OrderDetailHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.arg1;
            switch (i){
                case 1:
                    try {
                        JSONArray jsonArray = new JSONArray(msg.obj.toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        order_id.setText(jsonObject.getInt("orderId")+"");
                        order_detail_name.setText(jsonObject.getString("comName"));
                        order_detail_num.setText(1+"");
                        order_detail_one_price.setText(jsonObject.getString("price"));
                        order_detail_pay.setText("微信");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        JSONArray jsonArray = new JSONArray(msg.obj.toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        order_detail_name.setText(jsonObject.getString("comName"));
                        order_detail_num.setText(1+"");
                        order_detail_one_price.setText(jsonObject.getString("comPrice"));
                        order_detail_email.setText(jsonObject.getString("email"));
                        order_detail_phone.setText(jsonObject.getString("clientPhone"));
                        order_detail_pay.setText("微信");
                        order_detail_qq.setText(jsonObject.getString("qq"));
                        order_detail_type.setText(jsonObject.getString("type"));
                        order_detail_operating.setText(jsonObject.getString("operating"));
                        order_detail_method.setText(jsonObject.getString("method"));
                        order_detail_server.setText(jsonObject.getString("server"));
                        order_detail_content.setText(jsonObject.getString("content"));
                        order_detail_special.setText(jsonObject.getString("special"));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
