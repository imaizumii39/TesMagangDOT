package com.example.alex.testapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alex on 3/25/2019.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<CommentData> comment_data;

    public CommentAdapter(Context context, List<CommentData> comment_data) {
        this.context = context;
        this.comment_data = comment_data;
    }


    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card2,parent,false);
        return new CommentAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        holder.name.setText(comment_data.get(position).getName());
        holder.email.setText(comment_data.get(position).getEmail());
        holder.body.setText(comment_data.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return comment_data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name, email, body;
        public CardView card;
        public ViewHolder(View itemView){
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.card_comment);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            email = (TextView) itemView.findViewById(R.id.txt_email);
            body = (TextView) itemView.findViewById(R.id.txt_body);
        }
    }
}
