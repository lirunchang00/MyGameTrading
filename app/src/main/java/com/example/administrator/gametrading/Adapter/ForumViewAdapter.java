package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.ForumPage.PostActivity;
import com.example.administrator.gametrading.R;


import java.util.List;

public class ForumViewAdapter extends RecyclerView.Adapter<ForumViewAdapter.ViewHolder>{

    private List<Forum> mForumList;
    private Context context;
    public ForumViewAdapter(Context context, List<Forum> mForumList) {
        this.context = context;//上下文
        this.mForumList = mForumList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_forum_all, null);
        final ViewHolder holder = new ViewHolder(view);
        final Context context  = view.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();

                Forum forum  = mForumList.get(io);
                Log.e("forum1",""+forum.getAuthor());
                Intent intent = new Intent(context,PostActivity.class);
                intent.putExtra("forum",forum);

                context.startActivity(intent);
            }
        });

       /* holder.forumview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int io = holder.getAdapterPosition();
                Forum forum = mForumList.get(io);
                Toast.makeText(v.getContext(),"点击"+forum.getTitle(),Toast.LENGTH_LONG).show();
            }
        });*/
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Forum forum = mForumList.get(i);

        viewHolder.title.setText(forum.getTitle());
        viewHolder.author.setText(forum.getAuthor());
        viewHolder.time.setText(forum.getTime());
        viewHolder.repeat.setText(forum.getRepeat());
        boolean essence  = forum.isEssence();
        if (essence){
            viewHolder.title.setTextColor(Color.parseColor("#00CD00"));
        }

        /*viewHolder.indexName.setText(index.getName());
        viewHolder.indexImage.setImageResource(index.getImageId());*/
    }




    @Override
    public int getItemCount() {
        return mForumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View forumview;
        TextView title;
        TextView author;
        TextView time;
        TextView repeat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            forumview = itemView;
            title = (TextView)itemView.findViewById(R.id.forum_all_title);
            author = (TextView)itemView.findViewById(R.id.forum_all_author);
            time = (TextView)itemView.findViewById(R.id.forum_all_time);
            repeat = (TextView)itemView.findViewById(R.id.forum_all_repeat);


            /*indexView=itemView;
            indexImage = (ImageView)itemView.findViewById(R.id.index_pic);
            indexName = (TextView)itemView.findViewById(R.id.index_text);*/
        }
    }
}
