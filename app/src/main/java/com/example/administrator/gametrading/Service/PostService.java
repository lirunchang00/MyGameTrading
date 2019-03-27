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
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Handler.PostHandler;
import com.example.administrator.gametrading.Inter.PostInter;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostService implements PostInter {


    @Override
    public void allPost(Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        //实例一个请求序列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //创建一个请求地址
        String url = Tools.forumAllUrl;

        final PostHandler postHandler = new PostHandler(context,arrayList,arrayAdapter);
        Log.e("PSerivece","PSerivece");
        /**
         * Request.Method.POST : 请求方式
         */
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 2;
                message.obj =response;
                postHandler.handlemessage(message);
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
    public void essencePost(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        //实例一个请求序列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //创建一个请求地址
        String url = Tools.forumEssenceUrl;

        final PostHandler handler = new PostHandler(context,arrayList,arrayAdapter);

        /**
         * Request.Method.POST : 请求方式
         */
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 3;
                message.obj =response;
                handler.handlemessage(message);
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
    public void sendPost(final Forum forum,final Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final PostHandler postHandler = new PostHandler(context);
        String url = Tools.sendPostUrl;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("str",response);
                Message message = new Message();
                message.obj=response;
                message.arg1=4;
                postHandler.handlemessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
           /* public Map<String,String> getHeaders()throws AuthFailureError {
                String cookie =new SessionUtil(context).GetSession();
                Log.e("cookie",cookie+"");
                if (!cookie.equals("")){
                    HashMap<String,String> headers = new HashMap<String,String>();
                    headers.put("cookie",cookie);

                    return headers;
                }else {
                    return super.getHeaders();
                }
            }*/
            public Map<String,String>getParams()throws  AuthFailureError{
                Map<String, String> map = new HashMap<String, String>();
                map.put("author",forum.getAuthor());
                map.put("content",forum.getContent());
                map.put("title",forum.getTitle());
                map.put("time",forum.getTime());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void showRepeatPost(final Context context, ArrayList arrayList, RecyclerView.Adapter adapter,final Forum forum) {
        //实例一个请求序列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //创建一个请求地址
        String url = Tools.showPostUrl;
        Log.e("cookie",url+"");
        final String  id =String.valueOf(forum.getId());
        final PostHandler postHandler = new PostHandler(context,arrayList,adapter);
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
                postHandler.handlemessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
           /* public Map<String,String>getHeaders()throws AuthFailureError{
                String cookie =new SessionUtil(context).GetSession();
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
                Map<String , String> map = new HashMap<String, String>();
                //组装我们的请求信息
                map.put("id", id);
                return map;
            }
        };
        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);
    }

    @Override
    public void showPost(Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter, Forum forum) {
        //实例一个请求序列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //创建一个请求地址
        String url = Tools.showPost;
        Log.e("cookie",url+"");
        final String  id =String.valueOf(forum.getId());
        final PostHandler postHandler = new PostHandler(context,arrayList,arrayAdapter);
        /**
         * Request.Method.POST : 请求方式
         */
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Str",response);
                Message message = new Message();
                message.arg1 = 5;
                message.obj =response;
                postHandler.handlemessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            /* public Map<String,String>getHeaders()throws AuthFailureError{
                 String cookie =new SessionUtil(context).GetSession();
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
                Map<String , String> map = new HashMap<String, String>();
                //组装我们的请求信息
                map.put("id", id);
                return map;
            }
        };
        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);
    }


    @Override
    public void repeatPost(Context context,final Forum forum) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final PostHandler postHandler = new PostHandler(context);
        String url = Tools.repeatPostUrl;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("str",response);
                Message message = new Message();
                message.obj=response;
                message.arg1=6;
                postHandler.handlemessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            /* public Map<String,String> getHeaders()throws AuthFailureError {
                 String cookie =new SessionUtil(context).GetSession();
                 Log.e("cookie",cookie+"");
                 if (!cookie.equals("")){
                     HashMap<String,String> headers = new HashMap<String,String>();
                     headers.put("cookie",cookie);

                     return headers;
                 }else {
                     return super.getHeaders();
                 }
             }*/
            public Map<String,String>getParams()throws  AuthFailureError{
                Map<String, String> map = new HashMap<String, String>();
                map.put("author",forum.getAuthor());
                map.put("content",forum.getContent());
                map.put("floor",forum.getFloor());
                map.put("time",forum.getTime());
                map.put("postid",String.valueOf(forum.getPostid()));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
