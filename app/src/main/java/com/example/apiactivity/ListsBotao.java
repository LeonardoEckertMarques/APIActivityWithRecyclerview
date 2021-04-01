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
    comments.setOnClickListener(this);
  }

  // NO EXÉRCICÍO ANTERIOR EU NÃO ACABEI REALIZANDO UM SWITCH PERCORRENDO OS BOTÕES....
  // MAS ESTÁ AÍ AGORA....
  public void onClick(View view) {
    Intent intent = new Intent(ListsBotao.this, ListsActivity.class);
    switch (view.getId()) {
      case R.id.comments:
        intent.putExtra("op", "comments");
        startActivity(intent);
        break;
    }
  }

}