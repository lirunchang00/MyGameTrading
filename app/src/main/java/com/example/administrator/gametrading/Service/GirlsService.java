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
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.Handler.GirlsHandler;
import com.example.administrator.gametrading.Handler.ShopHandler;
import com.example.administrator.gametrading.Inter.GirlsInter;
import com.example.administrator.gametrading.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GirlsService implements GirlsInter{
    @Override
    public void add(final Girls girls, Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final GirlsHandler girlsHandler = new GirlsHandler(context);
        String url = Tools.baseUrl;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("str",response);
                Message message = new Message();
                message.obj=response;
                message.arg1=1;
                girlsHandler.handlemessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("请求失败","请求失败");
            }
        }){
            public Map<String,String>getParams()throws  AuthFailureError{
                Map<String, String> map = new HashMap<String, String>();
                map.put("No", String.valueOf(girls.getNo()));
                map.put("Name",girls.getName());
                map.put("Start", String.valueOf(girls.getStart()));
                map.put("Type",girls.getType());
                map.put("Damage", String.valueOf(girls.getDamage()));
                map.put("Shooting", String.valueOf(girls.getShooting()));
                map.put("Hit", String.valueOf(girls.getHit()));
                map.put("Avoid", String.valueOf(girls.getAvoid()));
                map.put("Portrait",girls.getPortrait());
                map.put("CirtRate", String.valueOf(girls.getCirtRate()));
                map.put("CirtDamage", String.valueOf(girls.getCirtDamage()));
                map.put("Piercing", String.valueOf(girls.getPiercing()));
                map.put("Armor", String.valueOf(girls.getArmor()));
                map.put("Aura",girls.getAura());
                map.put("Chain", String.valueOf(girls.getChain()));
                map.put("Introduction",girls.getIntroduction());
                map.put("Skin",girls.getSkin());
                map.put("Painter",girls.getPainter());
                map.put("Dubbing",girls.getDubbing());
                map.put("Hp", String.valueOf(girls.getHp()));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void show(Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        //实例一个请求序列
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //创建一个请求地址
        String url = Tools.getIndex;

        final GirlsHandler girlsHandler = new GirlsHandler(context,arrayList,arrayAdapter);
        Log.e("handler",""+girlsHandler);
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
                girlsHandler.handlemessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error+"");
            }
        }){

        };
        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);

    }

    @Override
    public void searchGirls(final String name, Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = Tools.searchGirls;

        final GirlsHandler girlsHandler = new GirlsHandler(context,arrayList,arrayAdapter);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.obj = response;
                message.arg1 = 3;
                girlsHandler.handlemessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", name);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

}
