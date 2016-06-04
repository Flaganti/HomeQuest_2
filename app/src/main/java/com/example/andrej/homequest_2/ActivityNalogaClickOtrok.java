package com.example.andrej.homequest_2;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Naloga;

public class ActivityNalogaClickOtrok extends AppCompatActivity {
    private ApplicationMy app;
    private int[] pozicija;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_naloga_click_otrok);

        app = (ApplicationMy) getApplication();
        TextView txtview = (TextView)findViewById(R.id.spinner2);
        TextView opis = (TextView) findViewById(R.id.etOpis2);
        TextView tocke = (TextView) findViewById(R.id.etTokce2);
        if(getIntent().getIntArrayExtra("opravilo")!=null) {
            pozicija = getIntent().getIntArrayExtra("opravilo");
            Button btnShrani;
            Naloga naloga;
            switch (pozicija[1]){
                case 1: //Neopravljena
                    btnShrani = (Button) findViewById(R.id.btnShrani2);
                    btnShrani.setVisibility(View.VISIBLE);
                    naloga = app.getAll().vrniNaloge().get(pozicija[0]);
                    opis.setText(naloga.getOpis());
                    tocke.setText(naloga.getTocke().toString());
                    txtview.setText(naloga.getName());
                    break;
                case 2: //Opravljena
                    btnShrani = (Button) findViewById(R.id.btnShrani2);
                    btnShrani.setVisibility(View.GONE);
                    naloga = app.getAll().vrniOpravljene().get(pozicija[0]);
                    opis.setText(naloga.getOpis());
                    tocke.setText(naloga.getTocke().toString());
                    txtview.setText(naloga.getName());
                    break;
                case 3: //Potrjena
                    btnShrani = (Button) findViewById(R.id.btnShrani2);
                    btnShrani.setVisibility(View.GONE);
                    naloga = app.getAll().vrniPotrjene().get(pozicija[0]);
                    opis.setText(naloga.getOpis());
                    tocke.setText(naloga.getTocke().toString());
                    txtview.setText(naloga.getName());
                    break;
                default:
            }

        }
    }
    public void buttonOnClick(View v){
        Button button = (Button) v;
        if(button.getText().toString().equals("Opravi")) {
            Naloga n = app.getAll().vrniNaloge().get(pozicija[0]);
            app.getAll().vrniNaloge().remove(pozicija[0]);
            app.getAll().dodaj_opraljene(n);
            Snackbar.make(v, "Naloga je opravljena", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        app.save();
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
        View view = findViewById(android.R.id.content);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit && pozicija[1]==1) {
            //TODO: Slikaj in shrani sliko. Ko je slika shranjena lahko shrani prez, da se nalogo odda.
            Snackbar.make(view, "Spremembe so bile shranjene", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            app.save();
            return true;}


        return super.onOptionsItemSelected(item);
    }
}
