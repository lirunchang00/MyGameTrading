package com.example.administrator.gametrading.Service;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.gametrading.Handler.PostHandler;
import com.example.administrator.gametrading.Handler.ShopHandler;
import com.example.administrator.gametrading.Inter.ShopInter;
import com.example.administrator.gametrading.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopService implements ShopInter{
    @Override
    public void allCom(Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        //实例一个请求序列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //创建一个请求地址
        String url = Tools.allComUrl;

        final ShopHandler shopHandler = new ShopHandler(context,arrayList,arrayAdapter);
        Log.e("PSerivece","PSerivece");
        /**
         * Request.Method.POST : 请求方式
         */
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 1;
                message.obj =response;
                shopHandler.handlerMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error+"");
            }
        }){
            /*public Map<String,String>getHeaders()throws AuthFailureError{
                String cookie =new ConnectViaSession(context).GetSession();
                Log.e("cookie",cookie+"");
                if (!cookie.equals("")){
                    HashMap<String,String> headers = new HashMap<String,String>();
                    headers.put("cookie",cookie);
                    return headers;
                }else {
                    return super.getHeaders();
                }
            }*/
        };
        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);
    }

    @Override
    public void showDetail(Context context, final String id,ArrayList arrayList) {
        //实例一个请求序列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //创建一个请求地址
        String url = Tools.showDetail;

        final ShopHandler shopHandler = new ShopHandler(context,arrayList);
        Log.e("PSerivece","PSerivece");
        /**
         * Request.Method.POST : 请求方式
         */
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 2;
                message.obj =response;
                shopHandler.handlerMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error+"");
            }
        }){
            /*public Map<String,String>getHeaders()throws AuthFailureError{
                String cookie =new ConnectViaSession(context).GetSession();
                Log.e("cookie",cookie+"");
                if (!cookie.equals("")){
                    HashMap<String,String> headers = new HashMap<String,String>();
                    headers.put("cookie",cookie);
                    return headers;
                }else {
                    return super.getHeaders();
                }
            }*/
            protected Map<String, String> getParams() throws AuthFailureError {
                //实例一个map对象
                Map<String, String> map = new HashMap<String, String>();
                //组装我们的请求信息
                map.put("id", id);
                return map;
            }
        };

        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);
    }
}
