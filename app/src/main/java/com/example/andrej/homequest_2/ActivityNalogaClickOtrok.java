package com.example.andrej.homequest_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Naloga;

import java.io.File;
import java.io.FileOutputStream;

public class ActivityNalogaClickOtrok extends AppCompatActivity {
    private ApplicationMy app;
    ImageView img;
    Naloga naloga;
    private int[] pozicija;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_naloga_click_otrok);

        app = (ApplicationMy) getApplication();
        TextView txtview = (TextView)findViewById(R.id.spinner2);
        TextView opis = (TextView) findViewById(R.id.etOpis2);
        TextView tocke = (TextView) findViewById(R.id.etTokce2);
        img = (ImageView) findViewById(R.id.imgViewSlika);
        Bitmap btmimg;
        File dest;

        if(getIntent().getIntArrayExtra("opravilo")!=null) {
            pozicija = getIntent().getIntArrayExtra("opravilo");
            Button btnShrani;

            switch (pozicija[1]){
                case 1: //Neopravljena
                    btnShrani = (Button) findViewById(R.id.btnShrani2);
                    btnShrani.setVisibility(View.VISIBLE);
                    naloga = app.getAll().vrniNaloge().get(pozicija[0]);
                    opis.setText(naloga.getOpis());
                    tocke.setText(naloga.getTocke().toString());
                    txtview.setText(naloga.getName());
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            // start the image capture Intent
                            startActivityForResult(intent,10);
                        }
                    });
                    dest = new File(this.getExternalFilesDir("Podatki"), ""
                            + naloga.getSlika_pot());
                    if(dest.exists()){
                        btmimg = BitmapFactory.decodeFile(dest.getAbsolutePath());
                        img.setImageBitmap(btmimg);
                    }
                    break;
                case 2: //Opravljena
                    btnShrani = (Button) findViewById(R.id.btnShrani2);
                    btnShrani.setVisibility(View.GONE);
                    naloga = app.getAll().vrniOpravljene().get(pozicija[0]);
                    opis.setText(naloga.getOpis());
                    tocke.setText(naloga.getTocke().toString());
                    txtview.setText(naloga.getName());
                    dest = new File(this.getExternalFilesDir("Podatki"), ""
                            + naloga.getSlika_pot());
                    if(dest.exists()) {
                        btmimg = BitmapFactory.decodeFile(dest.getAbsolutePath());
                        img.setImageBitmap(btmimg);
                    }

                    break;
                case 3: //Potrjena
                    btnShrani = (Button) findViewById(R.id.btnShrani2);
                    btnShrani.setVisibility(View.GONE);
                    naloga = app.getAll().vrniPotrjene().get(pozicija[0]);
                    opis.setText(naloga.getOpis()+" "+naloga.getSlika_pot());
                    tocke.setText(naloga.getTocke().toString());
                    txtview.setText(naloga.getName());
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
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == 10 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(photo);

            String filename =naloga.getDatum_objave().toString()+"slika"+naloga.getName();
            app.getAll().vrniNaloge().get(pozicija[0]).setSlika_pot(filename);
            File dest = new File(this.getExternalFilesDir("Podatki"), ""
                    + filename);
            try {
                FileOutputStream out = new FileOutputStream(dest);
                photo.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Nekaj:", "someOtherMethod()", e);
            }
            app.save();
        }
    }
}
