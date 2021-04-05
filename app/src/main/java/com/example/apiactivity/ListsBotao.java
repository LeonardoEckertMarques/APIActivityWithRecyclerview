package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListsBotao extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_botoes);
    Button comments = findViewById(R.id.comments);
    Button posts = findViewById(R.id.posts);
    Button albums = findViewById(R.id.albums);
    posts.setOnClickListener(this);
    albums.setOnClickListener(this);
    comments.setOnClickListener(this);
  }

  public void onClick(View view) {
    Intent intent = new Intent(ListsBotao.this, ListsActivity.class);
    switch (view.getId()) {
      case R.id.comments:
        intent.putExtra("op", "comments");
        startActivity(intent);
        break;
      case R.id.posts:
        intent.putExtra("op", "posts");
        startActivity(intent);
        break;
      case R.id.albums:
        intent.putExtra("op", "albums");
        startActivity(intent);
        break;
    }
  }

}