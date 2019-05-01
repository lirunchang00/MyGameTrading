package com.example.administrator.gametrading.ShoppingCarPage;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.OrderService;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.alipay.AuthResult;
import com.example.administrator.gametrading.alipay.OrderInfoUtil2_0;
import com.example.administrator.gametrading.alipay.PayResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {
    private TextView order_name,order_operating,order_type,order_server,order_content,order_method,order_special,order_one_price;
    private EditText order_email,order_phone,order_qq;
    private Button enter;
    private Intent intent;
    private ImageView order_back;
    private Commodity commodity ;
    private String phone,email,qq;
    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016092600597353";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "2016092600597353";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "2016092600597353";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCtZvOxX/xwG2vIVBkoRl3MPgHe/k7yilVAZp0X/QLKxD8AX+INxWkUID7L0S6qnXykchGqjpneNnnDe/JD8SYws5LxiGV7HC7g9/GfcYXQwUk4UeQR3NekF4NgBFK2vPd8vqPXSdu8RxKLHnNwmx+cpBDHofQjTTkW/P/ym+FZvtkxXSY1sg3HV2so76QGLc4dKEXsQM5UDUkMHo/LgelWyiKsRph6bl87XpFrE967YzbHwWwGNjeIZqFRGCfelyRBJlj7EKy78nuNbd6ePcT8MUMe1Zz4n03y+bPHb3q/g/7o1RFxOugMBtra2OP9Jf11Zgfn7vIdo+ZVuRNZ5W0VAgMBAAECggEAdVl3ElMjQ7tK9gfz/o2gbNiJ65+vTfTQPQU0y0cu3IpzGI3LLgarVjN8S/ME4fIyqBeKXSKkfv3e6ay4B8LrVYoxp8Pm6anpD9a8bmslZGFqa/L/phcO2L4Q6qoCUCkSVBmwR8J0Mrbmlb/F4C8a9acStAemcT2RpT0S+d0EZPbkBYNNevlbXi3AVSeOqQ8zOr/mYRq3a9LiOBu/4/ysH7YPP6pjL7IVXF87nwCfIbVmUkG9v0ZGh+zJ+ZNPSgaGeuUFzr1+q7Pj7KAa04HtoSftLZSk09gxCMCWzDhusJyBS9/ilYY4qsfcI+6Q7RA3XoB6v0sKCtnLMdQBJkfgAQKBgQDvYPoKBi9MkT2RT4FjybhYXXxQkMdDsbXB8W9243GDht8rXrmc3smkyh8sipdnYFlU0/SG9dti/ji4JlsPk7lkzkToAH1UjMYibHAzQfy545Dq+7FP+8GHvuAmdGMBMPtcLh99bixf34fUR4w61se9muDWKB9AePD0QsW917H0tQKBgQC5cTjjToBoyXE5gmOzZUpx/0jECRcjKXxjNUsIp2/7Qjy0IUIpQ5n8e6gq49UikB1qOEdyLAmG4c1kU/F/7UesOzfg+u0Ihg/QQpV0BUbyg0LNHZcUmp7yV6VgLsJAzOYtZNClb21jUc2rcrrPChoRaGuPNf0Tx5YBJxaAwyoy4QJ/VvXj38BfbnFU7HT1jEq7rV5IyvvNZ8onPU14MLhvhFh6ukavJ03H8xV6PbQbTHK0ARz+uAiYql2wPTWx3vbLYtOMNLv4y75YPCkomXZfWShSfQryXxwWMjolM9MUJFqm9dXLmsVrWWja70MOrlFYPokFJMdukv1ci1+2dkDlZQKBgQCyHF9JY4LOIn0PgX8FNUp1h356W0jPyu7f0rYIz9kseQgY8sC7JWTnLlXtK/rwQX8fIILu1AEqfgJqVH3mIx7t+8rF2fyEbU8O9oTZdKAaGZy1qbAMaB42IZyDbyXAW4zRGL2VyBpGhLGH9KAcb1VuVt7IJFC5CL+4x7RDZDBJwQKBgET7oCKEaok6+VYIfLbJFUwyEwhTbl0PrePmrX6BydiXnWzs3LyCvmAuEQ5UIvZ7rBKDXriMN7FfDD/YWJcG5I/iGGb105+kksodMM4agzyVdbcCOJtw+rSdmaPv+Mk+ctM1FISggsKPz6CHDT7KFEkCsGp1C70hLi3B11Z2mP/c";
    public static final String RSA_PRIVATE = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCtZvOxX/xwG2vIVBkoRl3MPgHe/k7yilVAZp0X/QLKxD8AX+INxWkUID7L0S6qnXykchGqjpneNnnDe/JD8SYws5LxiGV7HC7g9/GfcYXQwUk4UeQR3NekF4NgBFK2vPd8vqPXSdu8RxKLHnNwmx+cpBDHofQjTTkW/P/ym+FZvtkxXSY1sg3HV2so76QGLc4dKEXsQM5UDUkMHo/LgelWyiKsRph6bl87XpFrE967YzbHwWwGNjeIZqFRGCfelyRBJlj7EKy78nuNbd6ePcT8MUMe1Zz4n03y+bPHb3q/g/7o1RFxOugMBtra2OP9Jf11Zgfn7vIdo+ZVuRNZ5W0VAgMBAAECggEAdVl3ElMjQ7tK9gfz/o2gbNiJ65+vTfTQPQU0y0cu3IpzGI3LLgarVjN8S/ME4fIyqBeKXSKkfv3e6ay4B8LrVYoxp8Pm6anpD9a8bmslZGFqa/L/phcO2L4Q6qoCUCkSVBmwR8J0Mrbmlb/F4C8a9acStAemcT2RpT0S+d0EZPbkBYNNevlbXi3AVSeOqQ8zOr/mYRq3a9LiOBu/4/ysH7YPP6pjL7IVXF87nwCfIbVmUkG9v0ZGh+zJ+ZNPSgaGeuUFzr1+q7Pj7KAa04HtoSftLZSk09gxCMCWzDhusJyBS9/ilYY4qsfcI+6Q7RA3XoB6v0sKCtnLMdQBJkfgAQKBgQDvYPoKBi9MkT2RT4FjybhYXXxQkMdDsbXB8W9243GDht8rXrmc3smkyh8sipdnYFlU0/SG9dti/ji4JlsPk7lkzkToAH1UjMYibHAzQfy545Dq+7FP+8GHvuAmdGMBMPtcLh99bixf34fUR4w61se9muDWKB9AePD0QsW917H0tQKBgQC5cTjjToBoyXE5gmOzZUpx/0jECRcjKXxjNUsIp2/7Qjy0IUIpQ5n8e6gq49UikB1qOEdyLAmG4c1kU/F/7UesOzfg+u0Ihg/QQpV0BUbyg0LNHZcUmp7yV6VgLsJAzOYtZNClb21jUc2rcrrPChoRaGuPNf0Tx5YBJxaAwyoy4QJ/VvXj38BfbnFU7HT1jEq7rV5IyvvNZ8onPU14MLhvhFh6ukavJ03H8xV6PbQbTHK0ARz+uAiYql2wPTWx3vbLYtOMNLv4y75YPCkomXZfWShSfQryXxwWMjolM9MUJFqm9dXLmsVrWWja70MOrlFYPokFJMdukv1ci1+2dkDlZQKBgQCyHF9JY4LOIn0PgX8FNUp1h356W0jPyu7f0rYIz9kseQgY8sC7JWTnLlXtK/rwQX8fIILu1AEqfgJqVH3mIx7t+8rF2fyEbU8O9oTZdKAaGZy1qbAMaB42IZyDbyXAW4zRGL2VyBpGhLGH9KAcb1VuVt7IJFC5CL+4x7RDZDBJwQKBgET7oCKEaok6+VYIfLbJFUwyEwhTbl0PrePmrX6BydiXnWzs3LyCvmAuEQ5UIvZ7rBKDXriMN7FfDD/YWJcG5I/iGGb105+kksodMM4agzyVdbcCOJtw+rSdmaPv+Mk+ctM1FISggsKPz6CHDT7KFEkCsGp1C70hLi3B11Z2mP/c";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initData();
        order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        order_back = (ImageView) findViewById(R.id.order_back);
        order_name = (TextView)findViewById(R.id.order_name);
        order_operating = (TextView)findViewById(R.id.order_operating);
        order_type = (TextView)findViewById(R.id.order_type);
        order_server = (TextView)findViewById(R.id.order_server);
        order_content = (TextView)findViewById(R.id.order_content);
        order_method = (TextView)findViewById(R.id.order_method);
        order_special = (TextView)findViewById(R.id.order_special);
        order_one_price = (TextView)findViewById(R.id.order_one_price);
        order_email = (EditText)findViewById(R.id.order_email);
        order_phone = (EditText)findViewById(R.id.order_phone);
        order_qq = (EditText)findViewById(R.id.order_qq);
        enter =(Button)findViewById(R.id.order_enter);
    }

    private void initData() {
        intent=getIntent();
        commodity = (Commodity)intent.getSerializableExtra("makeOrder");

        order_name.setText(commodity.getComName());
        order_operating.setText(commodity.getOperating());
        order_type.setText(commodity.getType());
        order_server.setText(commodity.getComServer());
        order_content.setText(commodity.getComContent());
        order_method.setText(commodity.getComMethod());
        order_special.setText(commodity.getComSpecial());
        order_one_price.setText(commodity.getComPrice());
        //order_email.setText(commodity.getComName());
        // order_phone.setText(commodity.getComName());

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    next();
                    alipay();
            }
        });
    }

    private void alipay() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void next() {
        phone = order_phone.getText().toString();
        email = order_email.getText().toString();
        qq = order_qq.getText().toString();


        if (verification()){
            commodity.setClientPhone(phone);
            commodity.setEmail(email);
            commodity.setQq(qq);
            new OrderService().makeOrder(commodity,OrderActivity.this);
            Log.e("commodity",commodity.toString());
            // new OrderService().comDetail(OrderActivity.this,commodity);

        }
    }

    private boolean verification() {
        String emailregex = "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$";
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        boolean p = phone.matches(telRegex);
        boolean e = email.matches(emailregex);
        if (phone.equals("")) {
            Toast.makeText(OrderActivity.this, "联系电话不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } if (email.equals("")) {
            Toast.makeText(OrderActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!p){
            Toast.makeText(OrderActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!e){
            Toast.makeText(OrderActivity.this, "请输入正确的邮箱地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(OrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        try{
                            Thread.sleep(1000);
                            Intent intent = new Intent(OrderActivity.this,OrderDetailActivity.class);
                            intent.putExtra("commodity",commodity);
                            startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(OrderActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(OrderActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
}
