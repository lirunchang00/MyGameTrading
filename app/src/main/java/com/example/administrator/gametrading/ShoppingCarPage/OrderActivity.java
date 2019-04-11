package com.example.administrator.gametrading.ShoppingCarPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.OrderService;
import com.example.administrator.gametrading.Tools;

public class OrderActivity extends AppCompatActivity {
    private TextView order_name,order_operating,order_type,order_server,order_content,order_method,order_special,order_one_price;
    private EditText order_email,order_phone;
    private Button enter;
    private Intent intent;
    private Commodity commodity;
    private String phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initData();
    }

    private void initView() {
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
        enter =(Button)findViewById(R.id.order_enter);
    }

    private void initData() {
        commodity = (Commodity)intent.getSerializableExtra("makeOrder");

        order_name.setText(commodity.getComName());
        order_operating.setText(commodity.getComName());
        order_type.setText(commodity.getComName());
        order_server.setText(commodity.getComName());
        order_content.setText(commodity.getComName());
        order_method.setText(commodity.getComName());
        order_special.setText(commodity.getComName());
        order_one_price.setText(commodity.getComName());
        order_email.setText(commodity.getComName());
        order_phone.setText(commodity.getComName());

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    next();
            }
        });
    }

    private void next() {
        phone = order_phone.getText().toString();
        email = order_email.getText().toString();

        if (verification()){
            commodity.setPhone(phone);
            commodity.setEmail(email);
            Log.e("commodity",commodity.toString());
           // new OrderService().comDetail(OrderActivity.this,commodity);
            Intent intent = new Intent(OrderActivity.this,OrderDetailActivity.class);
            intent.putExtra("commodity",commodity);
        }
    }

    private boolean verification() {
        if (phone.equals("")) {
            Toast.makeText(OrderActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } if (enter.equals("")) {
            Toast.makeText(OrderActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
