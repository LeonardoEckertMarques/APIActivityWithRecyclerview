package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apiactivity.adapter.AlbumsAdapter;
import com.example.apiactivity.adapter.CommentsAdapter;
import com.example.apiactivity.adapter.PostsAdapter;
import com.example.apiactivity.model.Albums;
import com.example.apiactivity.model.Comments;
import com.example.apiactivity.model.Posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener {

  List<Posts> posts =  new ArrayList<>();
  List<Albums> albums =  new ArrayList<>();
  List<Comments> comments = new ArrayList<>();

  private TextView tipo;
  private String op;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista);
    tipo = findViewById(R.id.listaSelecionada);
    op = getIntent().getStringExtra("op");

    switch (op) {
      case "posts":
      case "albums":
      case "comments":
        tipo.setText("Lista Selecionada: \n" +op.toUpperCase());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/" + op;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);
        queue.add(jsonArrayRequest);
        break;
      default: break;
    }

  }

  @Override
  public void onResponse(JSONArray response) {

    try {

      RecyclerView recycler = findViewById(R.id.recycler);

      switch (op) {
        case "comments":
          for(int i = 0; i < response.length(); i++) {
            JSONObject json = response.getJSONObject(i);
            Comments obj = new Comments(
                    json.getInt("postId"),
                    json.getInt("id"),
                    json.getString("name"),
                    json.getString("email"),
                    json.getString("body"));
            comments.add(obj);
          }

          Toast.makeText(this,"Recebido: " + comments.size() + " comments",Toast.LENGTH_LONG).show();

          GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
          recycler.setLayoutManager(gridLayoutManager);

          CommentsAdapter commentsAdapter = new CommentsAdapter(comments, 0);
          recycler.setAdapter(commentsAdapter);
          break;
        case "albums":
          for(int i = 0; i < response.length(); i++) {
            JSONObject json = response.getJSONObject(i);
            Albums obj = new Albums(
                    json.getInt("userId"),
                    json.getInt("id"),
                    json.getString("title"));
            albums.add(obj);
          }

          Toast.makeText(this,"Recebido: " + albums.size() + " albums",Toast.LENGTH_LONG).show();

          GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3);
          recycler.setLayoutManager(gridLayoutManager1);

          AlbumsAdapter albumsAdapter = new AlbumsAdapter(albums, 0);
          recycler.setAdapter(albumsAdapter);
          break;

        case "posts":
          for(int i = 0; i < response.length(); i++) {
            JSONObject json = response.getJSONObject(i);
            Posts obj = new Posts(
                    json.getInt("userId"),
                    json.getInt("id"),
                    json.getString("title"),
                    json.getString("body"));
            posts.add(obj);
          }

          Toast.makeText(this,"Recebido: " + posts.size() + " posts",Toast.LENGTH_LONG).show();

          LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
          recycler.setLayoutManager(linearLayoutManager);

          PostsAdapter postsAdapter = new PostsAdapter(posts, 0);
          recycler.setAdapter(postsAdapter);
          break;

        default: break;
      }

    } catch (JSONException e) {
        Log.e("JSONException",e.getMessage());
        e.printStackTrace();
    }
  }

  @Override
  public void onErrorResponse(VolleyError error) {
    String msg = error.getMessage();
    Toast.makeText(ListsActivity.this,"onErrorResponse: "+msg,Toast.LENGTH_LONG).show();
  }

}