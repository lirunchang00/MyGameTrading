package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.ShoppingCarPage.ComDetailsActivity;

import java.util.List;

public class TrandingAdapter extends RecyclerView.Adapter<TrandingAdapter.ViewHolder> {

    private List<Commodity> mComList;
    private Context context;
    public TrandingAdapter(Context context, List<Commodity> mComList) {
        this.context = context;//上下文
        this.mComList = mComList;
    }
    @NonNull
    @Override
    public TrandingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       // View view = View.inflate(context, R.layout.item_tranding, null);
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tranding,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        final ViewHolder holder = new ViewHolder(view);
        final Context context  = view.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();

                Commodity commodity  = mComList.get(io);
                //int id = commodity.getComId();
                Intent intent = new Intent(context,ComDetailsActivity.class);
                intent.putExtra("commodity",commodity);
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrandingAdapter.ViewHolder viewHolder, final int i) {
        final Commodity commodity = mComList.get(i);

        viewHolder.item_tranding_price.setText(commodity.getComPrice());
        viewHolder.item_tranding_content.setText(commodity.getComContent());
        viewHolder.item_tranding_title.setText(commodity.getComName());
        viewHolder.item_tranding_active.setText(commodity.getActive()+"");
        String pic = Tools.headUrl+"a/"+commodity.getComImage();
        Glide.with(context).load(pic).placeholder(R.drawable.main_my).into(viewHolder.item_tranding_head);
        /*viewHolder.indexName.setText(index.getName());
        viewHolder.indexImage.setImageResource(index.getImageId());*/
    }




    @Override
    public int getItemCount() {
        return mComList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View shopView;
        ImageView item_tranding_head;
        TextView item_tranding_title;
        TextView item_tranding_price;
        TextView item_tranding_content;
        TextView item_tranding_active;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shopView = itemView;
            item_tranding_head = (ImageView) itemView.findViewById(R.id.item_tranding_head);
            item_tranding_title = (TextView)itemView.findViewById(R.id.item_tranding_title);
            item_tranding_price = (TextView)itemView.findViewById(R.id.item_tranding_price) ;
            item_tranding_content = (TextView)itemView.findViewById(R.id.item_tranding_content);
            item_tranding_active = (TextView)itemView.findViewById(R.id.item_tranding_active);



        }
    }
}