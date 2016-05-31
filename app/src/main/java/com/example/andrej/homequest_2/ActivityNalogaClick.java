package com.example.andrej.homequest_2;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.DataAll;
import com.example.Naloga;

import java.util.List;

public class ActivityNalogaClick extends AppCompatActivity {
    private ApplicationMy app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_naloga_click);
        app = (ApplicationMy) getApplication();
        List<String> stringArray = app.getAll().opravila;
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if(getIntent().getStringExtra("opravilo")!=null){
            String pozicija = getIntent().getStringExtra("opravilo");
            Naloga naloga = app.getAll().vrniVse().get(Integer.parseInt(pozicija));
            EditText opis = (EditText)findViewById(R.id.etOpis);
            EditText tocke = (EditText) findViewById(R.id.etTokce);
            opis.setText(naloga.getOpis());
            tocke.setText(naloga.getTocke().toString());
        }
    }

}
