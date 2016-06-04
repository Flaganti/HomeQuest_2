package com.example.andrej.homequest_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.Naloga;

import java.util.ArrayList;

public class ActivityMainOtrok extends AppCompatActivity {

    private ApplicationMy app;
    private int oseba;
    private int selekcija;
    private ListView listView;
    private Custom_ListView_Adapter customListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main_otrok);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        app = (ApplicationMy) getApplication();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Naloga> nekaj = app.getAll().vrniPotrjene();
                int tocke=0;
                for(Naloga n:nekaj) tocke+=n.getTocke();
                Snackbar.make(view, "Zbral si: "+tocke+" točk", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        selekcija=1;
        customListViewAdapter = new Custom_ListView_Adapter(getApplicationContext(),R.layout.custom_listview_layout);
        listView = (ListView) findViewById(R.id.naloga2);
        ArrayList<Naloga> list = app.getAll().vrniNaloge();
        for(Naloga sa: list) customListViewAdapter.add(sa);
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(onListClick);
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Intent intent = new Intent(ActivityMainOtrok.this, ActivityNalogaClickOtrok.class);
            int[] in = new int[]{position,selekcija};
            intent.putExtra("opravilo",in);
            startActivity(intent);
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }
        else if(id == R.id.action_neopravljene){
            //TODO: odpri novi activiti
            selekcija=1;
            customListViewAdapter = new Custom_ListView_Adapter(getApplicationContext(),R.layout.custom_listview_layout);
            listView = (ListView) findViewById(R.id.naloga2);
            ArrayList<Naloga> list = app.getAll().vrniNaloge();
            for(Naloga sa: list) customListViewAdapter.add(sa);
            listView.setAdapter(customListViewAdapter);
            listView.setOnItemClickListener(onListClick);
            return true;
        }
        else if(id == R.id.action_opravljene){
            //TODO: odrpi novi activiti
            selekcija=2;
            customListViewAdapter = new Custom_ListView_Adapter(getApplicationContext(),R.layout.custom_listview_layout);
            listView = (ListView) findViewById(R.id.naloga2);
            ArrayList<Naloga> list = app.getAll().vrniOpravljene();

            for(Naloga sa: list) customListViewAdapter.add(sa);
            listView.setAdapter(customListViewAdapter);
            listView.setOnItemClickListener(onListClick);
            return true;
        }
        else if(id == R.id.action_potrjene){
            //TODO: odrpi novi activiti
            selekcija=3;
            customListViewAdapter = new Custom_ListView_Adapter(getApplicationContext(),R.layout.custom_listview_layout);
            listView = (ListView) findViewById(R.id.naloga2);
            ArrayList<Naloga> list = app.getAll().vrniPotrjene();
            for(Naloga sa: list) customListViewAdapter.add(sa);
            listView.setAdapter(customListViewAdapter);
            listView.setOnItemClickListener(onListClick);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume(){
        super.onResume();
        super.onResume();
        app.load();
        if(selekcija==1){
            customListViewAdapter = new Custom_ListView_Adapter(getApplicationContext(),R.layout.custom_listview_layout);
            listView = (ListView) findViewById(R.id.naloga2);
            ArrayList<Naloga> list = app.getAll().vrniNaloge();
            for(Naloga sa: list) customListViewAdapter.add(sa);
            listView.setAdapter(customListViewAdapter);
            listView.setOnItemClickListener(onListClick);
        }
        else if(selekcija==2){
            customListViewAdapter = new Custom_ListView_Adapter(getApplicationContext(),R.layout.custom_listview_layout);
            listView = (ListView) findViewById(R.id.naloga2);
            ArrayList<Naloga> list = app.getAll().vrniOpravljene();
            for(Naloga sa: list) customListViewAdapter.add(sa);
            listView.setAdapter(customListViewAdapter);
            listView.setOnItemClickListener(onListClick);
        }
        else {
            customListViewAdapter = new Custom_ListView_Adapter(getApplicationContext(), R.layout.custom_listview_layout);
            listView = (ListView) findViewById(R.id.naloga2);
            ArrayList<Naloga> list = app.getAll().vrniPotrjene();
            for (Naloga sa : list) customListViewAdapter.add(sa);
            listView.setAdapter(customListViewAdapter);
            listView.setOnItemClickListener(onListClick);
        }
    }

}
