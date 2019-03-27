package com.example.administrator.gametrading.Service;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.gametrading.Bean.User;
import com.example.administrator.gametrading.Handler.RegisterHandler;
import com.example.administrator.gametrading.Inter.RegisterInter;
import com.example.administrator.gametrading.Tools;

import java.util.HashMap;
import java.util.Map;

public class RegisterService implements RegisterInter{
    @Override
    public void register(final User user, Context context) {
        // 实例一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.registerurl;
        final RegisterHandler registerHandler = new RegisterHandler(context);
        /**
         * Request.Method.POST : 请求方式  普通用户的注册
         */
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
                registerHandler.handlerMessage(message);

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
                map.put("name", user.getName());
                map.put("pass", user.getPass());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
