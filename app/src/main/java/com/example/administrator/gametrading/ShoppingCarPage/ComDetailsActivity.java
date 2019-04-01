package com.example.administrator.gametrading.ShoppingCarPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;

public class ComDetailsActivity extends AppCompatActivity {
    private ImageView com_detail_image;
    private TextView com_detail_name,com_detail_content,com_detail_num,com_detail_price,com_detail_special;
    private TextView com_detail_server,com_detail_operating,com_detail_method,com_detail_solder,com_detail_active,com_detail_type;
    private Commodity commodity;
    //private String id;
    //private ArrayList<Commodity> arrayList= new ArrayList<>();
    private Intent intent;
    //private String TAG = "run";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_details);
        intent = getIntent();
        initview();
        initData();
    }

    private void initData() {
        commodity = (Commodity) intent.getSerializableExtra("commodity");
        //new ShopService().showDetail(ComDetailsActivity.this,id,arrayList);




                Log.e("commodity",commodity.getComName());
                com_detail_name.setText(commodity.getComName());
                com_detail_content.setText(commodity.getComContent());
                com_detail_num.setText(commodity.getComNum());
                com_detail_price.setText(commodity.getComPrice());
                com_detail_special.setText(commodity.getComSpecial());
                com_detail_server.setText(commodity.getComServer());
                com_detail_operating.setText(commodity.getOperating());
                com_detail_method.setText(commodity.getComMethod());
                com_detail_solder.setText(commodity.getSolder());
                com_detail_active.setText(commodity.getActive()+"");
                com_detail_type.setText(commodity.getType());
                String pic = Tools.headUrl+"a/"+commodity.getComImage();
                Glide.with(ComDetailsActivity.this).load(pic).placeholder(R.drawable.main_my).into(com_detail_image);



     }


    private void initview() {
        com_detail_name = (TextView)findViewById(R.id.com_details_name);
        com_detail_content = (TextView)findViewById(R.id.com_details_content);
        com_detail_num = (TextView)findViewById(R.id.com_details_num);
        com_detail_price = (TextView)findViewById(R.id.com_details_price);
        com_detail_special = (TextView)findViewById(R.id.com_details_special);
        com_detail_server = (TextView)findViewById(R.id.com_details_server);
        com_detail_operating = (TextView)findViewById(R.id.com_details_operating);
        com_detail_method = (TextView)findViewById(R.id.com_details_method);
        com_detail_solder = (TextView)findViewById(R.id.com_details_solder);
        com_detail_active = (TextView)findViewById(R.id.com_details_active);
        com_detail_type = (TextView)findViewById(R.id.com_details_type);
        com_detail_image = (ImageView)findViewById(R.id.com_details_image);


    }
}
