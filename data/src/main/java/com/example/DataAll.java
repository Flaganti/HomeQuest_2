package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrej-PC on 30. 05. 2016.
 */

public class DataAll {
    ArrayList<Naloga> naloge;


    public ArrayList<String> opravila;

    public DataAll() {
        this.naloge = new ArrayList<>();
        this.opravila = new ArrayList<>();
    }

    public void dodaj(Naloga n) {
        this.naloge.add(n);
    }
    public void dodaj(String s){this.opravila.add(s);}

    public ArrayList<Naloga> vrniVse() {
        return this.naloge;
    }

    public ArrayList<Naloga> isci(String nekaj) {
        ArrayList<Naloga> najdeni = new ArrayList<>();
        for (int i = 0; i < naloge.size(); i++) {
            if (naloge.get(i).getName().equals(nekaj)) {
                najdeni.add(naloge.get(i));
            }
        }
        return najdeni;
    }

    public static DataAll napolni(){
        DataAll all = new DataAll();
        all.dodaj(new Naloga("Posoda", "", new Date(), 15));
        all.dodaj(new Naloga("Posoda", "", new Date(), 15));
        all.dodaj(new Naloga("Perilo", "Daj perilo prat", new Date(), 15));
        all.dodaj(new Naloga("Tla", "pometi tla", new Date(), 15));
        all.dodaj(new Naloga("Perilo", "Zlikaj perilo", new Date(), 15));
        all.dodaj(new Naloga("Posoda", "pospravi posodo", new Date(), 15));
        all.dodaj(new Naloga("Posoda", "Daj posodo v pomivalni stroj", new Date(), 15));
        all.dodaj(new Naloga("Dvorišče", "Pospravi dvorišče", new Date(), 115));
        all.dodaj(new Naloga("Perilo", "Daj perilo prat", new Date(), 15));
        all.dodaj(new Naloga("Perilo", "Zlikaj perilo", new Date(), 15));
        all.dodaj(new Naloga("Selitev", "Pomagaj Janezu pri selitvi", new Date(),300));
        all.dodaj("Posoda");
        all.dodaj("Tla");
        all.dodaj("Perilo");
        all.dodaj("Dvorišče");
        all.dodaj("Živali");
        all.dodaj("Hrana");
        return all;
    }
    @Override
    public String toString() {
        return "DataAll{" + "naloge=" + naloge + "}";
    }
}
