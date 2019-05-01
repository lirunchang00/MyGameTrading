package com.example.administrator.gametrading.IndexPage;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Adapter.GirlsDetailAdapter;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.GirlsService;
import com.example.administrator.gametrading.ShoppingCarPage.ComSearchActivity;
import com.example.administrator.gametrading.Tools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private ArrayList<String> girlsList;
    private List<ImageView> imageViews;
    private int currentIndex=300;
    private boolean isCyclical = true;
    private Gson gson;
    private String TAG = "DetailActivity";
    private TextView girls_details_start,girls_details_damage,girls_details_hit,girls_details_avoid,girls_details_shooting,girls_details_hp;
    private TextView girls_details_piercing,girls_details_cirt_rate,girls_details_cirt_damage,girls_details_chain,girls_details_armor;
    private TextView girls_details_skill_name,girls_details_skill_introduction,girls_details_skill_cd,girls_details_aura_introduction;
    private TextView girls_details_dubbing,girls_details_painter,girls_details_method,girls_details_introduction,girls_details_name;
    private ImageView girls_details_skill_pic,girls_details_type,girls_details_aura,girls_details_search;
    private Intent intent;
    private Button girls_details_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        intent = getIntent();
        linearLayout=(LinearLayout)this.findViewById(R.id.girls_details_father);
        linearLayout.getBackground().setAlpha(200);
        viewPager = (ViewPager)this.findViewById(R.id.girls_details_viewPager);
        initView();
        girlsList = new ArrayList<String>();
        imageViews = new ArrayList<>();
        gson = new Gson();

        girls_details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        girls_details_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Girls girls = (Girls)intent.getSerializableExtra("girls");
                String name=girls.getName();
                Intent intent = new Intent(DetailActivity.this,ComSearchActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        initData();
    }

    private void initView() {
        girls_details_name=(TextView)this.findViewById(R.id.girls_details_name);
        girls_details_start=(TextView)this.findViewById(R.id.girls_details_start);
        girls_details_damage=(TextView)this.findViewById(R.id.girls_details_damage);
        girls_details_hit=(TextView)this.findViewById(R.id.girls_details_hit);
        girls_details_avoid=(TextView)this.findViewById(R.id.girls_details_avoid);
        girls_details_shooting=(TextView)this.findViewById(R.id.girls_details_shooting);
        girls_details_hp=(TextView)this.findViewById(R.id.girls_details_hp);
        girls_details_piercing=(TextView)this.findViewById(R.id.girls_details_piercing);
        girls_details_cirt_rate=(TextView)this.findViewById(R.id.girls_details_cirt_rate);
        girls_details_cirt_damage=(TextView)this.findViewById(R.id.girls_details_cirt_damage);
        girls_details_chain=(TextView)this.findViewById(R.id.girls_details_chain);
        girls_details_armor=(TextView)this.findViewById(R.id.girls_details_armor);
        girls_details_skill_name=(TextView)this.findViewById(R.id.girls_details_skill_name);
        girls_details_skill_introduction=(TextView)this.findViewById(R.id.girls_details_skill_introduction);
        girls_details_skill_cd=(TextView)this.findViewById(R.id.girls_details_skill_cd);
        girls_details_aura_introduction=(TextView)this.findViewById(R.id.girls_details_aura_introduction);
        girls_details_dubbing=(TextView)this.findViewById(R.id.girls_details_dubbing);
        girls_details_painter=(TextView)this.findViewById(R.id.girls_details_painter);
        girls_details_method=(TextView)this.findViewById(R.id.girls_details_method);
        girls_details_introduction=(TextView)this.findViewById(R.id.girls_details_introduction);

        girls_details_skill_pic=(ImageView)this.findViewById(R.id.girls_details_skill_pic);
        girls_details_type=(ImageView)this.findViewById(R.id.girls_details_type);
        girls_details_aura=(ImageView)this.findViewById(R.id.girls_details_aura);
        girls_details_back=(Button) this.findViewById(R.id.girls_details_back);
        girls_details_search=(ImageView)this.findViewById(R.id.girls_details_search);
    }

    private void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(DetailActivity.this);

        String url = Tools.girlsDetail;

        Girls girls = (Girls)intent.getSerializableExtra("girls");
        Log.e(TAG,girls.toString());
       // final MyHandler handler = new MyHandler();

        final String name = girls.getName();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG,response);
              /*  Message message = new Message();
                message.arg1 = 1;
                message.obj = response;
                handler.handleMessage(message);*/
                doSomeThing(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("name",name);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void doSomeThing(String s) {
        Log.e(TAG,s);
        try {
            JSONArray jsonArray = new JSONArray(s);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            Girls girls = new Girls();
            girls.setName(jsonObject.getString("name"));
            girls.setNo(jsonObject.getString("no"));
            girls.setStart(jsonObject.getInt("start"));
            girls.setType(jsonObject.getString("type"));
            girls.setTypePic(jsonObject.getString("typePic"));
            girls.setPortrait(jsonObject.getString("portrait"));
            girls.setBreakPortrait(jsonObject.getString("breakPortrait"));
            girls.setHead(jsonObject.getString("head"));
            girls.setDamage(jsonObject.getInt("damage"));
            girls.setHit(jsonObject.getInt("hit"));
            girls.setAvoid(jsonObject.getInt("avoid"));
            girls.setShooting(jsonObject.getInt("shooting"));
            girls.setHp(jsonObject.getInt("hp"));
            girls.setCirtRate(jsonObject.getInt("cirtRate"));
            girls.setCirtDamage(jsonObject.getInt("cirtDamage"));
            girls.setPiercing(jsonObject.getInt("piercing"));
            girls.setArmor(jsonObject.getInt("armor"));
            girls.setChain(jsonObject.getInt("chain"));
            girls.setMethod(jsonObject.getString("method"));
            girls.setAura(jsonObject.getString("aura"));
            girls.setAuraIntroduction(jsonObject.getString("auraIntroduction"));
            girls.setIntroduction(jsonObject.getString("introduction"));
            girls.setSkin(jsonObject.getString("skin"));
            girls.setPainter(jsonObject.getString("painter"));
            girls.setDubbing(jsonObject.getString("dubbing"));
            girls.setSkillIntroduction(jsonObject.getString("skillIntroduction"));
            girls.setSkillName(jsonObject.getString("skillName"));
            girls.setSkillPic(jsonObject.getString("skillPic"));
            girls.setSkillCD(jsonObject.getString("skillCD"));
            //Girls girls  = gson.fromJson(jsonObject.toString(),Girls.class);
            Log.e(TAG,girls.toString());
            String getPortrait = girls.getPortrait();
            String getBreakPortrait = girls.getBreakPortrait();
            String getSkin = girls.getSkin();
            girlsList.add(getPortrait);
            girlsList.add(getBreakPortrait);
            if (getSkin!="N/A"){
                girlsList.add(getSkin);
            }
            Log.e(TAG,girlsList.toString());
            for (int i = 0; i<girlsList.size(); i++){
                ImageView imageView  =new ImageView(this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                String pic = Tools.headUrl+"a/"+girlsList.get(i);
                Glide.with(this).load(pic).placeholder(R.mipmap.picc).into(imageView);
                imageViews.add(imageView);
            }
            MyAdapter adapter = new MyAdapter();
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(currentIndex);
            viewPager.addOnPageChangeListener(this);

            girls_details_name.setText(girls.getName());
            girls_details_start.setText(girls.getStart()+"");
            girls_details_damage.setText(girls.getDamage()+"");
            girls_details_hit.setText(girls.getHit()+"");
            girls_details_avoid.setText(girls.getAvoid()+"");
            girls_details_shooting.setText(girls.getShooting()+"");
            girls_details_hp.setText(girls.getHp()+"");
            girls_details_piercing.setText(girls.getPiercing()+"");
            girls_details_cirt_rate.setText(girls.getCirtRate()+"");
            girls_details_cirt_damage.setText(girls.getCirtDamage()+"");
            girls_details_chain.setText(girls.getChain()+"");
            girls_details_armor.setText(girls.getArmor()+"");
            girls_details_skill_name.setText(girls.getSkillName());
            girls_details_skill_introduction.setText(girls.getSkillIntroduction());
            girls_details_skill_cd.setText(girls.getSkillCD());
            girls_details_aura_introduction.setText(girls.getAuraIntroduction());
            girls_details_dubbing.setText(girls.getDubbing());
            girls_details_painter.setText(girls.getPainter());
            girls_details_method.setText(girls.getMethod());
            girls_details_introduction.setText(girls.getIntroduction());

            String type = Tools.headUrl+"a/"+girls.getTypePic();
            Glide.with(this).load(type).placeholder(R.mipmap.rf).into(girls_details_type);
            String aura = Tools.headUrl+"a/"+girls.getAura();
            Glide.with(this).load(aura).placeholder(R.mipmap.rf).into(girls_details_aura);
            String skillPic = Tools.headUrl+"a/"+girls.getSkillPic();
            Glide.with(this).load(skillPic).placeholder(R.mipmap.rf).into(girls_details_skill_pic);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /*@SuppressLint("HandlerLeak")
    public class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.arg1;
            switch (i){
                case 1:
                    try {
                        JSONArray jsonArray  = new JSONArray(msg.obj.toString().trim());
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        Girls girls  = gson.fromJson(jsonObject.toString(),Girls.class);
                        String getPortrait = girls.getPortrait();
                        String getBreakPortrait = girls.getBreakPortrait();
                        String getSkin = girls.getSkin();
                        girlsList.add(getPortrait);
                        girlsList.add(getBreakPortrait);
                        if (getSkin!=null){
                            girlsList.add(getSkin);
                        }
                        for (int s = 0; s<girlsList.size(); s++){
                            ImageView imageView  =new ImageView(getApplicationContext());
                            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            String pic = Tools.headUrl+"a/"+girlsList.get(s);
                            Glide.with(getApplicationContext()).load(pic).placeholder(R.mipmap.picc).into(imageView);
                            imageViews.add(imageView);
                        }
                        MyAdapter adapter = new MyAdapter();
                        viewPager.setAdapter(adapter);
                        viewPager.setCurrentItem(currentIndex);
                        viewPager.addOnPageChangeListener(this);
                        *//*Girls girls = new Girls();
                        girls.setName(jsonObject.getString(""));
                        girls.setNo(jsonObject.getString());
                        girls.setStart(jsonObject.getInt());
                        girls.setType(jsonObject.getString());
                        girls.setTypePic(jsonObject.getString());
                        girls.setPortrait(jsonObject.getString());
                        girls.setBreakPortrait(jsonObject.getString());
                        girls.setHead(jsonObject.getString());
                        girls.setDamage(jsonObject.getInt());
                        girls.setHit(jsonObject.getInt());
                        girls.setAvoid(jsonObject.getInt());
                        girls.setShooting(jsonObject.getInt());
                        girls.setHp(jsonObject.getInt());
                        girls.setCirtRate(jsonObject.getInt());
                        girls.setCirtDamage(jsonObject.getInt());
                        girls.setPiercing(jsonObject.getInt());
                        girls.setArmor(jsonObject.getInt());
                        girls.setChain(jsonObject.getInt());
                        girls.setMethod(jsonObject.getString());
                        girls.setAura(jsonObject.getString());
                        girls.setAuraIntroduction(jsonObject.getString());
                        girls.setIntroduction(jsonObject.getString());
                        girls.setSkin(jsonObject.getString());
                        girls.setPainter(jsonObject.getString());
                        girls.setDubbing(jsonObject.getString());
                        girls.setSkillIntroduction(jsonObject.getString());
                        girls.setSkillName(jsonObject.getString());
                        girls.setSkillPic(jsonObject.getString());
                        girls.setSkillCD(jsonObject.getString());*//*

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }*/

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;  //最大值，可以认为无限大，反正你划不到尽头就行了
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            //在0~imageViewList.size()之间循环
           /* int index = position % imageViews.size();

            imageViews.get(index).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //处理图片点击事件....
                    Log.e("点击图片：",position % imageViews.size()+"");
                }
            });

            if (imageViews.size() > 0) {
                View view = imageViews.get(index);
                if (container.equals(view.getParent())) {
                    container.removeView(view);
                }
                container.addView(view);
                return view;
            }
            return null;*/
            View v = imageViews.get(position % imageViews.size());
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
            container.addView(v);
            return v;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //int index = position % imageViews.size();
           // container.removeView(imageViews.get(index));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isCyclical = false; //Activity退出后，图片循环线程停止
    }
}
