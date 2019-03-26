package com.example.alex.testapp;

import android.content.Context;
import android.content.Intent;
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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<PostData> my_data;

    public PostAdapter(Context context, List<PostData> my_data) {
        this.context = context;
        this.my_data = my_data;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(my_data.get(position).getTitle());
        holder.body.setText(my_data.get(position).getBody());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CommentActivity.class);
                intent.putExtra("id", String.valueOf(my_data.get(position).getId()));
                intent.putExtra("title",String.valueOf(my_data.get(position).getTitle()));
                intent.putExtra("body",String.valueOf(my_data.get(position).getBody()));
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title, body;
        public CardView card;
        public ViewHolder(View itemView){
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.card_post);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            body = (TextView) itemView.findViewById(R.id.txt_body);
        }
    }

}
