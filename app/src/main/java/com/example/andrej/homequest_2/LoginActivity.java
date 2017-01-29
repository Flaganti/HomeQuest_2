package com.example.andrej.homequest_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.Druzina;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity{

    ArrayList<Druzina> d;
    String[] emaili = new String[]{"andrej.531.knez","janez2020","tadej"};
    char[] vloga = new char[]{'P','C','P'};
    String[] gesla = new String[]{"admin1234","janez2020","1234"};
    ApplicationMy app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app = (ApplicationMy) getApplication();
        app.load();

    }
    public void Login(View v){
        boolean e = false;
        boolean g= false;
        EditText email= (EditText)findViewById(R.id.editText);
        EditText password= (EditText)findViewById(R.id.editText2);
        int i=0;
        for(String str: emaili){
            if(str.equals(email.getText().toString())){
                e=true;
                if(gesla[i].equals(password.getText().toString())){
                    g=true;
                    if(vloga[i]=='P'){
                        Intent intent = new Intent(LoginActivity.this, ActivityMain.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(LoginActivity.this, ActivityMainOtrok.class);
                        startActivity(intent);
                    }
                }
            }
            i++;
        }
        if(e==true && g ==false){
            Snackbar.make(v, "Napačno geslo", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(e==true && g ==true){

        }
        else{
            Snackbar.make(v, "Napačni e-mail ali geslo", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void NapolniOtrok(View v){
        EditText email= (EditText)findViewById(R.id.editText);
        EditText password= (EditText)findViewById(R.id.editText2);
        email.setText("janez2000");
        password.setText("janez2000");
    }
    public void NapolniStars(View v){
        EditText email= (EditText)findViewById(R.id.editText);
        EditText password= (EditText)findViewById(R.id.editText2);
        email.setText("andrej.531.knez");
        password.setText("admin1234");
    }
    public void ZazeniWeka(View v){
            Intent intent = new Intent(LoginActivity.this, Weka.class);
            startActivity(intent);
    }
    public void ustvariDruzino(View v){
        Intent intent = new Intent(LoginActivity.this, activityDruzina.class);
        startActivity(intent);

        //d = app.getAll().vrniDruzina();

    }

}
