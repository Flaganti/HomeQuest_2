package com.example;

import java.util.Date;

/**
 * Created by Andrej-PC on 30. 05. 2016.
 */

public class Naloga {
    String name;
    String opis;
    Date datum_objave;
    Date datum_do;
    Integer tocke;
    int namePos;

    public Naloga(){
        datum_objave = new Date();
    }
    public Naloga(String name,String opis,Date datum_do,Integer tocke,int namePos){
        datum_objave = new Date();
        this.name = name;
        this.opis = opis;
        this.datum_do=datum_do;
        this.tocke = tocke;
        this.namePos = namePos;
    }

    public Date getDatum_objave() {
        return datum_objave;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTocke() {
        return tocke;
    }

    public void setTocke(int tocke) {
        this.tocke = tocke;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatum_do() {
        return datum_do;
    }

    public void setDatum_do(Date datum_do) {
        this.datum_do = datum_do;
    }

    public int getNamePos() {
        return namePos;
    }

    public void setNamePos(int namePos) {
        this.namePos = namePos;
    }
}
