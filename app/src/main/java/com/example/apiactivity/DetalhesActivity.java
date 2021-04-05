package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apiactivity.model.Albums;
import com.example.apiactivity.model.Comments;
import com.example.apiactivity.model.Posts;

public class DetalhesActivity extends AppCompatActivity {

  private TextView tv;
  private String op;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhes);


    op = getIntent().getStringExtra("op");

    switch(op) {
      case "posts":
        Posts posts = getIntent().getParcelableExtra("objTp");
        bindPosts(posts);
        break;
      case "albums":
        Albums albums = getIntent().getParcelableExtra("objTp");
        bindAlbums(albums);
        break;
      case "comments":
        Comments comments = getIntent().getParcelableExtra("objTp");
        bindComments(comments);
        break;
    }

  }


  private void bindPosts(Posts obj) {
    tv = findViewById(R.id.userId);
    tv.setText("UserID: "+obj.getUserId()+"");
    tv = findViewById(R.id.postId);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.id);
    tv.setText("ID: "+obj.getId()+"");
    tv = findViewById(R.id.title);
    tv.setText("Title: " + obj.getTitle()+"");
    tv = findViewById(R.id.name);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.email);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.body);
    tv.setText("Body: "+obj.getBody()+"");
  }

  private void bindAlbums(Albums obj) {
    tv = findViewById(R.id.userId);
    tv.setText("UserID: "+obj.getUserId()+"");
    tv = findViewById(R.id.postId);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.id);
    tv.setText("ID: "+obj.getId()+"");
    tv = findViewById(R.id.title);
    tv.setText("Title: " + obj.getTitle()+"");
    tv = findViewById(R.id.name);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.email);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.body);
    tv.setVisibility(View.GONE);
  }

  private void bindComments(Comments obj) {
    tv = findViewById(R.id.userId);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.postId);
    tv.setText("PostID: "+obj.getPostId()+"");
    tv = findViewById(R.id.id);
    tv.setText("ID: "+obj.getId()+"");
    tv = findViewById(R.id.title);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.name);
    tv.setText("Name: "+obj.getName()+"");
    tv = findViewById(R.id.email);
    tv.setText("Email: "+obj.getEmail()+"");
    tv = findViewById(R.id.body);
    tv.setText("Body: "+obj.getBody()+"");
  }

}