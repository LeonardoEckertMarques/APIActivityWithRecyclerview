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
import com.example.apiactivity.model.Comments;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

  private List<Comments> listaComments;
  private TextView tv;
  private int layout;

  public class CommentsViewHolder extends RecyclerView.ViewHolder {
    public View viewComments;
    public CommentsViewHolder(@NonNull View itemView) {
      super(itemView);
      this.viewComments = itemView;
    }
  }

  public CommentsAdapter(List<Comments> comments, int layout) {
    this.listaComments = comments;
    this.layout =  R.layout.layout_lista;
  }

  @NonNull
  @Override
  public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
    return new CommentsViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
    Comments obj = (Comments) this.listaComments.get(position);
    CardView bt = holder.viewComments.findViewById(R.id.cardUser);
    tv = holder.viewComments.findViewById(R.id.name);
    tv.setText(obj.getId() + " - " + obj.getName());
    bt.setTag(obj);
    bt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CardView btn = (CardView) v;
        Comments comments = (Comments) btn.getTag();
        Intent intent = new Intent(holder.viewComments.getContext(), DetalhesActivity.class);
        intent.putExtra("objTp", obj);
        holder.viewComments.getContext().startActivity(intent);
      }
    });

  }

  @Override
  public int getItemCount() {
    return this.listaComments.size();
  }
}
