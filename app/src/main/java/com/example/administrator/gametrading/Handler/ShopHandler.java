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
import com.example.administrator.gametrading.MyCenterPage.LoginActivity;
import com.example.administrator.gametrading.util.DateUtil;
import com.example.administrator.gametrading.util.SessionUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

public class ShopHandler extends Handler{

    private Context context;
    private ArrayList arrayList ;
    private RecyclerView.Adapter arrayAdapter;
    private Map<String,String> map;
    public ShopHandler(Context context){
        this.context  = context;
    }
    public ShopHandler(Context context,Map<String,String> map){
        this.context  = context;
        this.map = map;
    }
    public ShopHandler(Context context,ArrayList arrayList){
        this.context  = context;
        this.arrayList = arrayList;
    }
    public ShopHandler(Context context, ArrayList arrayList,RecyclerView.Adapter arrayAdapter){
        this.context = context;
        this.arrayList = arrayList;
        this.arrayAdapter = arrayAdapter;
    }
    public  void  handlerMessage(Message msg){
        super.handleMessage(msg);
        int i = msg.arg1;
        switch (i){
            case 1:
                Log.e("message",msg.obj.toString());
                try{
                    //封装成jsonarray对象
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    String cookie=new SessionUtil(context).GetSession();
                    Log.e("cookie",cookie+"");
                    //循环遍历我们的jsonobject
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);

                        Commodity commodity = new Commodity();
                        commodity.setComId(jsonObject.getInt("comId"));
                        commodity.setComName(jsonObject.getString("comName"));
                        commodity.setComContent(jsonObject.getString("comContent"));
                        commodity.setComNum(jsonObject.getString("comNum"));
                        commodity.setComPrice(jsonObject.getString("comPrice"));
                        commodity.setComSpecial(jsonObject.getString("comSpecial"));
                        commodity.setSolder(jsonObject.getString("solder"));
                        commodity.setActive(jsonObject.getInt("active"));
                        commodity.setComImage(jsonObject.getString("comImage"));
                        commodity.setComMethod(jsonObject.getString("comMethod"));
                        commodity.setComServer(jsonObject.getString("comServer"));
                        commodity.setOperating(jsonObject.getString("operating"));
                        commodity.setType(jsonObject.getString("type"));
                        Log.e("commodity",commodity+"");
                        arrayList.add(commodity);
                        Collections.sort(arrayList, new Comparator<Commodity>() {
                            @Override
                            public int compare(Commodity o1, Commodity o2) {
                                int active1 = o1.getActive();
                                int active2 = o2.getActive();
                                if (active1<active2){
                                    return 1;
                                }
                                return -1;
                            }
                        });
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;

            case 2:
                Log.e("message",msg.obj.toString());
                try{
                    //封装成jsonarray对象
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    String cookie=new SessionUtil(context).GetSession();
                    Log.e("cookie",cookie+"");
                    //循环遍历我们的jsonobject
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Commodity commodity = new Commodity();
                        commodity.setComId(jsonObject.getInt("comId"));
                        commodity.setComName(jsonObject.getString("comName"));
                        commodity.setComContent(jsonObject.getString("comContent"));
                        commodity.setComNum(jsonObject.getString("comNum"));
                        commodity.setComPrice(jsonObject.getString("comPrice"));
                        commodity.setComSpecial(jsonObject.getString("comSpecial"));
                        commodity.setSolder(jsonObject.getString("solder"));
                        commodity.setActive(jsonObject.getInt("active"));
                        commodity.setComImage(jsonObject.getString("comImage"));
                        commodity.setComMethod(jsonObject.getString("comMethod"));
                        commodity.setType(jsonObject.getString("type"));
                        commodity.setComServer(jsonObject.getString("comServer"));
                        commodity.setOperating(jsonObject.getString("operating"));
                        Log.e("commodity",commodity.toString()+"");
                        arrayList.add(commodity);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
        }

    }
}
