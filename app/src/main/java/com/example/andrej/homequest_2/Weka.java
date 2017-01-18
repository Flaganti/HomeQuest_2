package com.example.andrej.homequest_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class Weka extends AppCompatActivity {
    private ApplicationMy app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weka);

        String[] bivArray = new String[] {"blok","hisa"};
        String[] katArray = new String[] {"hisno","terensko","pomoc"};
        String[] podkatArray = new String[] {"posoda","prah","perilo","smeti","zaganje_drv","zlaganje_drv","pomivanje_avta","tla"};
        String[] spol = new String[] {"M","F"};

        Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner)findViewById(R.id.spinner4);
        Spinner spinner5 = (Spinner)findViewById(R.id.spinner5);
        Spinner spinner6 = (Spinner)findViewById(R.id.spinner6);

        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, bivArray);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, katArray);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, podkatArray);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spol);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(adapter1);
        spinner4.setAdapter(adapter2);
        spinner5.setAdapter(adapter3);
        spinner6.setAdapter(adapter4);

    }
    public void Weka(View v) throws Exception{
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

        TextView txt = (TextView) findViewById(R.id.txtWeka);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setText(eval.toSummaryString("\nResults\n======\n",true)+"\n"+eval.fMeasure(1)+"  "+eval.precision(1)+"  "+eval.recall(1));
    }

    public void TestniPrimer(View v) throws Exception{

        EditText edStar = (EditText) findViewById(R.id.editStarost_Weka);
        EditText edTocke = (EditText) findViewById(R.id.editTocke_Weka);
        Spinner biv = (Spinner)findViewById(R.id.spinner3);
        Spinner kat = (Spinner)findViewById(R.id.spinner4);
        Spinner pod = (Spinner)findViewById(R.id.spinner5);
        Spinner spolS = (Spinner)findViewById(R.id.spinner6);

        double starostD = Double.parseDouble(edStar.getText().toString());
        double tockeD = Double.parseDouble(edTocke.getText().toString());

        String bivStr = biv.getSelectedItem().toString();
        String katStr = kat.getSelectedItem().toString();
        String podStr = pod.getSelectedItem().toString();
        String spolStr = spolS.getSelectedItem().toString();

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


        TextView txt = (TextView) findViewById(R.id.txtWeka);
        txt.setMovementMethod(new ScrollingMovementMethod());
        double nekaj = iBk.classifyInstance(dataset.instance(0));
        String[] izbira = new String[]{"Slabo","Dobro","Odlično"};
        txt.setText(izbira[(int)nekaj].toString());
    }
}
