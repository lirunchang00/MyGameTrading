package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private ArrayList<Forum> mforums;
    private Context mcontext;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Forum forum = mforums.get(i);

        String title  = forum.getTitle();
        int flag = Integer.valueOf(forum.getFloor());
        int a  = flag%2;
       /* Log.e("a",a+"");
        if (title=="null"){
            viewHolder.linearLayout_post_title.setVisibility(View.GONE);
        }else {
            viewHolder.title.setText(forum.getTitle());
        }*/
       Log.e("postAdapter",forum.toString());
        viewHolder.content.setText(forum.getContent());
        viewHolder.like.setText(forum.getAwesome()+"");
        viewHolder.time.setText(forum.getTime());
        viewHolder.author.setText(forum.getAuthor());
        viewHolder.floor.setText(forum.getFloor());
        viewHolder.active.setText(forum.getActive()+"");
        boolean essence  = forum.isEssence();
        if (essence){
            viewHolder.title.setTextColor(Color.parseColor("#00CD00"));
        }

      /*  if (a==0) {
            viewHolder.linearLayout_post_head.setBackgroundResource(R.color.white_yellow);
            viewHolder.linearLayout_post_content.setBackgroundResource(R.color.white_yellow);
            viewHolder.linearLayout_post_like.setBackgroundResource(R.color.white_yellow);
        }*/
        String head = Tools.headUrl+"a/"+forum.getHead();
        Log.e("head",head);
        Glide.with(mcontext).load(head).placeholder(R.drawable.main_my).into(viewHolder.head);

    }

    @Override
    public int getItemCount() {
        return mforums.size();
    }

    public PostAdapter(Context context,ArrayList<Forum> forums){
        mcontext = context;
        mforums = forums;
    }

    static public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,author,content,active,floor,like,time;
        LinearLayout linearLayout_post_head,linearLayout_post_content,linearLayout_post_like,linearLayout_post_title;
        ImageView head;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout_post_title= (LinearLayout)itemView.findViewById(R.id.post_linearlayout_title);
            linearLayout_post_head = (LinearLayout)itemView.findViewById(R.id.post_linearlayout_head);
            linearLayout_post_content = (LinearLayout)itemView.findViewById(R.id.post_linearlayout_content);
            linearLayout_post_like = (LinearLayout)itemView.findViewById(R.id.post_linearlayout_like);
            head = (ImageView)itemView.findViewById(R.id.post_head);
            title = (TextView)itemView.findViewById(R.id.post_title);
            author = (TextView)itemView.findViewById(R.id.post_author);
            content = (TextView)itemView.findViewById(R.id.post_content);
            active = (TextView)itemView.findViewById(R.id.post_active);
            floor = (TextView)itemView.findViewById(R.id.post_floor);
            time = (TextView)itemView.findViewById(R.id.post_time);
            like = (TextView)itemView.findViewById(R.id.post_like);


        }
    }
}
