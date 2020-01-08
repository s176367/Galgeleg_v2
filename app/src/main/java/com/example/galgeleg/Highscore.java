package com.example.galgeleg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Highscore extends AppCompatActivity implements View.OnClickListener {
    private TextView highTitel;
    private TextView highScores;
    private Button resetHighscores, menu;
    ArrayList<Spiller> spillerList;
    String navn;
    int score;
    Boolean gemspil;
    String ranking = "";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_list);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        /**resetHighscores = findViewById(R.id.resetHigh);
        highTitel = findViewById(R.id.highTitel);
        highScores = findViewById(R.id.highscores);
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(this);
         resetHighscores.setOnClickListener(this);
         **/


        Gson gson = new Gson();
        String json = sharedPreferences.getString("highscore", "");

        Intent intent = getIntent();

        score = intent.getIntExtra("score", 0);
        navn = intent.getStringExtra("navn");
        gemspil = false;
        gemspil = intent.getBooleanExtra("gemspil", false);


        if (json.isEmpty()){
            spillerList = new ArrayList<>();
        }else {
            Type type = new TypeToken<List<Spiller>>(){}.getType();
            spillerList = gson.fromJson(json, type);
        }
        /**for (int i = 0; i < spillerList.size() ; i++) {

            ranking += spillerList.get(i).getNavn()+ ": "+ spillerList.get(i).getScore() + "\n";

            //highScores.setText(ranking);

        }**/
        if(gemspil){

            Spiller spiller = new Spiller(navn, score);
            spillerList.add(spiller);
            String json2 = gson.toJson(spillerList);
            editor.putString("highscore", json2);
            editor.commit();

        }
        HighscoreAdapter adapter = new HighscoreAdapter(getApplicationContext(), spillerList);


        recycler = findViewById(R.id.recyclerView);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        recycler.setAdapter(adapter);

    }




    public void onClick(View v) {
        if(v==menu){
            Intent i = new Intent(this, Menu.class);
            startActivity(i);
        }
    }
}
