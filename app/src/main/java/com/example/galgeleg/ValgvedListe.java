package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValgvedListe extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Galgelogik logik = new Galgelogik();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valgved_liste);


        Intent liste = getIntent();

        logik.muligeOrd = liste.getStringArrayListExtra("ordliste");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, logik.muligeOrd);

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        setContentView(listView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View listView, int position, long id) {
        String valgtOrd = logik.muligeOrd.get(position);

        Intent spil = new Intent(getApplicationContext(), spil.class);
        spil.putExtra("ord", valgtOrd);
        startActivity(spil);
    }
}
