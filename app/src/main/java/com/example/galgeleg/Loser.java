package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Loser extends AppCompatActivity implements View.OnClickListener {
    String ordet;
    TextView getOrdet;
    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser);

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(this);

        TextView getOrdet = findViewById(R.id.getOrdet);

        Intent intentOrdet = getIntent();
        ordet = intentOrdet.getStringExtra("ordet");

        getOrdet.setText(ordet);

    }

    @Override
    public void onClick(View v) {
    if(v==menu){
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }
    }
}
