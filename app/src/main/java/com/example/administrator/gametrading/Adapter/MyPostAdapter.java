package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.OrderBean;
import com.example.administrator.gametrading.ForumPage.PostActivity;
import com.example.administrator.gametrading.R;

import java.util.List;

public class MyPostAdapter  extends RecyclerView.Adapter<MyPostAdapter.ViewHolder> {
    private List<Forum> list;
    private Context context;
    public MyPostAdapter(Context context,List<Forum> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_my_post,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        final ViewHolder holder = new ViewHolder(view);
        final Context context  = view.getContext();
        final ViewHolder viewHolder = new MyPostAdapter.ViewHolder(view);
            viewHolder.forumview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击进入post
                    int io = viewHolder.getAdapterPosition();

                    Forum forum = list.get(io);
                    Intent intent = new Intent(context, PostActivity.class);
                    intent.putExtra("forum",forum);
                    context.startActivity(intent);
                }
            });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Forum forum = list.get(i);
        viewHolder.title.setText(forum.getTitle());
        viewHolder.author.setText(forum.getAuthor());
        viewHolder.time.setText(forum.getTime());
        viewHolder.repeat.setText(forum.getRepeat());
        boolean essence  = forum.isEssence();
        if (essence){
            viewHolder.title.setTextColor(Color.parseColor("#00CD00"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       /* View myPostView;
        TextView my_forum_title,my_forum_author,my_forum_repeat,my_forum_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myPostView =itemView;
            my_forum_author = (TextView)itemView.findViewById(R.id.my_forum_author);
            my_forum_title = (TextView)itemView.findViewById(R.id.my_forum_title);
            my_forum_repeat = (TextView)itemView.findViewById(R.id.my_forum_repeat);
            my_forum_time  = (TextView)itemView.findViewById(R.id.my_forum_time);
        }*/
        View forumview;
        TextView title;
        TextView author;
        TextView time;
        TextView repeat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            forumview = itemView;
            title = (TextView)itemView.findViewById(R.id.my_forum_title);
            author = (TextView)itemView.findViewById(R.id.my_forum_author);
            time = (TextView)itemView.findViewById(R.id.my_forum_time);
            repeat = (TextView)itemView.findViewById(R.id.my_forum_repeat);


            /*indexView=itemView;
            indexImage = (ImageView)itemView.findViewById(R.id.index_pic);
            indexName = (TextView)itemView.findViewById(R.id.index_text);*/
        }
    }
}