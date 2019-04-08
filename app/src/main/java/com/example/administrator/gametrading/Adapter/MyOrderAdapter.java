package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.CollectionBean;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.OrderBean;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.ShoppingCarPage.ComDetailsActivity;
import com.example.administrator.gametrading.ShoppingCarPage.OrderActivity;
import com.example.administrator.gametrading.Tools;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    private List<OrderBean> list;
    private Context context;
    public MyOrderAdapter(Context context,List<OrderBean> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =View.inflate(context, R.layout.item_my_order,null);
        final ViewHolder viewHolder = new MyOrderAdapter.ViewHolder(view);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int io = viewHolder.getAdapterPosition();
                        OrderBean orderBean = list.get(io);
                        Intent intent = new Intent(context, OrderActivity.class);
                        intent.putExtra("orderBean",orderBean);
                        context.startActivity(intent);
                    }
                });
                viewHolder.myOrderSolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //联系卖家
                    }
                });
                viewHolder.myOrderAsses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //查看评价
                    }
                });
                viewHolder.myOrderPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //更多
                    }
                });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            final OrderBean orderBean = new OrderBean();


            viewHolder.myOrderComName.setText(orderBean.getComName());
            viewHolder.myOrderPrice.setText(orderBean.getPrice());
            viewHolder.myOrderStatus.setText(orderBean.getStatus());
            String pic = Tools.headUrl+"a/"+orderBean.getOrderImage();
            Glide.with(context).load(pic).placeholder(R.mipmap.picc).into(viewHolder.myOrderImage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View myOrderView;
        ImageView myOrderImage;
        TextView myOrderComName,myOrderPrice,myOrderStatus;
        Button myOrderSolder,myOrderAsses,myOrderPop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myOrderView = itemView;
            myOrderComName = (TextView)itemView.findViewById(R.id.my_order_comName);
            myOrderPrice = (TextView)itemView.findViewById(R.id.my_order_price);
            myOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
            myOrderSolder = (Button)itemView.findViewById(R.id.order_call_solder);
            myOrderAsses = (Button)itemView.findViewById(R.id.order_assess);
            myOrderPop = (Button)itemView.findViewById(R.id.order_pop);

        }
    }
}