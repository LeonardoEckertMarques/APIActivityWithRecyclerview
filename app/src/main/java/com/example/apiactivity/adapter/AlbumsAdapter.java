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
import com.example.apiactivity.model.Albums;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {

    private List<Albums> albumsList;
    private TextView tv;
    private int layout;

    public class AlbumsViewHolder extends RecyclerView.ViewHolder {
        public View viewAlbums;
        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewAlbums = itemView;
        }
    }

    public AlbumsAdapter(List<Albums> albums, int layout) {
        this.albumsList = albums;
        if (this.layout == 0) this.layout =  R.layout.layout_lista;
    }

    @NonNull
    @Override
    public AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new AlbumsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsViewHolder holder, int position) {
        Albums obj = (Albums) this.albumsList.get(position);
        CardView bt = holder.viewAlbums.findViewById(R.id.cardUser);
        tv = holder.viewAlbums.findViewById(R.id.link);
        tv.setText(obj.getId() + " - " + obj.getTitle());
        bt.setTag(obj);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView btn = (CardView) v;
                Albums albums = (Albums) btn.getTag();
                Intent intent = new Intent(holder.viewAlbums.getContext(), DetalhesActivity.class);
                intent.putExtra("op", "albums");
                intent.putExtra("objTp", obj);
                holder.viewAlbums.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.albumsList.size();
    }
}
