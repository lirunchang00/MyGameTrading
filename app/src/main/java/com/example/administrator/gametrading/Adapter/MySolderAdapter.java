package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.OrderBean;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.ShoppingCarPage.ComDetailsActivity;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;

import java.util.List;

public class MySolderAdapter  extends RecyclerView.Adapter<MySolderAdapter.ViewHolder> {
    private List<Commodity> list;
    private Context context;
    private String Cookie = "";
    public MySolderAdapter(Context context,List<Commodity> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
       // View view =View.inflate(context, R.layout.item_my_solder,null);
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_my_solder,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        final ViewHolder viewHolder = new MySolderAdapter.ViewHolder(view);
            viewHolder.mySolderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int io = viewHolder.getAdapterPosition();
                    //跳转我的出售
                    Commodity commodity = list.get(io);
                    Log.e("commoditysolder",commodity.toString());
                    Intent intent = new Intent(context, ComDetailsActivity.class);
                    intent.putExtra("solder",commodity);
                    context.startActivity(intent);
                }
            });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Commodity commodity =list.get(i);
        if (new SessionUtil(context).GetSession()!=null){
            Cookie = new SessionUtil(context).GetSession();
        }
        String cookie = Cookie.substring(5)+"jpg";
        viewHolder.my_solder_solder.setText(commodity.getSolder());
        viewHolder.my_solder_price.setText(commodity.getComPrice());
        viewHolder.my_solder_title.setText(commodity.getComName());
        viewHolder.my_solder_status.setText(commodity.getStatus());
        String pic = Tools.headUrl+"a/"+commodity.getComImage();
        Glide.with(context).load(pic).placeholder(R.mipmap.picc).into(viewHolder.my_solder_image);
        String head = Tools.headUrl+"a/"+cookie;
        Glide.with(context).load(head).placeholder(R.drawable.main_my).into(viewHolder.my_solder_head);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mySolderView;
        ImageView my_solder_head,my_solder_image;
        TextView my_solder_solder,my_solder_price,my_solder_title,my_solder_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mySolderView = itemView;
            my_solder_head =(ImageView) itemView.findViewById(R.id.my_solder_head);
            my_solder_image =(ImageView) itemView.findViewById(R.id.my_solder_image);
            my_solder_solder =(TextView)itemView.findViewById(R.id.my_solder_solder);
            my_solder_price =(TextView)itemView.findViewById(R.id.my_solder_price);
            my_solder_title =(TextView)itemView.findViewById(R.id.my_solder_title);
            my_solder_status =(TextView)itemView.findViewById(R.id.my_solder_status);
        }
    }
}