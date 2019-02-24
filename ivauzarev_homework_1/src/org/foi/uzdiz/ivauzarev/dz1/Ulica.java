/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz1.helpers.GeneralBuilder;
import org.foi.uzdiz.ivauzarev.dz1.helpers.KorisnikCreator;
import org.foi.uzdiz.ivauzarev.dz1.helpers.UserFactory;
import org.foi.uzdiz.ivauzarev.dz1.helpers.UserTypes;

/**
 *
 * @author Ivan
 */
public class Ulica {

    private final String naziv;
    private final int brojMjesta;
    private final int maliUdio;
    private final int srednjiUdio;
    private final int velikiUdio;
    private List<Korisnik> listaKorisnika = new ArrayList<>();
    private List<KonkretniSpremnik> listaSpremnika = new ArrayList<>();

    public static class Builder implements GeneralBuilder {

        private String naziv;
        private int brojMjesta;
        private int maliUdio;
        private int srednjiUdio;
        private int velikiUdio;
        private List<Korisnik> listaKorisnika = new ArrayList<>();
        private final List<KonkretniSpremnik> listaSpremnika = new ArrayList<>();

        public Builder() {

        }

        public Ulica build() {
            return new Ulica(this);
        }

        private void correctShares() {
            int max = Math.max(Math.max(maliUdio, srednjiUdio), velikiUdio);
            int sumShares = maliUdio + srednjiUdio + velikiUdio;
            if (sumShares > brojMjesta) {
                if (max == maliUdio) {
                    maliUdio -= (sumShares - brojMjesta);
                } else if (max == srednjiUdio) {
                    srednjiUdio -= (sumShares - brojMjesta);
                } else if (max == velikiUdio) {
                    velikiUdio -= (sumShares - brojMjesta);
                }
            } else if (sumShares < brojMjesta) {
                if (max == maliUdio) {
                    maliUdio += (brojMjesta - sumShares);
                } else if (max == srednjiUdio) {
                    srednjiUdio += (brojMjesta - sumShares);
                } else if (max == velikiUdio) {
                    velikiUdio += (brojMjesta - sumShares);
                }
            }
        }

        @Override
        public Builder addDataFromFile(String[] data) {
            this.naziv = data[0];
            this.brojMjesta = Integer.parseInt(data[1]);
            this.maliUdio = Math.round((float) Integer.parseInt(data[2]) / 100 * brojMjesta);
            this.srednjiUdio = Math.round((float) Integer.parseInt(data[3]) / 100 * brojMjesta);
            this.velikiUdio = Math.round((float) Integer.parseInt(data[4]) / 100 * brojMjesta);
            correctShares();
            System.out.println("----------------------------------------");
            System.out.println("Naziv ulice: " + naziv);
            System.out.println("Mali udio: " + maliUdio);
            System.out.println("Srednji udio: " + srednjiUdio);
            System.out.println("Veliki udio: " + velikiUdio);
            System.out.println("Ukupan broj mjesta: " + brojMjesta);
            return this;
        }

        public Builder addMaliUsers() {
            if (maliUdio > 0) {
                KorisnikCreator kc = UserFactory.getKorisnikCreator(UserTypes.MALI, maliUdio);
                listaKorisnika.addAll(kc.getUsers());
                listaSpremnika.addAll(kc.getSpremniciUlice());

            }
            return this;
        }

        public Builder addSrednjiUsers() {
            if (srednjiUdio > 0) {
                KorisnikCreator kc = UserFactory.getKorisnikCreator(UserTypes.SREDNJI, srednjiUdio);
                listaKorisnika.addAll(kc.getUsers());
                listaSpremnika.addAll(kc.getSpremniciUlice());
            }
            return this;
        }

        public Builder addVelikiUsers() {
            if (velikiUdio > 0) {
                KorisnikCreator kc = UserFactory.getKorisnikCreator(UserTypes.VELIKI, velikiUdio);
                listaKorisnika.addAll(kc.getUsers());
                listaSpremnika.addAll(kc.getSpremniciUlice());
            }
            return this;
        }

    }

    private Ulica(Builder builder) {
        this.naziv = builder.naziv;
        this.brojMjesta = builder.brojMjesta;
        this.maliUdio = builder.maliUdio;
        this.srednjiUdio = builder.srednjiUdio;
        this.velikiUdio = builder.velikiUdio;
        this.listaKorisnika = builder.listaKorisnika;
        this.listaSpremnika = builder.listaSpremnika;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getBrojMjesta() {
        return brojMjesta;
    }

    public int getMaliUdio() {
        return maliUdio;
    }

    public int getSrednjiUdio() {
        return srednjiUdio;
    }

    public int getVelikiUdio() {
        return velikiUdio;
    }

    public List<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }

    public List<KonkretniSpremnik> getListaSpremnika() {
        return listaSpremnika;
    }

    public void ukupnoOtpada() {
        int ukupnoOtpad = 0;
        for (KonkretniSpremnik k : listaSpremnika) {
            ukupnoOtpad += k.getNapunjenost();
        }
        System.out.println("Ukupno otpada: " + ukupnoOtpad);
    }

}
