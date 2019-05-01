package com.example.administrator.gametrading.Handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.administrator.gametrading.MainActivity;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginHandler extends Handler{
    private Context context;
    private ArrayList arrayList;
    private String gethead;
    private ArrayAdapter arrayAdapter;

    public LoginHandler(Context context){
        this.context  = context;
    }
    public  void  handlerMessage(Message msg){
        super.handleMessage(msg);
        int i = msg.arg1;
        switch (i){
            case 1:
                String login = msg.obj.toString();
                //将字符串组装成json对象
                try {
                    JSONObject jsonObject = new JSONObject(login);
                    int resCode = jsonObject.getInt("resCode");
                    if (resCode==1){
                        Toast.makeText(context,"登陆成功",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context,"登录失败,账号不存在或密码错误",Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
