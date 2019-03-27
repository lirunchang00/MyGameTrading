package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;

import java.util.List;

public class TrandingAdapter extends ArrayAdapter<Commodity> {

    private int resourceId;

    public TrandingAdapter(Context context, int textViewResourceId, List<Commodity> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View converView, ViewGroup parent) {
        Commodity commodity = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (converView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.it_image = (ImageView) view.findViewById(R.id.goods_image);
            viewHolder.it_name = (TextView) view.findViewById(R.id.goods_name_tv);
            viewHolder.it_price = (TextView) view.findViewById(R.id.good_price_tv);
            viewHolder.it_active = (TextView) view.findViewById(R.id.good_price_tv);
            viewHolder.it_content = (TextView) view.findViewById(R.id.goods_salesvolume_tv);
            view.setTag(viewHolder);
        } else {
            view = converView;
            viewHolder = (ViewHolder) view.getTag();
        }

        //viewHolder.it_image.setImageResource(Integer.valueOf(commodity.getComImage()));
        Glide.with(this.getContext()).load(Tools.url_comImages + commodity.getComImage()).placeholder(R.drawable.main_my).error(R.drawable.main_my).into(viewHolder.it_image);
        viewHolder.it_name.setText(commodity.getComName());
        viewHolder.it_content.setText(commodity.getComSynopsis());
        viewHolder.it_price.setText("￥"+commodity.getComPrice());
        viewHolder.it_active.setText(commodity.getActive());
        return view;
    }

    class ViewHolder {
        ImageView it_image;
        TextView it_name;
        TextView it_price;
        TextView it_active;
        TextView it_content;
    }
}