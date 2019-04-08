package com.example.administrator.gametrading.Handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.MyCenterPage.LoginActivity;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class OrderHandler extends Handler{
    private Context context;
    public OrderHandler(Context context){
        this.context = context;
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
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context,"提交失败",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
        }
    }
}
