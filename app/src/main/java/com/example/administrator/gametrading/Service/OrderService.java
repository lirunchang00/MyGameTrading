package com.example.administrator.gametrading.Service;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.OrderBean;
import com.example.administrator.gametrading.Handler.OrderHandler;
import com.example.administrator.gametrading.Handler.RegisterHandler;
import com.example.administrator.gametrading.Inter.OrderInter;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderService implements OrderInter{
    @Override
    public void addCom(final Commodity commodity, Context context) {
        // 实例一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.addCom;
        final OrderHandler orderHandler = new OrderHandler(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //返回信息
                Log.e("str", s);
                Message message = new Message();
                // 1注册
                message.arg1 = 1;
                message.obj = s;
                orderHandler.handleMessage(message);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //实例一个map对象
                Map<String, String> map = new HashMap<String, String>();
                //组装我们的请求信息
                map.put("comName", commodity.getComName());
                map.put("comContent",commodity.getComContent());
                map.put("comPrice",commodity.getComPrice());
                map.put("comImage",commodity.getComImage());
                map.put("comSpecial",commodity.getComSpecial());
                map.put("operating",commodity.getOperating());
                map.put("comServer",commodity.getComServer());
                map.put("comMethod",commodity.getComMethod());
                map.put("solder",commodity.getSolder());
                map.put("type",commodity.getType());
                map.put("date",commodity.getDate());
                map.put("account",commodity.getAccount());
                map.put("password",commodity.getPassword());
                map.put("character",commodity.getCharacter());
                map.put("secretPhone",commodity.getSecretPhone());
                map.put("phone",commodity.getPhone());
                map.put("qq",commodity.getQq());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void showMyOrder(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = Tools.showMyOrder;

        final OrderHandler orderHandler = new OrderHandler(context,arrayList,arrayAdapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 2;
                message.obj =response;
                orderHandler.handleMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            public Map<String,String> getHeaders()throws AuthFailureError {
                String cookie =new SessionUtil(context).GetSession();
                Log.e("cookie",cookie+"");
                if (!cookie.equals("")){
                    HashMap<String,String> headers = new HashMap<String,String>();
                    headers.put("cookie",cookie);
                    return headers;
                }else {
                    return super.getHeaders();
                }
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void mySolder(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = Tools.mySolder;

        final OrderHandler orderHandler = new OrderHandler(context,arrayList,arrayAdapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 3;
                message.obj =response;
                orderHandler.handleMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            public Map<String,String> getHeaders()throws AuthFailureError {
                String cookie =new SessionUtil(context).GetSession();
                Log.e("cookie",cookie+"");
                if (!cookie.equals("")){
                    HashMap<String,String> headers = new HashMap<String,String>();
                    headers.put("cookie",cookie);
                    return headers;
                }else {
                    return super.getHeaders();
                }
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void comDetail(final Context context,final Commodity commodity) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = Tools.comDetail;

        final OrderHandler orderHandler = new OrderHandler(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.obj = response;
                message.arg1 =4;
                orderHandler.handleMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"网络访问出错",Toast.LENGTH_LONG).show();
            }
        }){
            public Map<String,String> getHeaders()throws AuthFailureError {
                String cookie =new SessionUtil(context).GetSession();
                Log.e("cookie",cookie+"");
                if (!cookie.equals("")){
                    HashMap<String,String> headers = new HashMap<String,String>();
                    headers.put("cookie",cookie);
                    return headers;
                }else {
                    return super.getHeaders();
                }
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String ,String>();
                map.put("comName", commodity.getComName());
                map.put("comContent",commodity.getComContent());
                map.put("comPrice",commodity.getComPrice());
                map.put("comImage",commodity.getComImage());
                map.put("comSpecial",commodity.getComSpecial());
                map.put("operating",commodity.getOperating());
                map.put("comServer",commodity.getComServer());
                map.put("comMethod",commodity.getComMethod());
                map.put("solder",commodity.getSolder());
                map.put("type",commodity.getType());
                map.put("date",commodity.getDate());
                map.put("account",commodity.getAccount());
                map.put("password",commodity.getPassword());
                map.put("character",commodity.getCharacter());
                map.put("secretPhone",commodity.getSecretPhone());
                map.put("phone",commodity.getPhone());
                map.put("qq",commodity.getQq());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void makeOrder(final Commodity commodity,final Context context) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.e("makeOrder",commodity.toString());
        String url = Tools.makeOrder;

        final OrderHandler orderHandler = new OrderHandler(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               /* Message message = new Message();
                message.obj = response;
                message.arg1 =4;
                orderHandler.handleMessage(message);*/
               Log.e("makeroder","success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"网络访问出错",Toast.LENGTH_LONG).show();
            }
        }){
            public Map<String,String> getHeaders()throws AuthFailureError {
                String cookie =new SessionUtil(context).GetSession();
                Log.e("cookie",cookie+"");
                if (!cookie.equals("")){
                    HashMap<String,String> headers = new HashMap<String,String>();
                    headers.put("cookie",cookie);
                    return headers;
                }else {
                    return super.getHeaders();
                }
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String ,String>();
                map.put("comName", commodity.getComName());
                map.put("clientPhone",commodity.getClientPhone());
                map.put("comPrice",commodity.getComPrice());
                map.put("comImage",commodity.getComImage());
                map.put("email",commodity.getEmail());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
