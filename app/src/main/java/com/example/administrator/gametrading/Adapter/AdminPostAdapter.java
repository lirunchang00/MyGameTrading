package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.IndexPage.DetailActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.AdminService;
import com.example.administrator.gametrading.Tools;

import java.util.List;

public class AdminPostAdapter extends  RecyclerView.Adapter<AdminPostAdapter.ViewHolder>{
    private List<Forum> mIndexList;
    private Context context;
    private Forum forum;

    public AdminPostAdapter(Context context, List<Forum> indexList) {
        this.context = context;//上下文
        this.mIndexList = indexList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_admin_post,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        final ViewHolder holder = new ViewHolder(view);
        final Context context  = view.getContext();
        holder.indexView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();
                forum = mIndexList.get(io);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("forum",forum);
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AdminService().deletePost(forum,context);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Forum forum = mIndexList.get(i);
        viewHolder.admin_post_title.setText(forum.getTitle());
        viewHolder.admin_post_time.setText(forum.getTime());
        viewHolder.admin_post_repeat.setText(forum.getRepeat());
        viewHolder.admin_post_author.setText(forum.getAuthor());

    }

    @Override
    public int getItemCount() {
        return mIndexList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View indexView;
        TextView admin_post_title,admin_post_repeat;
        TextView admin_post_author,admin_post_time;
        TextView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            indexView=itemView;
            delete=(TextView)itemView.findViewById(R.id.admin_post_delete);
            admin_post_title = (TextView)itemView.findViewById(R.id.admin_post_title);
            admin_post_author = (TextView)itemView.findViewById(R.id.admin_post_author);
            admin_post_time = (TextView)itemView.findViewById(R.id.admin_post_time);
            admin_post_repeat = (TextView)itemView.findViewById(R.id.admin_post_repeat);
        }
    }
}
