package com.example;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Andrej-PC on 30. 05. 2016.
 */

public class DataAll {
    ArrayList<Naloga> naloge;
    ArrayList<Naloga> naloge_opravljene;
    ArrayList<Naloga> naloge_potrjene;
    ArrayList<Trgovina> trgovina;


    public ArrayList<String> opravila;

    public DataAll() {
        this.naloge = new ArrayList<>();
        this.naloge_potrjene = new ArrayList<>();
        this.naloge_opravljene = new ArrayList<>();
        this.opravila = new ArrayList<>();
        this.trgovina=new ArrayList<>();
    }

    public void dodaj(Naloga n) {
        this.naloge.add(n);
    }
    public void dodaj_opraljene(Naloga n){this.naloge_opravljene.add(n);}
    public void dodaj_potrjene(Naloga n){this.naloge_potrjene.add(n);}
    public void dodaj_izdelek(Trgovina n){this.trgovina.add(n);}

    public void dodaj(String s){this.opravila.add(s);}

    public ArrayList<Naloga> vrniNaloge() {
        return this.naloge;
    }
    public ArrayList<Naloga> vrniOpravljene(){
        return  this.naloge_opravljene;
    }
    public ArrayList<Naloga> vrniPotrjene(){
        return  this.naloge_potrjene;
    }
    public ArrayList<Trgovina> vrniTrgovina(){return this.trgovina;}

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
        all.dodaj(new Naloga("Posoda", "", new Date(), 15,0));
        all.dodaj(new Naloga("Posoda", "", new Date(), 15,0));
        all.dodaj(new Naloga("Perilo", "Daj perilo prat", new Date(), 15,2));
        all.dodaj(new Naloga("Tla", "pometi tla", new Date(), 15,1));
        all.dodaj(new Naloga("Perilo", "Zlikaj perilo", new Date(), 15,2));
        all.dodaj(new Naloga("Posoda", "pospravi posodo", new Date(), 15,0));
        all.dodaj(new Naloga("Posoda", "Daj posodo v pomivalni stroj", new Date(), 15,0));
        all.dodaj(new Naloga("Dvorišče", "Pospravi dvorišče", new Date(), 115,3));
        all.dodaj(new Naloga("Perilo", "Daj perilo prat", new Date(), 15,2));
        all.dodaj(new Naloga("Perilo", "Zlikaj perilo", new Date(), 15,2));
        all.dodaj("Posoda");
        all.dodaj("Tla");
        all.dodaj("Perilo");
        all.dodaj("Dvorišče");
        all.dodaj("Živali");
        all.dodaj("Hrana");
        all.dodaj_opraljene(new Naloga("Perilo", "Pomagaj Janezu pri selitvi", new Date(),300,2));
        all.dodaj_opraljene(new Naloga("Posoda", "Pomagaj Janezu pri selitvi", new Date(),300,0));
        all.dodaj_opraljene(new Naloga("Tla", "Pomagaj Janezu pri selitvi", new Date(),300,1));
        all.dodaj_opraljene(new Naloga("Dvorišče", "Pomagaj Janezu pri selitvi", new Date(),300,3));

        all.dodaj_izdelek(new Trgovina("Pokemon karte",50000));
        all.dodaj_izdelek(new Trgovina("Igralna konzola Nintendo Switch",150000));
        all.dodaj_izdelek(new Trgovina("1x 10eur zepnine",50000));
        all.dodaj_izdelek(new Trgovina("Yu-gi-oh karte",50000));
        return all;
    }
    @Override
    public String toString() {
        return "DataAll{" + "naloge=" + naloge + "}";
    }
}
