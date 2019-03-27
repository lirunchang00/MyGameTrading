package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.Index;
import com.example.administrator.gametrading.R;

import java.util.List;

public class IllustrationAdapter extends RecyclerView.Adapter<IllustrationAdapter.ViewHolder>{
    private List<Index> mIndexList;
    private Context context;

    public IllustrationAdapter(Context context, List<Index> indexList) {
        this.context = context;//上下文
        this.mIndexList = indexList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //View view = LayoutInflater.from(viewGroup.getContext())
       //         .inflate(R.layout.item_index,viewGroup,false);

        View view = View.inflate(context, R.layout.item_index, null);
        final ViewHolder holder = new ViewHolder(view);
        holder.indexView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();
                Index index = mIndexList.get(io);
                Intent intent = index.getIntent();
                context.startActivity(intent);
                Toast.makeText(v.getContext(),"点击"+index.getName(),Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Index index = mIndexList.get(i);
        viewHolder.indexName.setText(index.getName());
        viewHolder.indexImage.setImageResource(index.getImageId());
    }

    @Override
    public int getItemCount() {
        return mIndexList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View indexView;
        ImageView indexImage;
        TextView indexName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            indexView=itemView;
            indexImage = (ImageView)itemView.findViewById(R.id.index_pic);
            indexName = (TextView)itemView.findViewById(R.id.index_text);
        }
    }
}
