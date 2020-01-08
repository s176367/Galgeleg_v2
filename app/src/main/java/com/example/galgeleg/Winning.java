package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Winning extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Spiller> spillere;
    TextView antalbrugteBogstaver;
    String navn;
    int score;
    Boolean gemspil;
    String ranking = "";
    Button menu, highscore;

    Galgelogik logik = new Galgelogik();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);

        highscore = findViewById(R.id.highscore);
        highscore.setOnClickListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        antalbrugteBogstaver = findViewById(R.id.antalforsøgInt);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("highscore", "");

        Intent intent = getIntent();

        score = intent.getIntExtra("score", 0);
        navn = intent.getStringExtra("navn");
        gemspil = false;
        gemspil = intent.getBooleanExtra("gemspil", false);

        if (json.isEmpty()) {
            spillere = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<Spiller>>() {}.getType();
            spillere = gson.fromJson(json, type);
            if (gemspil) {

                Spiller spiller = new Spiller(navn, score);
                spillere.add(spiller);
                String json2 = gson.toJson(spillere);
                editor.putString("highscore", json2);
                editor.commit();

            }

        }
        String scoreString = String.valueOf(score);
        antalbrugteBogstaver.setText(scoreString + "!");


    }

    @Override
    public void onClick(View v) {
        if (v==highscore){
            Intent i = new Intent(this, Highscore.class);
            i.putExtra("navn", navn);
            i.putExtra("score", logik.getAntalForkerteBogstaver());
            i.putExtra("gemspil", true);

            startActivity(i);
        }

    }
}