package com.example.administrator.gametrading.MyCenterPage;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.User;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.RegisterService;
import com.example.administrator.gametrading.util.MD5Util;
import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity {

    private ImageView back;
    private Button registerBtn;
    private Button registerMsgBtn;
    private EditText userName;
    private EditText pwdText;
    private EditText confirmPwdText;
    private EditText register_msg;
    private MD5Util md5Util= new MD5Util();
    private RegisterService registerService;
    //短信验证校正码
    String APPKEY = "101732155b605";
    String APPSECRETE = "69d1850f4b74100266ab576b64e6cb16";
    int i = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        setListeners();
    }



     void initComponents() {
        back = findViewById(R.id.register_back);
        registerBtn = findViewById(R.id.register_btn);
        userName = findViewById(R.id.register_user);
        pwdText = findViewById(R.id.register_password);
        confirmPwdText = findViewById(R.id.register_pwd_confirm);
        registerMsgBtn = findViewById(R.id.register_msg_btn);
        register_msg = findViewById(R.id.register_msg);


         // 启动短信验证sdk
         MobSDK.init(RegisterActivity.this, APPKEY, APPSECRETE);
         EventHandler eventHandler = new EventHandler() {
             @Override
             public void afterEvent(int event, int result, Object data) {
                 Message msg = new Message();
                 msg.arg1 = event;
                 msg.arg2 = result;
                 msg.obj = data;
                 handler.sendMessage(msg);
             }
         };
         //注册回调监听接口
         SMSSDK.registerEventHandler(eventHandler);

    }

     void setListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });// 设置返回键监听

         registerMsgBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 msgVerification();
             }
         });
         registerBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 register();
             }
         });
    }

    private void msgVerification() {
        String phoneNums = userName.getText().toString();
        if (!judgePhoneNums(phoneNums)) {
            return;
        } // 2. 通过sdk发送短信验证
        SMSSDK.getVerificationCode("86", phoneNums);
        registerMsgBtn.setClickable(false);
        registerMsgBtn.setText("重新发送(" + i + ")");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    handler.sendEmptyMessage(-9);
                    if (i <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(-8);
            }
        }).start();
    }

    private void register() {
        String phoneNums = userName.getText().toString();
        String pass  = pwdText.getText().toString();
        String confirm = confirmPwdText.getText().toString();
        if (pass.equals(confirm)) {

            SMSSDK.submitVerificationCode("86", phoneNums, register_msg.getText().toString());


        } else {
            Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                registerMsgBtn.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                registerMsgBtn.setText("获取验证码");
                registerMsgBtn.setClickable(true);
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + data);
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        Toast.makeText(getApplicationContext(), "正在获取验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                        String pwd = pwdText.getText().toString();
                        String md5pwd = md5Util.encrypt(pwd);
                        User user = new User();
                        registerService = new RegisterService();
                        user.setName(userName.getText().toString());
                        user.setPass(md5pwd);
                        registerService.register(user,RegisterActivity.this);

                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                       /* UserBean userBean = new UserBean();
                        registerService = new LoginService();
                        userBean.setUsername(reg_phone.getText().toString().trim());
                        userBean.setUserpwd(msg_pass.getText().toString().trim());
                        registerService.register(userBean, MsgRegisterActivity.this);*/
                    } else if (result == SMSSDK.RESULT_ERROR) {
                        Toast.makeText(getApplicationContext(), "验证码错误", Toast.LENGTH_SHORT).show();
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };
    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }


    @Override
    protected void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }
}
