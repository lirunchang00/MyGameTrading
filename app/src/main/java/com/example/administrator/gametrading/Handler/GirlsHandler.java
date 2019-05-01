package com.example.administrator.gametrading.Handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.Girls;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GirlsHandler extends Handler{
    private Context context;
    private ArrayList<Girls> arrayList;
    private String gethead;
    private ArrayList<Girls> old = new ArrayList<>();
    private RecyclerView.Adapter arrayAdapter;

    public GirlsHandler(Context context){
        this.context  = context;
    }

    public GirlsHandler(Context context,ArrayList arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    public GirlsHandler(Context context, ArrayList arrayList,RecyclerView.Adapter arrayAdapter){
        this.context = context;
        this.arrayList = arrayList;
        this.arrayAdapter = arrayAdapter;
    }

    public void handlemessage(Message msg){
        super.handleMessage(msg);
        int i = msg.arg1;
        switch (i)
        {
            case 1:
                String back = msg.obj.toString();
                try{
                    JSONObject jsonObject = new JSONObject(back);
                    int resCode = jsonObject.getInt("resCode");
                    if (resCode==1){
                        Toast.makeText(context,"添加成功",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(context,"添加失败",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

                //加载图鉴
            case 2:
                Log.e("message",msg.obj.toString());
                try{
                    //封装成jsonarray对象
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    //String cookie=new ConnectViaSession(context).GetSession();
                    //Log.e("cookie",cookie+"");
                    //循环遍历我们的jsonobject
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Girls girls = new Girls();
                        girls.setNo(jsonObject.getString("no"));
                        girls.setName(jsonObject.getString("name"));
                        girls.setTypePic(jsonObject.getString("typePic"));
                        girls.setHead(jsonObject.getString("head"));
                        arrayList.add(girls);
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 3:
                Log.e("message",msg.obj.toString());
                try{
                    //封装成jsonarray对象
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    //String cookie=new ConnectViaSession(context).GetSession();
                    //Log.e("cookie",cookie+"");
                    //循环遍历我们的jsonobject
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Girls girls = new Girls();
                        girls.setNo(jsonObject.getString("no"));
                        girls.setName(jsonObject.getString("name"));
                        girls.setTypePic(jsonObject.getString("typePic"));
                        girls.setHead(jsonObject.getString("head"));
                        arrayList.add(girls);
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
        }
    }
}
