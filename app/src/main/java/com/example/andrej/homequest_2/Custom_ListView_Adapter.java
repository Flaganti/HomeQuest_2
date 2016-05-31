package com.example.andrej.homequest_2;

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


    public Custom_ListView_Adapter(Context context, int resource) {
        super(context, resource);
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        Naloga nalogag;
        nalogag=(Naloga)this.getItem(position);

        handler.slika.setImageResource(getContext().getResources().getIdentifier("android:drawable/ic_dialog_dialer", null, null));
        handler.naloga.setText(nalogag.getName());
        return row;
    }
}
