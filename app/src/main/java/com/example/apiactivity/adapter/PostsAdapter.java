package com.example.apiactivity.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiactivity.DetalhesActivity;
import com.example.apiactivity.R;
import com.example.apiactivity.model.Posts;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<Posts> postsList;
    private TextView tv;
    private int layout;

    public class PostsViewHolder extends RecyclerView.ViewHolder {
        public View viewPosts;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewPosts = itemView;
        }
    }

    public PostsAdapter(List<Posts> posts, int layout) {
        this.postsList = posts;
        this.layout =  R.layout.layout_lista;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new PostsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        Posts obj = (Posts) this.postsList.get(position);
        CardView bt = holder.viewPosts.findViewById(R.id.cardUser);
        tv = holder.viewPosts.findViewById(R.id.link);
        tv.setText(obj.getId() + " - " + obj.getTitle());
        bt.setTag(obj);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView btn = (CardView) v;
                Posts posts = (Posts) btn.getTag();
                Intent intent = new Intent(holder.viewPosts.getContext(), DetalhesActivity.class);
                intent.putExtra("op", "posts");
                intent.putExtra("objTp", obj);
                holder.viewPosts.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.postsList.size();
    }
}
