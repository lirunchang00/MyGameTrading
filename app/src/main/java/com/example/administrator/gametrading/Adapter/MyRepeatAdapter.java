package com.example.administrator.gametrading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.ForumPage.PostActivity;
import com.example.administrator.gametrading.R;

import java.util.List;

public class MyRepeatAdapter extends RecyclerView.Adapter<MyRepeatAdapter.ViewHolder> {
    private List<Forum> list;
    private Context context;
    public MyRepeatAdapter(Context context,List<Forum> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //View view =View.inflate(context, R.layout.item_my_repeat,null);
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_my_repeat,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        final Context context  = view.getContext();
        final ViewHolder viewHolder = new MyRepeatAdapter.ViewHolder(view);
            viewHolder.myRepeatView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转回复页面
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
        final Forum forum =list.get(i);
        viewHolder.my_repeat_content.setText(forum.getContent());
        viewHolder.repeat_post_title.setText(forum.getTitle());
        viewHolder.my_repeat_time.setText(forum.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View myRepeatView;
        TextView my_repeat_content,repeat_post_title,my_repeat_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myRepeatView =itemView;
            my_repeat_content = (TextView)itemView.findViewById(R.id.my_repeat_content);
            repeat_post_title = (TextView)itemView.findViewById(R.id.repeat_post_title);
            my_repeat_time = (TextView)itemView.findViewById(R.id.my_repeat_time);
        }
    }
}