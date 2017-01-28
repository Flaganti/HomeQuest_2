package com.example.andrej.homequest_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Naloga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class ActivityNalogaClick extends AppCompatActivity {
    private ApplicationMy app;
    private int[] pozicija;
    //TODO: Searilizacija
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_naloga_click);

        app = (ApplicationMy) getApplication();
        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.spinner_opravila,android.R.layout.simple_spinner_item);
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
        boolean shrani = false;
        Button button = (Button) v;
        EditText opis = (EditText) findViewById(R.id.etOpis);
        EditText tocke = (EditText) findViewById(R.id.etTokce);
        if(button.getText().toString().equals("Potrdi")) {
            Naloga n = app.getAll().vrniOpravljene().get(pozicija[0]);
            app.getAll().vrniOpravljene().remove(pozicija[0]);
            app.getAll().dodaj_potrjene(n);
            Snackbar.make(v, "Naloga je potrjena", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            shrani=true;

        }
        else if(button.getText().toString().equals("Shrani")){

            try {
                if(weka_test()!=0){
                    shrani=true;
                    app.getAll().dodaj(new Naloga(((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString(),opis.getText().toString(),new Date(), Integer.parseInt(tocke.getText().toString()),((Spinner)findViewById(R.id.spinner)).getSelectedItemPosition()));
                    Snackbar.make(v, "Naloga je bila dodana", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
            catch (Exception e){
                Log.d("Smth","smth");
            }


        }
        else{
            app.getAll().vrniNaloge().remove(pozicija[0]);
            Snackbar.make(v, "Naloga je zbrisana", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            shrani=true;
            app.getAll().vrniNaloge().remove(pozicija);
        }

        if(shrani){
            app.save();
            finish();
        }
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
    public double weka_test()throws Exception{


        double starostD = 14;
        double tockeD = Double.parseDouble(((EditText)findViewById(R.id.etTokce)).getText().toString());

        String bivStr = "hisa";//biv.getSelectedItem().toString(); //TODO: beri iz google spreadsheeta datum rojstva otroka
        String katStr = "hisno";//kat.getSelectedItem().toString();//TODO: beri iz google spreadsheeta ko bo pravilno deloval
        String podStr = ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
        podStr = podStr.toLowerCase();
        //zaganje_drv,zlaganje_drv,pomivanje_avta
        if(podStr.equals("živali")){
            podStr="zivali";
        }
        else if(podStr.equals("dvorišče")){
            podStr="dvorisce";
        }
        else if(podStr.equals("zaganje drv")){
            podStr="zaganje_drv";
        }
        else if(podStr.equals("zlaganje drv")){
            podStr="zlaganje_drv";
        }
        else if(podStr.equals("pomivanje avta")){
            podStr="pomivanje_avta";
        }

        String spolStr = "M"; //TODO beri spol iz google spreadsheeta! (ali ga odstrani)

        BufferedReader breader = null;
        File desc  = new File(this.getExternalFilesDir("Podatki"),"naloge.arff");
        FileReader reader = new FileReader(desc.getAbsolutePath());
        breader = new BufferedReader(reader);
        Instances train = new Instances(breader);
        breader.close();

        train.setClassIndex(train.numAttributes() - 1);

        IBk iBk = new IBk();
        iBk.buildClassifier(train);
        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(iBk, train, 10, new Random(1));

        Attribute starost = new Attribute("Starost");

        FastVector fvbiv= new FastVector(2);
        fvbiv.addElement("blok");
        fvbiv.addElement("hisa");
        Attribute bivalisce = new Attribute("Bivalisce",fvbiv);

        FastVector fvkat= new FastVector(3);
        fvkat.addElement("hisno");
        fvkat.addElement("terensko");
        fvkat.addElement("pomoc");
        Attribute kategorija = new Attribute("Kategorija",fvkat);

        FastVector fvpod= new FastVector(8);
        fvpod.addElement("posoda");
        fvpod.addElement("prah");
        fvpod.addElement("perilo");
        fvpod.addElement("smeti");
        fvpod.addElement("zaganje_drv");
        fvpod.addElement("zlaganje_drv");
        fvpod.addElement("pomivanje_avta");
        fvpod.addElement("tla");
        fvpod.addElement("dvorisce");
        fvpod.addElement("zivali");
        fvpod.addElement("hrana");
        Attribute podkategorija = new Attribute("Podkategorija",fvpod);
        Attribute tocke = new Attribute("Tocke");

        FastVector fvspol= new FastVector(2);
        fvspol.addElement("M");
        fvspol.addElement("F");
        Attribute spol = new Attribute("Spol",fvspol);

        weka.core.FastVector fvClassVal = new FastVector(3);
        fvClassVal.addElement("slaba");
        fvClassVal.addElement("dobra");
        fvClassVal.addElement("odlicna");
        Attribute Class = new Attribute("Izbira",fvClassVal);

        FastVector fvWekaAttributes = new FastVector(7);
        fvWekaAttributes.addElement(starost);
        fvWekaAttributes.addElement(tocke);
        fvWekaAttributes.addElement(bivalisce);
        fvWekaAttributes.addElement(podkategorija);
        fvWekaAttributes.addElement(kategorija);
        fvWekaAttributes.addElement(spol);
        fvWekaAttributes.addElement(Class);

        Instances dataset = new Instances("whatever",fvWekaAttributes,0);
        Instance instanca = new DenseInstance(6);
        instanca.setValue(starost,starostD);
        instanca.setValue(tocke,tockeD);
        instanca.setValue(bivalisce,bivStr);
        instanca.setValue(podkategorija,podStr);
        instanca.setValue(kategorija,katStr);
        instanca.setValue(spol,spolStr);

        dataset.add(instanca);
        dataset.setClassIndex(dataset.numAttributes()-1);


        //TextView txt = (TextView) findViewById(R.id.txtWeka);
        //txt.setMovementMethod(new ScrollingMovementMethod());
        double nekaj = iBk.classifyInstance(dataset.instance(0));
        String[] izbira = new String[]{"Prosim izberite boljše parameter. Naloga je ocenjena perslabo, da bi jo dodali","Dobro","Odlično"};
        //txt.setText(izbira[(int)nekaj].toString());
        Toast.makeText(this, izbira[(int)nekaj].toString(),
                Toast.LENGTH_LONG).show();
        return nekaj;
    }



}
