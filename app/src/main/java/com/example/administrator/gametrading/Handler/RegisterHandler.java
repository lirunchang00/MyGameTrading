package com.example.administrator.gametrading.Handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.administrator.gametrading.MyCenterPage.LoginActivity;

import org.json.JSONObject;

public class RegisterHandler extends Handler{
    private Context context;

    public RegisterHandler(Context context){
        this.context  = context;
    }
    public  void  handlerMessage(Message msg){
        super.handleMessage(msg);
        int i = msg.arg1;
        switch (i){
            case 1:
                String register = msg.obj.toString();
                try{
                    JSONObject jsonObject = new JSONObject(register);
                    int resCode = jsonObject.getInt("resCode");
                    if (resCode==1){
                        Toast.makeText(context,"注册成功",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context,"注册失败",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }

    }
}
