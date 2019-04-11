package com.example.administrator.gametrading.Handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.Order;
import com.example.administrator.gametrading.Bean.OrderBean;
import com.example.administrator.gametrading.MainActivity;
import com.example.administrator.gametrading.MyCenterPage.LoginActivity;
import com.example.administrator.gametrading.MyCenterPage.MyOrderActivity;
import com.example.administrator.gametrading.MyCenterPage.MySolderActivity;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderHandler extends Handler{
    private Context context;
    private ArrayList arrayList;
    private RecyclerView.Adapter adapter;
    public OrderHandler(Context context){
        this.context = context;
    }
    public OrderHandler(Context context, ArrayList arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    public OrderHandler(Context context, ArrayList arrayList, RecyclerView.Adapter adapter){
        this.context = context;
        this.arrayList = arrayList;
        this.adapter =adapter;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int i = msg.arg1;
        switch (i){
            case 1:
                String message = msg.obj.toString();
                try {
                    JSONObject jsonObject = new JSONObject(message);
                    int resCode = jsonObject.getInt("resCode");
                    if (resCode==1){
                        Toast.makeText(context,"成功提交，等待审核",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, MySolderActivity.class);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context,"提交失败",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 2:
                Log.e("message",msg.obj.toString());
                try{
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        OrderBean orderBean = new OrderBean();
                        orderBean.setComName(jsonObject.getString("comName"));
                        orderBean.setDate(jsonObject.getString("date"));
                        orderBean.setEmail(jsonObject.getString("email"));
                        orderBean.setOrderID(jsonObject.getInt("orderId"));
                        orderBean.setPhone(jsonObject.getString("phone"));
                        orderBean.setStatus(jsonObject.getString("status"));
                        orderBean.setPrice(jsonObject.getString("price"));
                        orderBean.setOrderImage(jsonObject.getString("orderImage"));
                        orderBean.setUsername(jsonObject.getString("userName"));
                        arrayList.add(orderBean);
                    }
                    if(adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 3:
                Log.e("message",msg.obj.toString());
                try{
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Commodity commodity =new Commodity();
                        commodity.setComId(jsonObject.getInt("comId"));
                        commodity.setComImage(jsonObject.getString("comImage"));
                        commodity.setSolder(jsonObject.getString("solder"));
                        commodity.setComPrice(jsonObject.getString("comPrice"));
                        commodity.setComName(jsonObject.getString("comName"));
                        commodity.setStatus(jsonObject.getString("status"));
                        Log.e("commodity",commodity.toString());
                        arrayList.add(commodity);

                    }
                    if(adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
        }
    }
}
