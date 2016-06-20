package com.example.andrej.homequest_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Naloga;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrej-PC on 31. 05. 2016.
 */

public class Custom_ListView_Adapter extends ArrayAdapter {
    List list = new ArrayList();
    private ApplicationMy app;
    boolean slika_ja_ne;
    int pogled;


    public Custom_ListView_Adapter(Context context, int resource, Activity activity,boolean slika,int pogled) {
        super(context, resource);
        app = (ApplicationMy) activity.getApplication();
        slika_ja_ne = slika;
        this.pogled = pogled;
    }
    static class DataHandler{
        TextView naloga;
        ImageView slika;

    }
    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount(){
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    public void setItem(int position, Naloga naloga){
        list.add(position,naloga);
        list.remove(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        DataHandler handler;
        if(convertView ==null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_listview_layout,parent,false);
            handler = new DataHandler();
            handler.naloga = (TextView)row.findViewById(R.id.view_starsi_pogled_textview);
            handler.slika = (ImageView)row.findViewById(R.id.view_starsi_pogled_opravljeno);

            row.setTag(handler);
        }
        else {
            handler = (DataHandler)row.getTag();
        }
        final Naloga nalogag;
        nalogag=(Naloga)this.getItem(position);

        ImageView slika = (ImageView)row.findViewById(R.id.view_starsi_pogled_opravljeno);
        slika.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(slika_ja_ne) {
                    if (pogled == 1) {
                        app.getAll().vrniNaloge().remove(position);
                        list.remove(position);
                        app.save();
                        Custom_ListView_Adapter.this.notifyDataSetChanged();
                    }
                    else if (pogled==2){
                        Naloga nal = app.getAll().vrniOpravljene().get(position);
                        app.getAll().vrniOpravljene().remove(position);
                        app.getAll().vrniPotrjene().add(nal);
                        list.remove(position);
                        app.save();
                        Custom_ListView_Adapter.this.notifyDataSetChanged();
                    }
                }
            }
        });

        if(slika_ja_ne) {
            if (pogled == 1) {
                handler.slika.setImageResource(getContext().getResources().getIdentifier("android:drawable/ic_menu_delete", null, null));
            }
            else if(pogled==2){handler.slika.setImageResource(getContext().getResources().getIdentifier("android:drawable/checkbox_on_background", null, null));}
        }
        handler.naloga.setText(nalogag.getName());
        return row;
    }

}
