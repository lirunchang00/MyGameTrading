package com.example.administrator.gametrading.Handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.MyCenterPage.MyPostActivity;
import com.example.administrator.gametrading.util.DateUtil;
import com.example.administrator.gametrading.util.SessionUtil;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class PostHandler extends Handler{
    private Context context;
    private ArrayList arrayList;
    private RecyclerView.Adapter arrayAdapter;

    public PostHandler(Context context){
        this.context  = context;
    }

    public PostHandler(Context context,ArrayList arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    public PostHandler(Context context, ArrayList arrayList,RecyclerView.Adapter arrayAdapter){
        this.context = context;
        this.arrayList = arrayList;
        this.arrayAdapter = arrayAdapter;
    }

    public void handlemessage(Message msg){
        super.handleMessage(msg);
        int i = msg.arg1;
        switch (i)
        {
            //showRepeatPost
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
                        Forum forum = new Forum();
                        //forum.setId(jsonObject.getInt("id"));
                        forum.setAuthor(jsonObject.getString("author"));
                        //forum.setRepeat(jsonObject.getString("repeat"));
                        forum.setTime(jsonObject.getString("time"));
                        forum.setTitle(jsonObject.getString("title"));
                        //forum.setEssence(jsonObject.getBoolean("essence"));
                        forum.setAwesome(jsonObject.getInt("awesome"));
                        forum.setHead(jsonObject.getString("head"));
                        forum.setImage(jsonObject.getString("image"));
                        forum.setContent(jsonObject.getString("content"));
                        forum.setFloor(jsonObject.getString("floor"));
                        forum.setPostid(jsonObject.getInt("postid"));
                        forum.setActive(jsonObject.getInt("active"));
                        arrayList.add(forum);
                        Collections.sort(arrayList, new Comparator<Forum>() {
                            @Override
                            public int compare(Forum o1, Forum o2) {
                                Date date1 = DateUtil.stringToDate(o1.getTime());
                                Date date2 = DateUtil.stringToDate(o2.getTime());
                                if (date2.before(date1)){
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
                //allPost
            case 2:
                Log.e("message",msg.obj.toString());
                try{
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Forum forum = new Forum();
                        forum.setId(jsonObject.getInt("id"));
                        forum.setAuthor(jsonObject.getString("author"));
                        forum.setRepeat(jsonObject.getString("repeat"));
                        forum.setTime(jsonObject.getString("time"));
                        forum.setTitle(jsonObject.getString("title"));
                        forum.setEssence(jsonObject.getBoolean("essence"));
                        forum.setAwesome(jsonObject.getInt("awesome"));
                        forum.setHead(jsonObject.getString("head"));
                        forum.setImage(jsonObject.getString("image"));
                        forum.setContent(jsonObject.getString("content"));
                        forum.setFloor(jsonObject.getString("floor"));
                        forum.setPostid(jsonObject.getInt("postid"));
                        arrayList.add(forum);
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
                //EssencePost
            case 3:
                Log.e("message",msg.obj.toString());
                try{
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Forum forum = new Forum();
                        forum.setId(jsonObject.getInt("id"));
                        forum.setAuthor(jsonObject.getString("author"));
                        forum.setRepeat(jsonObject.getString("repeat"));
                        forum.setTime(jsonObject.getString("time"));
                        forum.setTitle(jsonObject.getString("title"));
                        forum.setEssence(jsonObject.getBoolean("essence"));
                        forum.setAwesome(jsonObject.getInt("awesome"));
                        forum.setHead(jsonObject.getString("head"));
                        forum.setImage(jsonObject.getString("image"));
                        forum.setContent(jsonObject.getString("content"));
                        forum.setFloor(jsonObject.getString("floor"));
                        forum.setPostid(jsonObject.getInt("postid"));
                        arrayList.add(forum);
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
                //SendPost
            case 4:
                String sendPost = msg.obj.toString();
                try{
                    JSONObject jsonObject = new JSONObject(sendPost);
                    int resCode = jsonObject.getInt("resCode");
                    if (resCode==1){
                        Toast.makeText(context,"发帖成功",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, MyPostActivity.class);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context,"发帖失败",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 5:
                Log.e("message",msg.obj.toString());
                try{
                    //封装成jsonarray对象
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    String cookie=new SessionUtil(context).GetSession();
                    Log.e("cookie",cookie+"");
                    //循环遍历我们的jsonobject
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Forum forum = new Forum();
                        forum.setId(jsonObject.getInt("id"));
                        forum.setAuthor(jsonObject.getString("author"));
                        forum.setRepeat(jsonObject.getString("repeat"));
                        forum.setTime(jsonObject.getString("time"));
                        forum.setTitle(jsonObject.getString("title"));
                        forum.setEssence(jsonObject.getBoolean("essence"));
                        forum.setAwesome(jsonObject.getInt("awesome"));
                        forum.setHead(jsonObject.getString("head"));
                        forum.setImage(jsonObject.getString("image"));
                        forum.setContent(jsonObject.getString("content"));
                        forum.setFloor(jsonObject.getString("floor"));
                        String user = jsonObject.getString("user");
                        JSONArray b = new JSONArray(user);
                        JSONObject c = b.getJSONObject(0);
                        forum.setActive(c.getInt("active"));
                        arrayList.add(forum);
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 6:
                String repeatPost = msg.obj.toString();
                try{
                    JSONObject jsonObject = new JSONObject(repeatPost);
                    int resCode = jsonObject.getInt("resCode");
                    if (resCode==1){
                        Toast.makeText(context,"回复成功",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(context,"发帖失败",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 7:
                Log.e("message",msg.obj.toString());
                try{
                    //封装成jsonarray对象
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    String cookie=new SessionUtil(context).GetSession();
                    Log.e("cookie",cookie+"");
                    //循环遍历我们的jsonobject
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Forum forum = new Forum();
                        forum.setId(jsonObject.getInt("id"));
                        forum.setAuthor(jsonObject.getString("author"));
                        forum.setRepeat(jsonObject.getString("repeat"));
                        forum.setTime(jsonObject.getString("time"));
                        forum.setTitle(jsonObject.getString("title"));
                        forum.setEssence(jsonObject.getBoolean("essence"));
                        forum.setAwesome(jsonObject.getInt("awesome"));
                        forum.setHead(jsonObject.getString("head"));
                        forum.setImage(jsonObject.getString("image"));
                        forum.setContent(jsonObject.getString("content"));
                        forum.setFloor(jsonObject.getString("floor"));
                        forum.setPostid(jsonObject.getInt("postid"));
                        Log.e("forum12",forum.toString());
                        arrayList.add(forum);
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 8:
                Log.e("message",msg.obj.toString());
                try{
                    //封装成jsonarray对象
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    String cookie=new SessionUtil(context).GetSession();
                    Log.e("cookie",cookie+"");
                    //循环遍历我们的jsonobject
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Forum forum = new Forum();
                        forum.setId(jsonObject.getInt("id"));
                        forum.setAuthor(jsonObject.getString("author"));
                        forum.setRepeat(jsonObject.getString("repeat"));
                        forum.setTime(jsonObject.getString("time"));
                        forum.setTitle(jsonObject.getString("title"));
                        forum.setEssence(jsonObject.getBoolean("essence"));
                        forum.setAwesome(jsonObject.getInt("awesome"));
                        forum.setHead(jsonObject.getString("head"));
                        forum.setImage(jsonObject.getString("image"));
                        forum.setContent(jsonObject.getString("content"));
                        forum.setFloor(jsonObject.getString("floor"));
                        forum.setPostid(jsonObject.getInt("postid"));
                        arrayList.add(forum);
                    }
                    if(arrayAdapter != null) {
                        arrayAdapter.notifyDataSetChanged();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 9:
                Log.e("message",msg.obj.toString());
                try{
                    //封装成jsonarray对象
                    JSONArray jsonArray = new JSONArray(msg.obj.toString().trim());
                    String cookie=new SessionUtil(context).GetSession();
                    Log.e("cookie",cookie+"");
                    //循环遍历我们的jsonobject
                    for(int index=0; index< jsonArray.length();index++){
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Forum forum = new Forum();
                        //forum.setId(jsonObject.getInt("id"));
                        forum.setAuthor(jsonObject.getString("author"));
                        forum.setRepeat(jsonObject.getString("repeat"));
                        forum.setTime(jsonObject.getString("time"));
                        forum.setTitle(jsonObject.getString("title"));
                        forum.setEssence(jsonObject.getBoolean("essence"));
                        forum.setAwesome(jsonObject.getInt("awesome"));
                        forum.setHead(jsonObject.getString("head"));
                        forum.setImage(jsonObject.getString("image"));
                        forum.setContent(jsonObject.getString("content"));
                        forum.setFloor(jsonObject.getString("floor"));
                        forum.setPostid(jsonObject.getInt("postid"));
                        //forum.setActive(jsonObject.getInt("active"));
                        arrayList.add(forum);
                        Collections.sort(arrayList, new Comparator<Forum>() {
                            @Override
                            public int compare(Forum o1, Forum o2) {
                                Date date1 = DateUtil.stringToDate(o1.getTime());
                                Date date2 = DateUtil.stringToDate(o2.getTime());
                                if (date2.before(date1)){
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
        }
    }
}
