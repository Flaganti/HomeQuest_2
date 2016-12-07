package com.example.andrej.homequest_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.Trgovina;

import java.util.ArrayList;

public class ActicityTrgovina extends AppCompatActivity {
    private int selekcija;
    private ApplicationMy app;
    private ListView listView;
    Custom_ListView_Adapter customListViewAdapter;
    ArrayAdapter<Trgovina> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acticity_trgovina);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app = (ApplicationMy) getApplication();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //customListViewAdapter = new Custom_ListView_Adapter(getApplicationContext(),R.layout.custom_listview_layout,this,true,1);
        adapter=new ArrayAdapter<Trgovina>(getApplicationContext(),android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.trgovina);
        ArrayList<Trgovina> list = app.getAll().vrniTrgovina();
        for(Trgovina sa: list) adapter.add(sa);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onListClick);
    }
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Snackbar.make(view,app.getAll().vrniTrgovina().get(position).getIme_izdelka().toString()+"\n"+app.getAll().vrniTrgovina().get(position).getCena_izdelka()+" tock", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            //TODO: TrgovinaClickActivity!
            /*Intent intent = new Intent(ActicityTrgovina.this, ActivityNalogaClick.class);
            int[] in = new int[]{position,selekcija};
            intent.putExtra("smth",in);
            startActivity(intent);*/
        }

    };
}
