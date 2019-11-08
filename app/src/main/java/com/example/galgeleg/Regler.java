package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Regler extends AppCompatActivity implements View.OnClickListener {
Button tilbage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regler);
        tilbage = findViewById(R.id.tilbage);
        tilbage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    if(v==tilbage){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
    }
}
