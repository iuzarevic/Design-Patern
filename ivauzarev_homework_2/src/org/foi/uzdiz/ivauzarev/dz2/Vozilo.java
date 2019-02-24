/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz2.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz2.decorators.VoziloInterface;
import org.foi.uzdiz.ivauzarev.dz2.helpers.GeneralBuilder;
import org.foi.uzdiz.ivauzarev.dz2.helpers.GenerateNumber;
import org.foi.uzdiz.ivauzarev.dz2.loggers.VozilaLogger;
import org.foi.uzdiz.ivauzarev.dz2.observers.KonkretniObserverZaVozila;
import org.foi.uzdiz.ivauzarev.dz2.observers.KonkretniObserverZaVozilaVrati;
import org.foi.uzdiz.ivauzarev.dz2.observers.ObserverZaVozila;

/**
 *
 * @author Ivan
 */
public class Vozilo implements VoziloInterface {

    private List<ObserverZaVozila> observers = new ArrayList<ObserverZaVozila>();

    private final String id;
    private final String naziv;
    private final int tip;
    private final int vrsta;
    private final int nosivost;
    private final String vozaci;

    private float popunjenost;
    private Statistika statistika;
    int interniCiklus = 0;

    public Vozilo(Vozilo.Builder builder) {
        this.id = builder.id;
        this.naziv = builder.naziv;
        this.tip = builder.tip;
        this.vrsta = builder.vrsta;
        this.nosivost = builder.nosivost;
        this.vozaci = builder.vozaci;
        this.popunjenost = 0;
        statistika = new Statistika();
    }

    void provjeriStanjeCiklusa() {
        int brojRadnihCiklusaZaOdvoz = EzoConfig.getInstance().getBrojRadnihCiklusaZaOdvoz();
        if (interniCiklus == brojRadnihCiklusaZaOdvoz + 1) {
            notifyOnlyVrati();
        }
    }

    public static class Builder implements GeneralBuilder {

        private String id;
        private String naziv;
        private int tip;
        private int vrsta;
        private int nosivost;
        private String vozaci;

        public Builder() {

        }

        public Vozilo build() {
            return new Vozilo(this);
        }

        @Override
        public Vozilo.Builder addDataFromFile(String[] data) {
            this.id = data[0];
            this.naziv = data[1];
            this.tip = Integer.parseInt(data[2]);
            this.vrsta = Integer.parseInt(data[3]);
            this.nosivost = Integer.parseInt(data[4]);
            this.vozaci = data[5];
            return this;
        }
    }

    public String getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getTip() {
        return tip;
    }

    public int getNosivost() {
        return nosivost;
    }

    public String getVozaci() {
        return vozaci;
    }

    public float getPopunjenost() {
        GenerateNumber gn = GenerateNumber.getInstance();
        return gn.getFloatNumber(popunjenost);
    }

    public void setPopunjenost(float popunjenost) {
        this.popunjenost = popunjenost;
    }

    public float racunajSlobodno() {
        GenerateNumber gn = GenerateNumber.getInstance();
        return gn.getFloatNumber(getNosivost() - getPopunjenost());
    }

    public int getVrsta() {
        return vrsta;
    }

    public Statistika getStatistika() {
        return statistika;
    }

    public void setStatistika(Statistika statistika) {
        this.statistika = statistika;
    }

    public void povecajInterniCiklus() {
        interniCiklus++;
    }

    public void povecajKapacitet(int i) {
        
    }

    public void dohvatiOtpad(KonkretniSpremnik ks) {
        //sta ako je spremnik veci od nosivosti, mislim da je to nemoguca situacija, nijedan spremnik u stvarnosti nije veci od nosivosti
        VozilaLogger.printRad(this, ks);
        if (ks.getNazivBroj() == getVrsta()) {
            float nova_popunjenost = popunjenost + ks.getNapunjenost();
            if (nova_popunjenost >= nosivost) {
                notifyAllObservers();
            } else {
                popunjenost = nova_popunjenost;
                ks.isprazni();
                azurirajStatistikuOtpada(1, ks.getNapunjenost());
            }
        }
    }

    public void azurirajStatistikuOtpada(int brojSpremnika, float kolicinaOtpada) {
        statistika.setUkupnoSpremnika(brojSpremnika);
        statistika.setUkupnoKolicinaOtpada(kolicinaOtpada);
    }

    public void attach(ObserverZaVozila observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (ObserverZaVozila observer : observers) {
            if (observer instanceof KonkretniObserverZaVozila) {
                observer.premjesti();
            }
        }
    }

    public void notifyOnlyVrati() {
        for (ObserverZaVozila observer : observers) {
            if (observer instanceof KonkretniObserverZaVozilaVrati) {
                observer.premjesti();
            }
        }
        interniCiklus = 0;
    }
}
