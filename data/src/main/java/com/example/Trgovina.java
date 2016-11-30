package com.example;

import java.util.Date;

/**
 * Created by Andrej-PC on 30. 11. 2016.
 */

public class Trgovina {
    String ime_izdelka;
    int cena_izdelka;
    String slika_pot;//če bo dodana slika
    Date datum_objave;

    public Trgovina(){datum_objave = new Date();}
    public Trgovina(String ime_izdelka,int cena_izdelka){
        datum_objave=new Date();
        this.ime_izdelka=ime_izdelka;
        this.cena_izdelka=cena_izdelka;
        this.slika_pot="";
    }

    public String getIme_izdelka() {
        return ime_izdelka;
    }

    public void setIme_izdelka(String ime_izdelka) {
        this.ime_izdelka = ime_izdelka;
    }

    public int getCena_izdelka() {
        return cena_izdelka;
    }

    public void setCena_izdelka(int cena_izdelka) {
        this.cena_izdelka = cena_izdelka;
    }

    public String getSlika_pot() {
        return slika_pot;
    }

    public void setSlika_pot(String slika_pot) {
        this.slika_pot = slika_pot;
    }

    public Date getDatum_objave() {
        return datum_objave;
    }

    public void setDatum_objave(Date datum_objave) {
        this.datum_objave = datum_objave;
    }

}
