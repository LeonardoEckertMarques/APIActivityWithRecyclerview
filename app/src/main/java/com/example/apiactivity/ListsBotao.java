package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListsBotao extends AppCompatActivity {

  private Button comments;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_botoes);

    comments = findViewById(R.id.comments);

    comments.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(ListsBotao.this, ListsActivity.class);
        i.putExtra("op", "comments");
        startActivity(i);
      }
    });

  }
}