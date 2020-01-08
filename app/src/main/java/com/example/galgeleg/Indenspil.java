package com.example.galgeleg;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class Indenspil extends AppCompatActivity implements View.OnClickListener {

    Galgelogik logik = new Galgelogik();

    LottieAnimationView loading;

    ArrayList listeord = new ArrayList();

    Button liste, randomOrd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valg_indenspil);

        liste = findViewById(R.id.listeknap);
        liste.setOnClickListener(this);

        randomOrd = findViewById(R.id.randomordknap);
        randomOrd.setOnClickListener(this);

        //lottie er her brugt for animation
        //reference: https://lottiefiles.com/13453-loading
        loading = findViewById(R.id.loading);
        loading.setVisibility(View.INVISIBLE);


    }


    @Override
    public void onClick(View v) {
        if(v==randomOrd){
            loading.setVisibility(View.VISIBLE);
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
        if(v==liste){

            loading.setVisibility(View.VISIBLE);
            class Asynk3 extends AsyncTask {

                @Override
                protected Object doInBackground(Object[] objects) {
                    try{
                            logik.hentOrdFraDr();
                            listeord.add(logik.muligeOrd);
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onProgressUpdate(Object[] values) {
                    loading.setVisibility(View.VISIBLE);
                }

                @Override
                protected void onPostExecute(Object o) {
                Intent i = new Intent(getApplicationContext(), ValgvedListe.class);
                i.putExtra("ordliste", logik.muligeOrd);
                startActivity(i);
                }
            }new Asynk3().execute();
        }
    }
}
