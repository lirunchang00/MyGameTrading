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
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Handler.CollectionHandler;
import com.example.administrator.gametrading.Inter.CollectionInter;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionService implements CollectionInter{
    @Override
    public void comCollection(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.comCollection;
        final CollectionHandler collectionHandler = new CollectionHandler(context,arrayList,arrayAdapter);
        StringRequest stringRequest = new StringRequest(Request.Method.GET
                , url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 1;
                message.obj =response;
                collectionHandler.handleMessage(message);
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
    public void postCollection(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.postCollection;
        final CollectionHandler collectionHandler = new CollectionHandler(context,arrayList,arrayAdapter);
        StringRequest stringRequest = new StringRequest(Request.Method.GET
                , url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 2;
                message.obj =response;
                collectionHandler.handleMessage(message);
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
    public void deleteComCollection(final Context context, final String comId) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.deleteComCollection;
        StringRequest stringRequest = new StringRequest(Request.Method.POST
                , url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,"取消收藏",Toast.LENGTH_SHORT).show();
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

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("comId",comId);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void addComCollection(final Context context, final String comId) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.addComCollection;

        StringRequest stringRequest = new StringRequest(Request.Method.POST
                , url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("comId",comId);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void deletePostCollection(final Context context, final String postId) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.deletePostCollection;

        StringRequest stringRequest = new StringRequest(Request.Method.POST
                , url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,"取消收藏",Toast.LENGTH_SHORT).show();
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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("postId",postId);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void addPostCollection(final Context context, final String postId) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.addPostCollection;

        StringRequest stringRequest = new StringRequest(Request.Method.POST
                , url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("postId",postId);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
