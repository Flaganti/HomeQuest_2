package com.example.andrej.homequest_2;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Naloga;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Date;

public class ActivityMainOtrok extends AppCompatActivity {

    private static final String DEBUG_TAG = "TAG:";
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
        getMenuInflater().inflate(R.menu.menu_activity_main2, menu);
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
        else if(id==R.id.action_qrcode){
            int cameraId = -1;
            int stevilo = Camera.getNumberOfCameras();
            for (int i = 0; i < stevilo; i++) {
                Camera.CameraInfo info = new Camera.CameraInfo();
                Camera.getCameraInfo(i, info);
                if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    Log.d(DEBUG_TAG, "Camera found");
                    cameraId = i;
                    break;
                }
            }
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("QR code scanner");
            integrator.setCameraId(cameraId);  // Use a specific camera of the device

            integrator.initiateScan();

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
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                String[] str = result.getContents().split("_");
                app.getAll().vrniPotrjene().add(new Naloga(str[0],str[1],new Date(), Integer.parseInt(str[2]), Integer.parseInt(str[3])));
                app.save();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
