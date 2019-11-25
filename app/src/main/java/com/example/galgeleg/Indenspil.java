package com.example.galgeleg;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Indenspil extends AppCompatActivity implements View.OnClickListener {

    Galgelogik logik = new Galgelogik();

    Button liste, randomOrd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valg_indenspil);

        liste = findViewById(R.id.listeknap);
        liste.setOnClickListener(this);

        randomOrd = findViewById(R.id.randomordknap);
        randomOrd.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if(v==randomOrd){

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
    }
}