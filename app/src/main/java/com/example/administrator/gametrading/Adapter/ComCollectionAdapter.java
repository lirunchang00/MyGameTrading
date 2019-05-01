package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.CollectionService;
import com.example.administrator.gametrading.ShoppingCarPage.ComDetailsActivity;
import com.example.administrator.gametrading.Tools;

import java.util.List;

public class ComCollectionAdapter extends RecyclerView.Adapter<ComCollectionAdapter.ViewHolder> {
    private List<CollectionBean> list;
    private Context context;
    public ComCollectionAdapter(Context context,List<CollectionBean> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view =View.inflate(context, R.layout.item_com_collection,null);
        final ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int io = viewHolder.getAdapterPosition();

                    CollectionBean collectionBean = list.get(io);
                    Intent intent = new Intent(context, ComDetailsActivity.class);
                    intent.putExtra("collectionBean",collectionBean);
                    context.startActivity(intent);
                }
            });

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int io = viewHolder.getAdapterPosition();
                CollectionBean collectionBean = list.get(io);
                String comId = String.valueOf(collectionBean.getComId());
                if(isChecked){
                    new CollectionService().deleteComCollection(context,comId);
                    viewHolder.comCollectionBtn.setTextOn("添加收藏");
                }
                else{
                    new CollectionService().addComCollection(context,comId);
                    viewHolder.comCollectionBtn.setTextOff("取消收藏");
                }
            }
        };
        viewHolder.comCollectionBtn.setOnCheckedChangeListener(listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComCollectionAdapter.ViewHolder viewHolder, int i) {
        final CollectionBean collectionBean = list.get(i);

        viewHolder.comCollectionPrice.setText(collectionBean.getPrice());
        viewHolder.comCollectionContent.setText(collectionBean.getComName());
        viewHolder.comCollectionSolder.setText(collectionBean.getSolder());
        String pic = Tools.headUrl+"a/"+collectionBean.getImage();
        Glide.with(context).load(pic).placeholder(R.mipmap.picc).into(viewHolder.comCollectionImage);
        String head = Tools.headUrl+"a/"+collectionBean.getHead();
        Log.e("pic",pic);
        Log.e("head",head);
        Glide.with(context).load(head).placeholder(R.drawable.main_my).into(viewHolder.comCollectionHead);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View comCollectionView;
        ImageView comCollectionHead,comCollectionImage;
        TextView comCollectionSolder,comCollectionPrice,comCollectionContent;
        ToggleButton comCollectionBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comCollectionView=itemView;
            comCollectionHead = (ImageView)itemView.findViewById(R.id.com_collection_head);
            comCollectionImage= (ImageView)itemView.findViewById(R.id.com_collection_image);
            comCollectionSolder = (TextView)itemView.findViewById(R.id.com_collection_solder);
            comCollectionContent = (TextView)itemView.findViewById(R.id.com_collection_content);
            comCollectionPrice = (TextView)itemView.findViewById(R.id.com_collection_price);
            comCollectionBtn = (ToggleButton)itemView.findViewById(R.id.com_collection_btn);
        }
    }
}
