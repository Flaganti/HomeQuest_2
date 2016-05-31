package com.example.andrej.homequest_2;

import android.app.Application;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.DataAll;
import com.example.Naloga;

import java.util.Date;
import java.util.List;

public class ActivityNalogaClick extends AppCompatActivity {
    private ApplicationMy app;
    private String pozicija;
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
        EditText opis = (EditText) findViewById(R.id.etOpis);
        EditText tocke = (EditText) findViewById(R.id.etTokce);
        if(getIntent().getStringExtra("opravilo")!=null) {
            Button btnShrani = (Button)findViewById(R.id.btnShrani);
            btnShrani.setClickable(false);
            pozicija = getIntent().getStringExtra("opravilo");
            Naloga naloga = app.getAll().vrniVse().get(Integer.parseInt(pozicija));
            opis.setText(naloga.getOpis());
            tocke.setText(naloga.getTocke().toString());
            spinner.setSelection(naloga.getNamePos());
        }
        else{
            Button btnPotrdi = (Button)findViewById(R.id.btnPotrdi);
            btnPotrdi.setClickable(false);
            Button btnZbrisi = (Button)findViewById(R.id.btnZbrisi);
            btnZbrisi.setClickable(false);
        }
    }
    public void buttonOnClick(View v){
        Button button = (Button) v;
        EditText opis = (EditText) findViewById(R.id.etOpis);
        EditText tocke = (EditText) findViewById(R.id.etTokce);
        if(button.getText().toString().equals("Potrdi")) {
            app.getAll().vrniVse().get(Integer.parseInt(pozicija)).setOpis(opis.getText().toString());
            app.getAll().vrniVse().get(Integer.parseInt(pozicija)).setTocke(Integer.parseInt(tocke.getText().toString()));
            app.getAll().vrniVse().get(Integer.parseInt(pozicija)).setName(((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString());
            app.getAll().vrniVse().get(Integer.parseInt(pozicija)).setNamePos(((Spinner)findViewById(R.id.spinner)).getSelectedItemPosition());
        }
        else if(button.getText().toString().equals("Shrani")){
            app.getAll().dodaj(new Naloga(((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString(),opis.getText().toString(),new Date(), Integer.parseInt(tocke.getText().toString()),((Spinner)findViewById(R.id.spinner)).getSelectedItemPosition()));
        }
        else{
            app.getAll().vrniVse().remove(Integer.parseInt(pozicija));
        }
    }
}
