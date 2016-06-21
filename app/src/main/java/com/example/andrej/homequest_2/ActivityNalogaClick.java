package com.example.andrej.homequest_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.Naloga;

import java.io.File;
import java.util.Date;
import java.util.List;

public class ActivityNalogaClick extends AppCompatActivity {
    private ApplicationMy app;
    private int[] pozicija;
    //TODO: Searilizacija
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
        tocke.setText("0");
        File dest;
        ImageView img = (ImageView) findViewById(R.id.imgViewSlika);
        Bitmap btmimg;
        if(getIntent().getIntArrayExtra("opravilo")!=null) {
            pozicija = getIntent().getIntArrayExtra("opravilo");
            Button btnShrani,btnPotrdi,btnZbrisi;
            Naloga naloga;
            switch (pozicija[1]){
                case 1: //Neopravljena
                    btnShrani = (Button) findViewById(R.id.btnShrani);
                    btnShrani.setVisibility(View.GONE);
                    btnPotrdi = (Button)findViewById(R.id.btnPotrdi);
                    btnPotrdi.setVisibility(View.GONE);
                    btnZbrisi = (Button)findViewById(R.id.btnZbrisi);
                    btnZbrisi.setVisibility(View.VISIBLE);
                    naloga = app.getAll().vrniNaloge().get(pozicija[0]);
                    opis.setText(naloga.getOpis());
                    tocke.setText(naloga.getTocke().toString());
                    spinner.setSelection(naloga.getNamePos());
                    break;
                case 2: //Opravljena
                    btnShrani = (Button) findViewById(R.id.btnShrani);
                    btnShrani.setVisibility(View.GONE);
                    btnPotrdi = (Button)findViewById(R.id.btnPotrdi);
                    btnPotrdi.setVisibility(View.VISIBLE);
                    btnZbrisi = (Button)findViewById(R.id.btnZbrisi);
                    btnZbrisi.setVisibility(View.GONE);
                    naloga = app.getAll().vrniOpravljene().get(pozicija[0]);
                    opis.setText(naloga.getOpis());
                    tocke.setText(naloga.getTocke().toString());
                    spinner.setSelection(naloga.getNamePos());
                    dest = new File(this.getExternalFilesDir("Podatki"), ""
                            + naloga.getSlika_pot());
                    if(dest.exists()) {
                        btmimg = BitmapFactory.decodeFile(dest.getAbsolutePath());
                        img.setImageBitmap(btmimg);
                    }
                    break;
                case 3: //Potrjena
                    btnShrani = (Button) findViewById(R.id.btnShrani);
                    btnShrani.setVisibility(View.GONE);
                    btnPotrdi = (Button)findViewById(R.id.btnPotrdi);
                    btnPotrdi.setVisibility(View.GONE);
                    btnZbrisi = (Button)findViewById(R.id.btnZbrisi);
                    btnZbrisi.setVisibility(View.GONE);
                    naloga = app.getAll().vrniPotrjene().get(pozicija[0]);
                    opis.setText(naloga.getOpis());
                    tocke.setText(naloga.getTocke().toString());
                    spinner.setSelection(naloga.getNamePos());
                    dest = new File(this.getExternalFilesDir("Podatki"), ""
                            + naloga.getSlika_pot());
                    if(dest.exists()) {
                        btmimg = BitmapFactory.decodeFile(dest.getAbsolutePath());
                        img.setImageBitmap(btmimg);
                    }
                    break;
                default:
            }

        }
        else{
            Button btnPotrdi = (Button)findViewById(R.id.btnPotrdi);
            btnPotrdi.setVisibility(View.GONE);
            Button btnZbrisi = (Button)findViewById(R.id.btnZbrisi);
            btnZbrisi.setVisibility(View.GONE);
        }
    }
    public void buttonOnClick(View v){
        Button button = (Button) v;
        EditText opis = (EditText) findViewById(R.id.etOpis);
        EditText tocke = (EditText) findViewById(R.id.etTokce);
        if(button.getText().toString().equals("Potrdi")) {
            Naloga n = app.getAll().vrniOpravljene().get(pozicija[0]);
            app.getAll().vrniOpravljene().remove(pozicija[0]);
            app.getAll().dodaj_potrjene(n);
            Snackbar.make(v, "Naloga je potrjena", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(button.getText().toString().equals("Shrani")){
            app.getAll().dodaj(new Naloga(((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString(),opis.getText().toString(),new Date(), Integer.parseInt(tocke.getText().toString()),((Spinner)findViewById(R.id.spinner)).getSelectedItemPosition()));
            Snackbar.make(v, "Naloga je bila dodana", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }
        else{
            app.getAll().vrniNaloge().remove(pozicija[0]);
            Snackbar.make(v, "Naloga je zbrisana", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            app.getAll().vrniNaloge().remove(pozicija);
        }
        app.save();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_naloga_click, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        EditText opis = (EditText) findViewById(R.id.etOpis);
        EditText tocke = (EditText) findViewById(R.id.etTokce);
        View view = findViewById(android.R.id.content);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit && pozicija[1]==1) {
            app.getAll().vrniNaloge().get(pozicija[0]).setOpis(opis.getText().toString());
            app.getAll().vrniNaloge().get(pozicija[0]).setTocke(Integer.parseInt(tocke.getText().toString()));
            app.getAll().vrniNaloge().get(pozicija[0]).setName(((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString());
            app.getAll().vrniNaloge().get(pozicija[0]).setNamePos(((Spinner)findViewById(R.id.spinner)).getSelectedItemPosition());
            Snackbar.make(view, "Spremembe so bile shranjene", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            app.save();
            return true;}


        return super.onOptionsItemSelected(item);
    }
}
