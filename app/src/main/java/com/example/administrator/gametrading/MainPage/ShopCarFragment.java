package com.example.administrator.gametrading.MainPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.gametrading.Adapter.CarAdapter;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.ShoppingCarPage.OrderActivity;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.HttpUtil;
import com.example.administrator.gametrading.util.SessionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ShopCarFragment extends LazyLoadBaseFragment implements CarAdapter.ItemClickListener {

    @BindView(R.id.sc_listView)ListView sc_listview;
    @BindView(R.id.all_chekbox)CheckBox all_checkbox;
    @BindView(R.id.sc_total_price)TextView sc_total_price;
    @BindView(R.id.sc_pay)TextView sc_pay;
    @BindView(R.id.emptycar)LinearLayout emptycar;
    @BindView(R.id.sc_bottom)LinearLayout sc_bottom;

    private double totalPrice = 0.00;
    private int totalCount = 0;
    private List<HashMap<String, String>> shopList;
    private List<HashMap<String, String>> payList;
    public static FragmentActivity instance;

    private CarAdapter adapter;
    private View view;
    private Handler carhandler,delhandler;
    public ShopCarFragment(){}

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_car_fargment,container,false);
        instance=getActivity();

        ButterKnife.bind(this,view);
        carhandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        emptycar.setVisibility(View.GONE);
                        initView(view);
                        break;
                    case 2:
                        sc_listview.setVisibility(View.GONE);
                        sc_bottom.setVisibility(View.GONE);
                        break;

                }
            }
        };

        String Cookie="";
        Cookie = new SessionUtil(getActivity()).GetSession();


        if (Cookie==""){
            Message msg=new Message();
            msg.what=2;
            carhandler.sendMessage(msg);
        }else{
            //String cookie =Cookie.substring(5);
            initData();
        }
       return view;
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        adapter=new CarAdapter(getActivity(),shopList, R.layout.item_car_sideslip);
        adapter.setOnItemClickListener(this);
        sc_listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData(){

        String Cookie="";
        Cookie = new SessionUtil(getActivity()).GetSession();
        String cookie =Cookie.substring(5);

        HttpUtil.getShopcar(Tools.baseUrl+"getShopcar",cookie,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                shopList = new ArrayList<>();
                try {
                    JSONArray jsonArray=new JSONArray(responseData);
                    for (int i =0 ;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("id", "0");
                        map.put("comStock", jsonObject.getString("comStock"));
                        map.put("comPrice", jsonObject.getString("comPrice"));
                        map.put("comSoldNum", jsonObject.getString("comSoldNum"));
                        map.put("num", jsonObject.getString("num"));
                        map.put("comSynopsis", jsonObject.getString("comSynopsis"));
                        map.put("comName", jsonObject.getString("comName"));
                        map.put("comFlag", jsonObject.getString("comFlag"));
                        map.put("comImage", jsonObject.getString("comImage"));
                        map.put("comId", jsonObject.getString("comId"));
                        map.put("comClass", jsonObject.getString("comClass"));
                        shopList.add(map);
                    }
                    Log.e("getShopcar",shopList.toString());

                    if (shopList.size()!=0){
                        Message msg=new Message();
                        msg.what=1;
                        carhandler.sendMessage(msg);
                    }else {
                        Message msg=new Message();
                        msg.what=2;
                        carhandler.sendMessage(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
    @OnClick({R.id.all_chekbox, R.id.sc_pay})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.all_chekbox:
                AllTheSelected(true);
                break;
            case R.id.sc_pay:
                if (totalCount <=0){
                    Toast.makeText(getActivity(), "请选择要付款的商品~", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    payList = new ArrayList<>();
                    for (int i=0;i<shopList.size();i++){
                        if (shopList.get(i).get("id").equals("1")){
                            payList.add(shopList.get(i));
                        }
                    }
                    Log.e("payList",payList.toString());

                    Intent intent=new Intent(getActivity(), OrderActivity.class);
                    intent.putExtra("payList", (Serializable) payList);
                    intent.putExtra("totalPrice",totalPrice);
                    startActivity(intent);
                }
        }
    }
    @Override
    public void ItemClickListener(View view, int position) {
        HashMap<String, String>hashMap=shopList.get(position);
        if (((CheckBox)view).isChecked()){
            hashMap.put("id","1");
        }else {
            hashMap.put("id","0");
        }
        shopList.set(position, hashMap);
        AllTheSelected(false);
    }
    //删除商品
    @SuppressLint("HandlerLeak")
    @Override
    public void ItemDeleteClickListener(View view, final int position) {
        String Cookie="";
        Cookie = new SessionUtil(getActivity()).GetSession();
        String cookie =Cookie.substring(5);

        delhandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        shopList.remove(position);
                        AllTheSelected(false);
                        if (shopList.size()==0){
                            sc_listview.setVisibility(View.GONE);
                            sc_bottom.setVisibility(View.GONE);
                            emptycar.setVisibility(View.VISIBLE);
                        }
                }
            }
        };

        HttpUtil.deleteShopcar(Tools.baseUrl+"deleteShopcar",cookie,shopList.get(position).get("comId"),new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException{
                String delCar=response.body().string();
                int dc=Integer.valueOf(delCar);
                if (dc==1){

                    Log.e("删除购物车成功",delCar);

                    Message msg=new Message();
                    msg.what=1;
                    delhandler.sendMessage(msg);
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
    //增加商品
    @Override
    public void ItemAddClickListener(View view, int position) {
        HashMap<String, String> hashMap = shopList.get(position);
        int currentCount = Integer.valueOf(hashMap.get("num"));
        int c=Integer.valueOf(shopList.get(position).get("comStock"));
        if (currentCount + 1 > c) {
            Toast.makeText(getActivity(), "亲，已达库存上限~", Toast.LENGTH_SHORT).show();
        } else {
            hashMap.put("num", String.valueOf(currentCount + 1));
            shopList.set(position, hashMap);
        }
        AllTheSelected(false);
    }
    //减少商品
    @Override
    public void ItemReduceClickListener(View view, int position) {
        HashMap<String, String> hashMap = shopList.get(position);
        int currentCount = Integer.valueOf(hashMap.get("num"));
        if (currentCount - 1 < 1) {
            Toast.makeText(getActivity(), "宝贝不能再减少了哦~", Toast.LENGTH_SHORT).show();
        } else {
            hashMap.put("num", String.valueOf(currentCount - 1));
            shopList.set(position, hashMap);
        }
        AllTheSelected(false);
    }
    //选择
    private void AllTheSelected(Boolean aBoolean){
        int number=0;
        for (int j=0;j<shopList.size();j++){
            if (shopList.get(j).get("id").equals("1")){
                number++;
            }
        }
        if (aBoolean) {
            //反选
            if (number==shopList.size()){
                for (int i=0;i<shopList.size();i++){
                    shopList.get(i).put("id","0");
                }
                all_checkbox.setChecked(false);
                //全选
            } else if (number==0){
                for (int i=0;i<shopList.size();i++){
                    shopList.get(i).put("id","1");
                }
                all_checkbox.setChecked(true);
                //部分选择
            }else {
                for (int i=0;i<shopList.size();i++){
                    shopList.get(i).put("id", "1");
                }
                all_checkbox.setChecked(true);
            }
        }else {
            if (number==shopList.size()){
                all_checkbox.setChecked(true);
            }else {
                all_checkbox.setChecked(false);
            }
        }

        adapter.notifyDataSetChanged();
        priceContro();
    }
    //价格计算
    private void priceContro() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i=0;i<shopList.size();i++){
            if (shopList.get(i).get("id").equals("1")){
                totalCount++;
                double goodsPrice=Integer.valueOf(shopList.get(i).get("num")) * Double.valueOf(shopList.get(i).get("comPrice"));
                totalPrice = totalPrice + goodsPrice;
            }
        }
        DecimalFormat decimalFormat=new DecimalFormat("#.00");
        sc_total_price.setText("￥ " + decimalFormat.format(totalPrice));
        sc_pay.setText("付款(" + totalCount + ")");
    }
}
