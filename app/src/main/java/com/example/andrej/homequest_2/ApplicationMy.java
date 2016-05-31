package com.example.andrej.homequest_2;

import android.app.Application;
import android.os.Environment;

import com.example.DataAll;

/**
 * Created by Andrej-PC on 31. 05. 2016.
 */

public class ApplicationMy extends Application {
    private DataAll all;
    @Override
    public void onCreate(){
        super.onCreate();
        all = DataAll.napolni();
    }
    public DataAll getAll(){
        return all;
    }
    private void setAll(DataAll all){
        this.all=all;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
