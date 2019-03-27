package com.example.administrator.gametrading.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.NetworkResponse;

import java.util.HashMap;
import java.util.Map;

public class SessionUtil {
    private static final String CooKie = "cookie_";
    private String mHeader;
    private Map<String, String> getHeader=new HashMap<String, String>();
    private String rawCookies;
    private String SesstionID;
    private Context context;

    public SessionUtil(Context context){
        this.context = context;
    }

    public void getSession(NetworkResponse response){
        //获取头部信息
        mHeader = response.headers.toString();
        //获取cookie头部信息
        getHeader = response.headers;
        rawCookies = getHeader.get("Set-Cookie");
        //;分隔获取sessionid
        SesstionID = rawCookies.substring(0,rawCookies.indexOf(";"));
        //使用SharedPreferences本地存储
        Log.e("sessionId",SesstionID);
        SaveSession(SesstionID);
    }

    //本地保存session
    public void SaveSession(String sessionid){
        SharedPreferences preferences =  context.
                getSharedPreferences("ConnectViaSession.class", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CooKie,sessionid);
        editor.commit();
    }

    //从本地获取session
    public String GetSession(){
        SharedPreferences preferences =  context
                .getSharedPreferences("ConnectViaSession.class", Context.MODE_PRIVATE);
        return preferences.getString(CooKie,"");
    }

    public void Clear() {
        SharedPreferences preferences = context.getSharedPreferences("ConnectViaSession.class", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
