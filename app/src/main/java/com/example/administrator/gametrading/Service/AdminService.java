package com.example.administrator.gametrading.Service;

import android.content.Context;
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
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.Inter.AdminInter;
import com.example.administrator.gametrading.Tools;

import java.util.HashMap;
import java.util.Map;

public class AdminService implements AdminInter{
    @Override
    public void deletePost(final Forum forum, final Context context) {
        String url = Tools.deletePost;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("respone",response);
                Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String,String>();
                map.put("title",forum.getTitle());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void shenhe(final Commodity commodity, final Context context) {
        String url = Tools.shenhe;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("respone",response);
                Toast.makeText(context,"审核成功",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String,String>();
                map.put("name",commodity.getComName());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void deleteIndex(final Girls girls, final Context context) {
        String url = Tools.deleteIndex;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.e("girlsdelete",girls.getName());
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("respone",response);
                Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String,String>();
                map.put("Name",girls.getName());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
