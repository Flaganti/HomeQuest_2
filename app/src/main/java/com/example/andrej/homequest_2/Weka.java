package com.example.andrej.homequest_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;

public class Weka extends AppCompatActivity {
    private ApplicationMy app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weka);

    }
    public void Weka(View v) throws Exception{
        BufferedReader breader = null;
        File desc  = new File(this.getExternalFilesDir("Podatki"),"naloge.arff");
        FileReader reader = new FileReader(desc.getAbsolutePath());
        breader = new BufferedReader(reader);
        Instances train = new Instances(breader);
        train.setClassIndex(train.numAttributes() - 1);
        breader.close();
        IBk iBk = new IBk();
        iBk.buildClassifier(train);
        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(iBk, train, 10, new Random(1));

        TextView txt = (TextView) findViewById(R.id.txtWeka);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setText(eval.toSummaryString("\nResults\n======\n",true)+"\n"+eval.fMeasure(1)+"  "+eval.precision(1)+"  "+eval.recall(1));
    }
}
