package com.example.administrator.gametrading.Handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.PostCollection;
import com.example.administrator.gametrading.util.DateUtil;
import com.example.administrator.gametrading.util.SessionUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class CollectionHandler extends Handler{
    private Context context;
    private ArrayList arrayList;
    private RecyclerView.Adapter arrayAdapter;

    public CollectionHandler (Context context){
        this.context = context;
    }
    public CollectionHandler (Context context,ArrayList arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    public CollectionHandler (Context context,ArrayList arrayList,RecyclerView.Adapter adapter){
        this.context = context;
        this.arrayList = arrayList;
        this.arrayAdapter = adapter;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int i = msg.arg1;
        switch (i){
            //商品收藏
            case 1:
                try{
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    String cookie = new SessionUtil(context).GetSession();
                    Log.e("jsonArray",jsonArray+"");
                    for (int index = 0; index<jsonArray.length();index++){
                        JSONObject jsonObject =jsonArray.getJSONObject(index);

                        /**
                         * collection取user表中的collection数组
                         * b转化为的collection数组
                         * c转化为object类型
                         */
                        String head = jsonObject.getString("head");
                        //collectionBean.setHead(jsonObject.getString("head"));
                        String collection = jsonObject.getString("comCollection");
                        JSONArray b = new JSONArray(collection);
                        JSONObject c = b.getJSONObject(0);
                        /**
                         * commodity取collection数组里的commodities
                         * d转化为community数组
                         * commodities遍历数组，拿到具体需要的东西
                         */
                        String status = c.getString("status");
                        //collectionBean.setStatus(c.getString("status"));
                        String commodity = c.getString("commodities");
                        JSONArray d = new JSONArray(commodity);
                        for (int com=0;com<d.length();com++){
                            CollectionBean collectionBean = new CollectionBean();
                            JSONObject commodities = d.getJSONObject(com);
                            Log.e("commodities",""+commodities);
                            collectionBean.setComId(commodities.getInt("comId"));//商品ID号
                            collectionBean.setComName(commodities.getString("comName"));                     //商品名字
                            collectionBean.setImage(commodities.getString("comImage"));
                            collectionBean.setSolder(commodities.getString("solder"));
                            collectionBean.setPrice(commodities.getString("comPrice"));
                            collectionBean.setStatus(status);
                            collectionBean.setHead(head);
                            arrayList.add(collectionBean);
                            Log.e("arraylist",arrayList+"");
                        }

                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 2:
                Log.e("message",msg.obj.toString());
                try{

                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    String cookie = new SessionUtil(context).GetSession();
                    Log.e("jsonArray",jsonArray+"");
                    for (int index = 0; index<jsonArray.length();index++){
                        JSONObject jsonObject =jsonArray.getJSONObject(index);
                        PostCollection postCollection = new PostCollection();
                        /**
                         * collection取user表中的collection数组
                         * b转化为的collection数组
                         * c转化为object类型
                         */

                        String collection = jsonObject.getString("postCollection");
                        JSONArray b = new JSONArray(collection);
                        JSONObject c = b.getJSONObject(0);
                        /**
                         * commodity取collection数组里的commodities
                         * d转化为community数组
                         * commodities遍历数组，拿到具体需要的东西
                         */
                        String forum = c.getString("forums");
                        JSONArray d = new JSONArray(forum);
                        for (int com=0;com<d.length();com++){
                            JSONObject forums = d.getJSONObject(com);
                            Log.e("forums",""+forums);

                            postCollection.setAuthor(forums.getString("author"));
                            postCollection.setDate(forums.getString("time"));
                            postCollection.setPostId(forums.getInt("postid"));
                            postCollection.setRepeat(forums.getString("repeat"));
                            postCollection.setTitle(forums.getString("title"));
                            postCollection.setEssence(forums.getBoolean("essence"));
                            arrayList.add(postCollection);
                        }
                        Log.e("arraylist",arrayList+"");
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }

    }
}
