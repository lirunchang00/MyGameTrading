package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.IndexPage.DetailActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.AdminService;
import com.example.administrator.gametrading.Tools;

import java.util.ArrayList;
import java.util.List;

public class AdminIndexAdapter extends RecyclerView.Adapter<AdminIndexAdapter.ViewHolder>{
    private List<Girls> mIndexList;
    private Context context;

    public AdminIndexAdapter(Context context, List<Girls> indexList) {
        this.context = context;//上下文
        this.mIndexList = indexList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_admin_index, null);
        final ViewHolder holder = new ViewHolder(view);
        holder.indexView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();
                Girls girls = mIndexList.get(io);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("girls",girls);
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();
                Girls girls = mIndexList.get(io);
                new AdminService().deleteIndex(girls,context);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Girls girls = mIndexList.get(i);
        Log.e("girls",girls.toString());
        viewHolder.indexName.setText(girls.getName());
        viewHolder.indexNo.setText(girls.getNo());
        String type = Tools.headUrl+"a/"+girls.getTypePic();
        Log.e("type",type);
        Glide.with(context).load(type).placeholder(R.drawable.main_my).into(viewHolder.indexType);
        String pic = Tools.headUrl+"a/"+girls.getHead();
        Glide.with(context).load(pic).placeholder(R.mipmap.picc).into(viewHolder.indexImage);
    }

    @Override
    public int getItemCount() {
        return mIndexList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View indexView;
        ImageView indexImage;
        TextView indexName;
        TextView indexNo;
        ImageView indexType;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            indexView=itemView;
            delete =(Button)itemView.findViewById(R.id.admin_delete) ;
            indexImage = (ImageView)itemView.findViewById(R.id.admin_girls_pic);
            indexType = (ImageView)itemView.findViewById(R.id.admin_girls_type_pic);
            indexName = (TextView)itemView.findViewById(R.id.admin_girls_name);
            indexNo = (TextView)itemView.findViewById(R.id.admin_girls_no);
        }
    }
}
