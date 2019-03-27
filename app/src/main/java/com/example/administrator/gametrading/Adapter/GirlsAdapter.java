package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;

import java.util.ArrayList;
import java.util.List;

public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.ViewHolder>{
    private ArrayList<Girls> mGirlsList;
    private Context mcontext;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_girls,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Girls girls = mGirlsList.get(i);
        String head = Tools.headUrl+"a/"+girls.getHead();
        Log.e("head",head);
        Glide.with(mcontext).load(head).placeholder(R.drawable.main_my).into(viewHolder.girls);

    }

    @Override
    public int getItemCount() {
        return mGirlsList.size();
    }

    public GirlsAdapter(Context context,ArrayList<Girls> girlsList){
        mcontext = context;
        mGirlsList = girlsList;
    }

     static public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView girls;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            girls = (ImageView)itemView.findViewById(R.id.girls_pic);
        }
    }
}
