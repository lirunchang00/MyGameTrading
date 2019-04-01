package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
        View view = View.inflate(context, R.layout.item_tranding, null);
        final TrandingAdapter.ViewHolder holder = new TrandingAdapter.ViewHolder(view);
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

        viewHolder.comPrice.setText(commodity.getComPrice()+"");
        viewHolder.comContent.setText(commodity.getComContent());
        viewHolder.comName.setText(commodity.getComName());
        viewHolder.comActive.setText(commodity.getActive()+"");
        String pic = Tools.headUrl+"a/"+commodity.getComImage();
        Glide.with(context).load(pic).placeholder(R.drawable.main_my).into(viewHolder.comImage);
        /*viewHolder.indexName.setText(index.getName());
        viewHolder.indexImage.setImageResource(index.getImageId());*/
    }




    @Override
    public int getItemCount() {
        return mComList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View shopView;
        ImageView comImage;
        TextView comName;
        TextView comPrice;
        TextView comContent;
        TextView comActive;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shopView = itemView;
            comImage = (ImageView) itemView.findViewById(R.id.goods_image);
            comName = (TextView)itemView.findViewById(R.id.goods_name_tv);
            comPrice = (TextView)itemView.findViewById(R.id.good_price_tv) ;
            comContent = (TextView)itemView.findViewById(R.id.goods_introduction_tv);
            comActive = (TextView)itemView.findViewById(R.id.goods_salesvolume_tv);



        }
    }
}