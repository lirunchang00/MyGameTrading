package com.example.administrator.gametrading.Adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.ViewHolder;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class CarAdapter extends CommoAdapter<HashMap<String,String>>{

    private ItemClickListener listener;

    public CarAdapter(Context context, List<HashMap<String, String>> datas, int layoutId){
        super(context,datas,layoutId);
    }

    @Override
    public void convert(final ViewHolder holder, HashMap<String, String> map) {

        if (map.get("id").equals("0")){
            holder.setChecked(R.id.sc_check_box,false);
        }else {
            holder.setChecked(R.id.sc_check_box,true);
        }

        Glide.with(this.mContext).load(Tools.url_comImages+map.get("comImage")).placeholder(R.drawable.main_my).error(R.drawable.main_my).into((ImageView) holder.getView(R.id.sc_image));
        //holder.setImageResource(R.id.sc_image,Integer.valueOf(map.get("image")));
        holder.setText(R.id.sc_name,map.get("comName"));
        holder.setText(R.id.sc_num,map.get("num"));
        holder.setText(R.id.sc_price,"￥"+Double.valueOf(map.get("comPrice")));

        final EasySwipeMenuLayout easySwipeMenuLayout=holder.getView(R.id.action_bar);

        holder.setOnClickListener(R.id.sc_check_box, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ItemClickListener(v,holder.getPosition());
            }
        });

        holder.setOnClickListener(R.id.sc_del, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easySwipeMenuLayout.resetStatus();
                listener.ItemDeleteClickListener(v, holder.getPosition());
            }
        });

        holder.setOnClickListener(R.id.sc_reduce, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ItemReduceClickListener(v, holder.getPosition());
            }
        });

        holder.setOnClickListener(R.id.sc_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ItemAddClickListener(v, holder.getPosition());
            }
        });
    }

    public void setOnItemClickListener(ItemClickListener listener){
        this.listener=listener;
    }

    public interface ItemClickListener {
        void ItemClickListener(View view, int position);

        void ItemDeleteClickListener(View view, int position);

        void ItemAddClickListener(View view, int position);

        void ItemReduceClickListener(View view, int position);
    }

}
