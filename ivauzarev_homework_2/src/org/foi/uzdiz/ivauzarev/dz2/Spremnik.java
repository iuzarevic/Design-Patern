/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2;

import org.foi.uzdiz.ivauzarev.dz2.helpers.GeneralBuilder;

/**
 *
 * @author Ivan
 */
public class Spremnik {

    private final String naziv;
    private final int vrsta;
    private final int naBrojMalih;
    private final int naBrojSrednjih;
    private final int naBrojVelikih;
    private final int nosivost;

    public String getNaziv() {
        return naziv;
    }

    public int getVrsta() {
        return vrsta;
    }

    public int getNaBrojMalih() {
        return naBrojMalih;
    }

    public int getNaBrojSrednjih() {
        return naBrojSrednjih;
    }

    public int getNaBrojVelikih() {
        return naBrojVelikih;
    }

    public int getNosivost() {
        return nosivost;
    }

    private Spremnik(Spremnik.Builder builder) {
        this.naziv = builder.naziv;
        this.vrsta = builder.vrsta;
        this.naBrojMalih = builder.naBrojMalih;
        this.naBrojSrednjih = builder.naBrojSrednjih;
        this.naBrojVelikih = builder.naBrojVelikih;
        this.nosivost = builder.nosivost;
    }

    public static class Builder implements GeneralBuilder {

        private String naziv;
        private int vrsta;
        private int naBrojMalih;
        private int naBrojSrednjih;
        private int naBrojVelikih;
        private int nosivost;

        public Builder() {

        }

        public Spremnik build() {
            return new Spremnik(this);
        }

        @Override
        public Builder addDataFromFile(String[] data) {
            this.naziv = data[0];
            this.vrsta = Integer.parseInt(data[1]);
            this.naBrojMalih = Integer.parseInt(data[2]);
            this.naBrojSrednjih = Integer.parseInt(data[3]);
            this.naBrojVelikih = Integer.parseInt(data[4]);
            this.nosivost = Integer.parseInt(data[5]);
            return this;
        }

    }

}
