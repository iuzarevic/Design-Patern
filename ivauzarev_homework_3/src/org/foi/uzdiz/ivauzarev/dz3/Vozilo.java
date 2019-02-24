/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.composite.PodrucjeComponent;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz3.decorators.VoziloInterface;
import org.foi.uzdiz.ivauzarev.dz3.helpers.GeneralBuilder;
import org.foi.uzdiz.ivauzarev.dz3.helpers.GenerateNumber;
import org.foi.uzdiz.ivauzarev.dz3.loggers.VozilaLogger;
import org.foi.uzdiz.ivauzarev.dz3.observers.ObserverZaVozila;
import org.foi.uzdiz.ivauzarev.dz3.state.Vozac;
import org.foi.uzdiz.ivauzarev.dz3.state.VoziloInicijalno;
import org.foi.uzdiz.ivauzarev.dz3.state.VoziloState;

/**
 *
 * @author Ivan
 */
public class Vozilo implements VoziloInterface {

    private List<ObserverZaVozila> observers = new ArrayList<ObserverZaVozila>();
    private List<Vozac> lVozaci = new ArrayList<Vozac>();
    private List<PodrucjeComponent> lPodrucja = new ArrayList<PodrucjeComponent>();
    Vozac trenutniVozac;

    private final String id;
    private final String naziv;
    private final int tip;
    private final int vrsta;
    private final int nosivost;
    private final String vozaci;

    private float popunjenost;
    private Statistika statistika;
    public int interniCiklus = 0;

    public int stanjeGoriva = 0;
    public int kapacitetGoriva = 0;
    public int trenutniCiklusiRada = 0;
    public int ciklusiPunjenjaGoriva = 0;

    private VoziloState currentState;

    public Vozilo(Vozilo.Builder builder) {
        this.id = builder.id;
        this.naziv = builder.naziv;
        this.tip = builder.tip;
        this.vrsta = builder.vrsta;
        this.nosivost = builder.nosivost;
        this.vozaci = builder.vozaci;
        this.popunjenost = 0;
        statistika = new Statistika();
        currentState = new VoziloInicijalno();
        obradiListuVozace();
        trenutniVozac = lVozaci.get(0);
        obradiTipVozila();
    }

    void obradiListuVozace() {
        String[] splitArray = (vozaci.replace(" ", "")).split(",");
        for (String v : splitArray) {
            Vozac novi = new Vozac(v);
            //postavi vozace da su dodijeljeni za preuzimanje odredjenim vozilima
            novi.postaviPreuzimanje();
            //dodaj u internu listu vozaca odredjenoga vozila
            lVozaci.add(novi);
            //dodaj u globalnu listu vozaca
            EzoConfig.getInstance().getListaVozaca().add(novi);
        }
    }

    void obradiTipVozila() {
        if(tip == 0) {
            kapacitetGoriva = EzoConfig.getInstance().getKapacitetDizelVozila();
            ciklusiPunjenjaGoriva = EzoConfig.getInstance().getPunjenjeDizelVozila();
        } else {
            kapacitetGoriva = EzoConfig.getInstance().getKapacitetElektroVozila();
            ciklusiPunjenjaGoriva = EzoConfig.getInstance().getPunjenjeElektroVozila();
        }
    }

    void printVozaci() {
        for (Vozac v : lVozaci) {
            System.out.println(v.getIme());
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

    public void povecajStanjeGoriva() {
        stanjeGoriva++;
    }

    public void povecajKapacitet(int i) {

    }

    public boolean dohvatiOtpad(KonkretniSpremnik ks) {
        //sta ako je spremnik veci od nosivosti, mislim da je to nemoguca situacija, nijedan spremnik u stvarnosti nije veci od nosivosti
        if (ks.getNazivBroj() == getVrsta()) {
            float nova_popunjenost = popunjenost + ks.getNapunjenost();
            if (nova_popunjenost >= nosivost) {
                provjeriStanja();
                return false;
            } else {
                VozilaLogger.printRad(this, ks);
                popunjenost = nova_popunjenost;
                ks.isprazni();
                azurirajStatistikuOtpada(1, ks.getNapunjenost());
                povecajStanjeGoriva();
                provjeriStanja();
                return true;
            }
        }
        return false;
    }

    public void azurirajStatistikuOtpada(int brojSpremnika, float kolicinaOtpada) {
        statistika.setUkupnoSpremnika(brojSpremnika);
        statistika.setUkupnoKolicinaOtpada(kolicinaOtpada);
    }

    public void attach(ObserverZaVozila observer) {
        observers.add(observer);
    }

//    public void notifyAllObservers() {
//        for (ObserverZaVozila observer : observers) {
//            if (observer instanceof KonkretniObserverZaVozila) {
//                observer.premjesti();
//            }
//        }
//    }
//    public void notifyOnlyVrati() {
//        for (ObserverZaVozila observer : observers) {
//            if (observer instanceof KonkretniObserverZaVozilaVrati) {
//                observer.premjesti();
//            }
//        }
//        interniCiklus = 0;
//    }
    public void set_state(VoziloState s) {
        currentState = s;
    }

    public void postaviPreuzimanje() {
        currentState.postaviPreuzimanje(this);
    }

    public void postaviKvar() {
        currentState.postaviKvar(this);
    }

    public void postaviKontrola() {
        currentState.postaviKontrola(this);
    }

    public void postaviIsprazni() {
        currentState.postaviIsprazni(this);
    }

    public void postaviBezVozaca() {
        currentState.postaviBezVozaca(this);
    }

    public void postaviPunjenje() {
        currentState.postaviPunjenje(this);
    }

    public VoziloState getCurrentState() {
        return currentState;
    }

    public void dodajPodrucjeVozilu(PodrucjeComponent comp) {
        lPodrucja.add(comp);
    }

    public void provjeriStanja() {
        for (ObserverZaVozila observer : observers) {
            observer.premjesti();
        }
    }

    public Vozac getTrenutniVozac() {
        return trenutniVozac;
    }

    public int getStanjeGoriva() {
        return stanjeGoriva;
    }

    public boolean provjeriStanjeGoriva() {
        if(stanjeGoriva == kapacitetGoriva) {
            return true;
        }
        return false;
    }

}
