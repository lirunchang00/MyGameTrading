package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.ForumPage.PostActivity;
import com.example.administrator.gametrading.R;

import java.util.List;

public class PostCollectionAdapter extends RecyclerView.Adapter<PostCollectionAdapter.ViewHolder> {
    private List<Forum> list;
    private Context context;
    public PostCollectionAdapter(Context context,List<Forum> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =View.inflate(context, R.layout.item_post,null);
        final ViewHolder viewHolder = new PostCollectionAdapter.ViewHolder(view);
            viewHolder.myPostView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        final Forum forum =new Forum();
        viewHolder.forum_collection_time.setText(forum.getTime());
        viewHolder.forum_collection_repeat.setText(forum.getRepeat());
        viewHolder.forum_collection_title.setText(forum.getTitle());
        viewHolder.forum_collection_author.setText(forum.getAuthor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View myPostView;
        TextView forum_collection_title,forum_collection_author,forum_collection_time,forum_collection_repeat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myPostView =itemView;
            forum_collection_title = (TextView)itemView.findViewById(R.id.forum_collection_title);
            forum_collection_author = (TextView)itemView.findViewById(R.id.forum_collection_author);
            forum_collection_time = (TextView)itemView.findViewById(R.id.forum_collection_time);
            forum_collection_repeat  = (TextView)itemView.findViewById(R.id.forum_collection_repeat);
        }
    }
}