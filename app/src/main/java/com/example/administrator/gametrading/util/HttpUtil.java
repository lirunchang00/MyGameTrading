package com.example.administrator.gametrading.util;

import android.util.Log;

import com.example.administrator.gametrading.Bean.Collection;
import com.example.administrator.gametrading.Bean.Order;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void getCollection(String address, Collection collection, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("comId",String.valueOf(collection.getComId())).add("userName",collection.getUserName()).build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        Log.e("已提交收藏数据",collection.toString());
        client.newCall(request).enqueue(callback);
    }

    public static void getComList(String address,String search,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("search",search).build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
        Log.e("已提交搜索数据",search);
    }
    public static void getRecommend(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
        Log.e("已向服务器发出获取全部推荐商品的请求",address);
    }

    public static void addShopCar(String address, String userName,int comId,int num,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("userName",userName)
                .add("comId",String.valueOf(comId)).add("num", String.valueOf(num)).build();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
        Log.e("加入购物车userName",userName);
        Log.e("加入购物车comId",String.valueOf(comId));
        Log.e("加入购物车num", String.valueOf(num));
    }
    public static void getShopcar(String address,String userName,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("userName",userName).build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
        Log.e("购物车用户",userName);
    }
    public static void deleteShopcar(String address,String userName,String comId,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("userName",userName).add("comId",comId).build();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
        Log.e("删除购物车","用户名："+userName+"，商品："+comId);
    }
    public static void generateOrder(String address, Order order, okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("userName",order.getUserName()).add("totalPrice",order.getTotalPrice()).add("address",order.getAddress()).add("phone",order.getPhone()).add("receiver",order.getReceiver()).add("date",order.getDate()).build();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
        Log.e("形成订单",order.toString());
    }
    public static void formOrderCom(String address, String json, okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("json",json).build();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
        Log.e("orderCom", String.valueOf(json));
    }
    //发起网络请求
    //参数：address：要访问的地址;feedback：访问的内容；listener:回调机制，内部开启一个子线程用于网络访问
    public static void setFeedback(String address, Feedbacks feedback, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("content",feedback.getFeedbackContent()).add("tele_content",feedback.getTeleContent()).build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
        Log.e("已提交反馈的数据",address);
    }
    public static void delAllShopcar(String address,String userName,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("userName",userName).build();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
        Log.e("userName",userName);
    }
    public interface HttpCallbackListener {
        void onFinish(String response);
        void onError(Exception e);
    }
}