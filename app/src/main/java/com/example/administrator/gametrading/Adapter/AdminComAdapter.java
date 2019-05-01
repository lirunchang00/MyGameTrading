package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.IndexPage.DetailActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.AdminService;
import com.example.administrator.gametrading.ShoppingCarPage.ComDetailsActivity;
import com.example.administrator.gametrading.Tools;

import java.util.List;

public class AdminComAdapter extends RecyclerView.Adapter<AdminComAdapter.ViewHolder>{
    private List<Commodity> mIndexList;
    private Context context;

    public AdminComAdapter(Context context, List<Commodity> indexList) {
        this.context = context;//上下文
        this.mIndexList = indexList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_admin_com,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        final ViewHolder holder = new ViewHolder(view);
        final Context context  = view.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();

                Commodity commodity  = mIndexList.get(io);
                //int id = commodity.getComId();
                Intent intent = new Intent(context,ComDetailsActivity.class);
                intent.putExtra("commodity",commodity);
                context.startActivity(intent);
            }
        });
        holder.shenhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();

                Commodity commodity  = mIndexList.get(io);
                customView(commodity);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Commodity commodity = mIndexList.get(i);
        Log.e("commodity",commodity.toString());
        viewHolder.admin_com__price.setText(commodity.getComPrice());
        //viewHolder.admin_com__active.setText(commodity.getActive());
        viewHolder.admin_com__title.setText(commodity.getComName());
        viewHolder.admin_com__content.setText(commodity.getComContent());
        String type = Tools.headUrl+"a/"+commodity.getComImage();
        Glide.with(context).load(type).placeholder(R.drawable.main_my).into(viewHolder.admin_com_head);
    }

    @Override
    public int getItemCount() {
        return mIndexList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View indexView;
        ImageView admin_com_head;
        TextView admin_com__title,admin_com__price;
        TextView admin_com__content,admin_com__active;
        Button shenhe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            indexView=itemView;
            shenhe = (Button)itemView.findViewById(R.id.shenhe);
            admin_com_head = (ImageView)itemView.findViewById(R.id.admin_com_head);
            admin_com__price = (TextView)itemView.findViewById(R.id.admin_com__price);
            admin_com__active = (TextView)itemView.findViewById(R.id.admin_com__active);
            admin_com__title = (TextView)itemView.findViewById(R.id.admin_com__title);
            admin_com__content = (TextView)itemView.findViewById(R.id.admin_com__content);
        }
    }

    private void customView(final Commodity commodity) {
        // 创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置参数
        builder.setMessage("审核是否通过？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AdminService().shenhe(commodity,context);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create()
                .show();
    }
}
