package com.example.administrator.gametrading.MyCenterPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.User;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.LoginService;
import com.example.administrator.gametrading.util.MD5Util;
import com.example.administrator.gametrading.util.SharedPreferencesUtil;
import com.example.administrator.gametrading.util.UserManager;

import org.litepal.LitePal;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button loginBtn;
    private Button registerBtn;
    private EditText accountText;
    private EditText passwordText;
    private CheckBox isRememberPwd;
    private CheckBox isAutoLogin;
    private MD5Util md5Util= new MD5Util();

    private LoginService loginService;
    private String name;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        setListeners();

        //自动填充账号密码
        SharedPreferencesUtil spu = new SharedPreferencesUtil(this);
        Boolean isRemember =(Boolean)spu.getParam("isRememberPwd",false);
        Boolean isAutoLogin1 =(Boolean)spu.getParam("isAutoLogin",false);
        // SharedPreference获取用户账号密码，存在则填充
        String name = (String) spu.getParam("name","");
        String pwd = (String)spu.getParam("pwd","");
        if (!name.equals("")&&!pwd.equals("")){
            if (isRemember){
                accountText.setText(name);
                passwordText.setText(pwd);
                isRememberPwd.setChecked(true);
            }
            if (isAutoLogin1){
                isAutoLogin.setChecked(true);
                Login();
            }
        }
    }

    private void Login() {
        name = accountText.getText().toString();
        password = passwordText.getText().toString();

        String md5pwd = md5Util.encrypt(password);
        progressBar.setVisibility(View.VISIBLE);// 显示进度条
        if (validate()){
            User user  = new User();
            loginService = new LoginService();
            user.setName(name);
            user.setPass(md5pwd);
            loginService.login(user,LoginActivity.this);
            OptionHandle(name,password);// 处理自动登录及记住密码
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
    private boolean validate() {
        if (name.equals("")) {
            Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.equals("")) {

            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //布局
    void initView() {
        loginBtn = findViewById(R.id.login);
        registerBtn = findViewById(R.id.register);
        accountText = findViewById(R.id.account);
        passwordText = findViewById(R.id.password);
        isRememberPwd = findViewById(R.id.login_remember);
        isAutoLogin = findViewById(R.id.login_auto);
        progressBar = findViewById(R.id.progressbar);

        LitePal.getDatabase();
        UserManager.clear();
    }

    void setListeners() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    void OptionHandle(String name,String pwd){
        SharedPreferences.Editor editor = getSharedPreferences("UserData",MODE_PRIVATE).edit();
        SharedPreferencesUtil spu = new SharedPreferencesUtil(this);

        if(isRememberPwd.isChecked()){
            editor.putBoolean("isRememberPwd",true);
            // 保存账号密码
            spu.setParam("name",name);
            spu.setParam("pwd",pwd);
        }else{
            editor.putBoolean("isRememberPwd",false);
        }
        if(isAutoLogin.isChecked()){
            editor.putBoolean("isAutoLogin",true);
        }else{
            editor.putBoolean("isAutoLogin",false);
        }
        editor.apply();
    }
}
