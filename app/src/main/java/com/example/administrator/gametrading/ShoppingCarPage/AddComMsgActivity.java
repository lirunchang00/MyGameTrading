package com.example.administrator.gametrading.ShoppingCarPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.MyCenterPage.LoginActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.OrderService;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;
import com.example.administrator.gametrading.util.UploadUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddComMsgActivity extends AppCompatActivity {
    private EditText add_com_num,add_com_pass,add_com_game_name,add_com_register_phone,add_com_phone,add_com_qq;
    private Button add_com_msg_back,add_com_msg_enter;
    private Intent intent;
    private Commodity commodity;
    private String picPath = null;
    private String Cookie = "";
    private OrderService orderService;
    private String Account,Password,Character,SecretPhone,Phone,Qq;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_com_msg);
        initView();



        add_com_msg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_com_msg_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter();
            }
        });
    }

    private void enter() {
        intent = getIntent();
        commodity = (Commodity)intent.getSerializableExtra("commodity");
        Account = add_com_num.getText().toString();
        Password = add_com_pass.getText().toString();
        SecretPhone = add_com_register_phone.getText().toString();
        Phone = add_com_phone.getText().toString();
        Qq = add_com_qq.getText().toString();
        Character = add_com_game_name.getText().toString();

        picPath = commodity.getComImage();
        String image = picPath.substring(22);
        //String cookie = Cookie.substring(5);
        Log.e("image",image);
        Cookie = new SessionUtil(context).GetSession();
        String cookie = "";
        if (Cookie!="") {
             cookie = Cookie.substring(5);
        }
        if (picPath!=null){
            touploadFile(AddComMsgActivity.this);
        }else {
            Log.e("error","error");
        }

        Date date = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date.getTime());

        Commodity commodity1 = new Commodity();
        commodity1.setComName(commodity.getComName());
        commodity1.setType(commodity.getType());
        commodity1.setOperating(commodity.getOperating());
        commodity1.setComSpecial(commodity.getComSpecial());
        commodity1.setSolder(cookie);
        commodity1.setComContent(commodity.getComContent());
        commodity1.setComMethod(commodity.getComMethod());
        commodity1.setComServer(commodity.getComServer());
        commodity1.setComPrice(commodity.getComPrice());

        commodity1.setComImage(image);

        commodity1.setDate(time);
        commodity1.setAccount(Account);
        commodity1.setPassword(Password);
        commodity1.setCharacter(Character);
        commodity1.setSecretPhone(SecretPhone);
        commodity1.setPhone(Phone);
        commodity1.setQq(Qq);

        Log.e("toString",commodity1.toString());

        if (init()) {
            new OrderService().addCom(commodity1, AddComMsgActivity.this);
        }
    }

    private void initView() {
        add_com_msg_back = (Button)findViewById(R.id.add_com_msg_back);
        add_com_num = (EditText)findViewById(R.id.add_com_num);
        add_com_pass =(EditText)findViewById(R.id.add_com_pass);
        add_com_game_name =(EditText)findViewById(R.id.add_com_game_name);
        add_com_register_phone =(EditText)findViewById(R.id.add_com_register_phone);
        add_com_phone =(EditText)findViewById(R.id.add_com_phone);
        add_com_qq =(EditText)findViewById(R.id.add_com_qq);
        add_com_msg_enter = (Button)findViewById(R.id.add_com_msg_enter);
    }

    private boolean init() {
        if (Account.equals("")) {
            Toast.makeText(AddComMsgActivity.this, "游戏账号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Password.equals("")) {

            Toast.makeText(AddComMsgActivity.this, "游戏密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Character.equals("")) {
            Toast.makeText(AddComMsgActivity.this, "角色名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (SecretPhone.equals("")) {

            Toast.makeText(AddComMsgActivity.this, "密保手机不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Phone.equals("")) {
            Toast.makeText(AddComMsgActivity.this, "手机不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Qq.equals("")) {

            Toast.makeText(AddComMsgActivity.this, "联系QQ不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void touploadFile(final Context context) {
        String fileKey = "pic";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        Map<String, String> params = new HashMap<String, String>();
        Log.e("filepath",picPath+"");
        params.put("orderId", "zhansgan");
        uploadUtil.uploadFile( picPath,fileKey, Tools.imageurl,params,context);
    }
}
