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
import com.example.apiactivity.adapter.CommentsAdapter;
import com.example.apiactivity.model.Comments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener {

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
      case "comments":
        tipo.setText("Lista Selecionada: \n" +op.toUpperCase());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/" + op;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);
        queue.add(jsonArrayRequest);
        break;
    }

  }

  @Override
  public void onResponse(JSONArray response) {

    try {
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

      RecyclerView recycler = findViewById(R.id.recycler);
      GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
      recycler.setLayoutManager(gridLayoutManager);

      CommentsAdapter commentsAdapter = new CommentsAdapter(comments, R.layout.layout_lista);
      recycler.setAdapter(commentsAdapter);

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