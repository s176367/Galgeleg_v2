package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    Button regler, spil, highscore;
    Galgelogik logik = new Galgelogik();
    ProgressBar ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        regler = findViewById(R.id.button2);
        spil = findViewById(R.id.button);

        regler.setOnClickListener(this);
        spil.setOnClickListener(this);

        highscore = findViewById(R.id.highscore);
        highscore.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == spil) {

            class Asynk2 extends AsyncTask {
                @Override
                protected Object doInBackground(Object[] objects) {
                    try {
                        logik.hentOrdFraDr();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
                protected void onProgressUpdate(Object... progress) {
                    ring.setVisibility(View.VISIBLE);
                }

                @Override
                protected void onPostExecute(Object objects) {
                    Intent i = new Intent(getApplicationContext(), spil.class);
                    i.putExtra("ordliste", logik.muligeOrd);
                    startActivity(i);
                }
            }
            new Asynk2().execute();


        }
        if (v == regler) {
            Intent i = new Intent(this, Regler.class);
            startActivity(i);
        }
        if (v == highscore) {
            Intent i = new Intent(this, Highscore.class);
            startActivity(i);
        }
    }

}
