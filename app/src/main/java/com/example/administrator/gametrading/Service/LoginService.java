package com.example.administrator.gametrading.Service;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.gametrading.Bean.User;
import com.example.administrator.gametrading.Inter.LoginInter;
import com.example.administrator.gametrading.Handler.LoginHandler;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;
import com.example.administrator.gametrading.util.UserManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginService implements LoginInter{
    @Override
    public void login(final User user, final Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.loginurl;
        final String name = user.getName();
        final String pwd = user.getPass();
        final LoginHandler loginHandler = new LoginHandler(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //返回信息
                Log.e("str", s);

                try{
                    JSONObject jsonObject = new JSONObject(s);
                    int resCode = jsonObject.getInt("resCode");
                    if (resCode==1){
                        User user1 = DataSupport.where("name=?",name).findFirst(User.class);
                        if (user1==null){
                            user1 = new User();
                            user1.setName(name);
                            user1.setPass(pwd);
                            user1.save();
                        }
                        UserManager.setCurrentUser(user1);// 设置当前用户
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }

                Message message = new Message();
                // 1登录
                message.arg1 = 1;
                message.obj = s;
                loginHandler.handlerMessage(message);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }


        }){
        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            //获取session
            Response<String> superResponse = super.parseNetworkResponse(response);
            Map<String, String> responseHeaders = response.headers;
            Log.e("SessionID",superResponse+"");
            String rawCookies = responseHeaders.get("Set-Cookie");
            Log.e("rawCookies",rawCookies+"");
            String SessionID= rawCookies.substring(0, rawCookies.indexOf(";"));
            //Constant是一个自建的类，存储常用的全局变量
            Log.e("SessionID",SessionID+"");
            new SessionUtil(context).SaveSession(SessionID);
            return superResponse;
        }
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



    @Override
    public void head(final Context context, ArrayList arrayList) {

    }

    @Override
    public void editpass(final User user, Context context) {

    }
}
