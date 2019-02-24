/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3;

/**
 *
 * @author Ivan
 */
public class KonkretniSpremnik {

    static int counter = 0;
    private int id = 0;
    private String naziv;
    private int vrsta;
    private int nosivost;
    private float napunjenost = 0;

    public KonkretniSpremnik(String naziv, int vrsta, int nosivost, int napunjenost) {
        id = ++counter;
        this.naziv = naziv;
        this.vrsta = vrsta;
        this.nosivost = nosivost;
        this.napunjenost = napunjenost;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getNazivBroj() {
        int rezultat = -1;
        switch (naziv) {
            case "staklo":
                rezultat = 0;
                break;
            case "papir":
                rezultat = 1;
                break;
            case "metal":
                rezultat = 2;
                break;
            case "bio":
                rezultat = 3;
                break;
            case "mješano":
                rezultat = 4;
                break;
            default:
                rezultat = -1;
        }
        return rezultat;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrsta() {
        return vrsta;
    }

    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }

    public int getNosivost() {
        return nosivost;
    }

    public void setNosivost(int nosivost) {
        this.nosivost = nosivost;
    }

    public float getNapunjenost() {
        return napunjenost;
    }

    public void setNapunjenost(float napunjenost) {
        this.napunjenost += napunjenost;
    }

    public void isprazni() {
//        if(this.napunjenost <= napunjenost) {
//            this.napunjenost = 0;
//        }
//        this.napunjenost -= napunjenost;
        napunjenost = 0;
    }

    public int getId() {
        return id;
    }

}
