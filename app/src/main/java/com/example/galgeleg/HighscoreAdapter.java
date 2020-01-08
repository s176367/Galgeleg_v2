package com.example.galgeleg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HighscoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context cntx;
    ArrayList<Spiller> spillerListe;

    HighscoreAdapter (Context cntx, ArrayList<Spiller> spillerListe){
       this.cntx = cntx;
       this.spillerListe = spillerListe;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(cntx).inflate(R.layout.highscoreview, null);
        return new RecyclerView.ViewHolder(itemView) {};

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView textViewNavn = holder.itemView.findViewById(R.id.highscoreName);
        TextView textViewScore = holder.itemView.findViewById(R.id.highscoreScore);
        Button resetHighscore = holder.itemView.findViewById(R.id.resetHighscore);

        String scoreString = Integer.toString(spillerListe.get(position).getScore());
        textViewNavn.setText(spillerListe.get(position).getNavn());
        textViewScore.setText(scoreString);

    }

    @Override
    public int getItemCount() {
        return spillerListe.size();
    }
}

