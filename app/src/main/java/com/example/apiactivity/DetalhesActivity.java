package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apiactivity.model.Comments;

public class DetalhesActivity extends AppCompatActivity {

  private TextView tv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhes);

    Comments comments = getIntent().getParcelableExtra("objTp");
    bind(comments);
  }

  private void bind(Comments obj) {
    tv = findViewById(R.id.postId);
    tv.setText("PostID: "+obj.getPostId()+"");
    tv = findViewById(R.id.id);
    tv.setText("ID: "+obj.getId()+"");
    tv = findViewById(R.id.name);
    tv.setText("Name: "+obj.getName()+"");
    tv = findViewById(R.id.email);
    tv.setText("Email: "+obj.getEmail()+"");
    tv = findViewById(R.id.body);
    tv.setText("Body: "+obj.getBody()+"");
  }

}