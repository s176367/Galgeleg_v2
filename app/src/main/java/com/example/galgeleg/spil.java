package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;


public class spil extends AppCompatActivity implements View.OnClickListener {
    EditText gatfelt, navn;
    ImageView galge;
    Button gatknap, tilbage, gem;
    TextView gattekst, brugtebogstaver, gætbar, antalforsøg;
    ImageView hang;
    ArrayList<String> score = new ArrayList<>();

    LottieAnimationView rigtigt, forkert;

    Galgelogik logik = new Galgelogik();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        Intent intent = getIntent();

        logik.muligeOrd.clear();

        if (intent.getStringArrayListExtra("ordliste")!=null){
            logik.muligeOrd = intent.getStringArrayListExtra("ordliste");
        }else{
            String ordet = intent.getStringExtra("ord");
            logik.muligeOrd.add(ordet);
        }


        logik.nulstil();


        System.out.println(logik.muligeOrd);
        System.out.println(logik.getOrdet());
        gatfelt = findViewById(R.id.gatText);
        gatfelt.getText();

        navn = findViewById(R.id.highscoreName);
        navn.getText();
        navn.setVisibility(View.INVISIBLE);

        galge = findViewById(R.id.imageView);

        gatknap = findViewById(R.id.gat);
        gatknap.setOnClickListener(this);

        tilbage = findViewById(R.id.button3);
        tilbage.setOnClickListener(this);

        gem = findViewById(R.id.gemspil);
        gem.setOnClickListener(this);
        gem.setVisibility(View.INVISIBLE);

        gætbar = findViewById(R.id.gætbar);
        gætbar.getText();

        hang = findViewById(R.id.imageView);

        brugtebogstaver = findViewById(R.id.brugtebogstaver);
        brugtebogstaver.getText();
        gætbar.setText(logik.getSynligtOrd());

        System.out.println(logik.getOrdet());

        rigtigt = findViewById(R.id.thumbs_up);
        rigtigt.setVisibility(View.INVISIBLE);

        forkert = findViewById(R.id.thumbs_down);
        forkert.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View v) {

        if (v == gem) {
            Intent i = new Intent(this, Winning.class);
            String gemNavn = navn.getText().toString();
            i.putExtra("navn", gemNavn);
            i.putExtra("score", logik.getAntalForkerteBogstaver());
            i.putExtra("gemspil", true);

            startActivity(i);
        }

        String gæt = gatfelt.getText().toString();
        if (v == tilbage) {
            Intent i = new Intent(this, Menu.class);
            startActivity(i);
        }


        if (v == gatknap) {
            for (int i = 0; i < 6; i++)
                logik.gætBogstav(gæt);
            logik.erSpilletVundet();
            if (logik.erSpilletVundet()) {
                gatknap.setVisibility(View.INVISIBLE);
                gem.setVisibility(View.VISIBLE);
                navn.setVisibility(View.VISIBLE);
                gatfelt.setVisibility(View.INVISIBLE);
                galge.setVisibility(View.INVISIBLE);






            } else if (logik.erSpilletTabt()) {

            }
        }

        if (logik.erSidsteBogstavKorrekt()) {
            gætbar.setText(logik.getSynligtOrd());
            brugtebogstaver.setText(logik.getBrugteBogstaver().toString());
            rigtigt.setVisibility(View.VISIBLE);
            rigtigt.playAnimation();

            class Asynctask1 extends AsyncTask {

                @Override
                protected Object doInBackground(Object[] objects) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    rigtigt.setVisibility(View.INVISIBLE);
                }
            } new Asynctask1().execute();

        } else {
            brugtebogstaver.setText(logik.getBrugteBogstaver().toString());
            forkert.setVisibility(View.VISIBLE);
            forkert.playAnimation();

            switch (logik.getAntalForkerteBogstaver()) {
                case 0:
                    hang.setImageResource(R.drawable.galge);
                    break;
                case 1:
                    hang.setImageResource(R.drawable.forkert1);
                    break;
                case 2:
                    hang.setImageResource(R.drawable.forkert2);
                    break;
                case 3:
                    hang.setImageResource(R.drawable.forkert3);
                    break;
                case 4:
                    hang.setImageResource(R.drawable.forkert4);
                    break;
                case 5:
                    hang.setImageResource(R.drawable.forkert5);
                    break;
                case 6:
                    logik.erSpilletTabt();
                    /*hang.setImageResource(R.drawable.forkert6);
                    gatfelt.setText("Du tabte!");
                    gattekst.setText("Spillet er slut");
                    gatknap.setText("Nustil spillet");
                    hang.setImageResource(R.drawable.spilslut);
                    gattekst.setText("Tryk på tilbage for at komme til hovedmenuen");*/
                    Intent loser = new Intent(this, Loser.class);
                    loser.putExtra("ordet", logik.getOrdet());
                    startActivity(loser);
                    //Intentputextra.getordet();

                    break;


            }
            class Asynctask2 extends AsyncTask{

                @Override
                protected Object doInBackground(Object[] objects) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    forkert.setVisibility(View.INVISIBLE);
                }
            } new Asynctask2().execute();

        }
        gatfelt.setText(null);


    }
}





