package com.example.administrator.gametrading.ShoppingCarPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.gametrading.R;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView order_id,order_detail_name,order_detail_num,order_detail_one_price,order_detail_pay;
    private TextView order_detail_pay_status,order_detail_operating,order_detail_type,order_detail_server;
    private TextView order_detail_content,order_detail_method,order_detail_special,order_detail_email,order_detail_phone,order_detail_qq;
    private ImageView com_detail_image,com_details_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initView();

    }

    private void initView() {
        com_details_back = (ImageView) findViewById(R.id.com_details_back);
        com_detail_image = (ImageView) findViewById(R.id.com_detail_image);
        order_id = (TextView) findViewById(R.id.order_id);
        order_detail_name = (TextView) findViewById(R.id.order_detail_name);
        order_detail_num = (TextView) findViewById(R.id.order_detail_num);
        order_detail_one_price = (TextView) findViewById(R.id.order_detail_one_price);
        order_detail_pay = (TextView) findViewById(R.id.order_detail_pay);
        order_detail_pay_status = (TextView) findViewById(R.id.order_detail_pay_status);
        order_detail_operating = (TextView) findViewById(R.id.order_detail_operating);
        order_detail_type = (TextView) findViewById(R.id.order_detail_type);
        order_detail_server = (TextView) findViewById(R.id.order_detail_server);
        order_detail_content = (TextView) findViewById(R.id.order_detail_content);
        order_detail_method = (TextView) findViewById(R.id.order_detail_method);
        order_detail_special = (TextView) findViewById(R.id.order_detail_special);
        order_detail_email = (TextView) findViewById(R.id.order_detail_email);
        order_detail_phone = (TextView) findViewById(R.id.order_detail_phone);
        order_detail_qq = (TextView) findViewById(R.id.order_detail_qq);
    }
}
