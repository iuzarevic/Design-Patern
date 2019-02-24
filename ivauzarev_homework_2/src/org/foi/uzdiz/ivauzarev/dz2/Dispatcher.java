/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz2.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz2.decorators.UniversalVoziloDecorator;
import org.foi.uzdiz.ivauzarev.dz2.decorators.VoziloInterface;
import org.foi.uzdiz.ivauzarev.dz2.iterator.IteratorVozilo;
import org.foi.uzdiz.ivauzarev.dz2.iterator.IteratorVoziloAktivni;
import org.foi.uzdiz.ivauzarev.dz2.iterator.IteratorVoziloNaOdvozu;
import org.foi.uzdiz.ivauzarev.dz2.iterator.VozniParkRepository;
import org.foi.uzdiz.ivauzarev.dz2.observers.KonkretniObserverZaVozila;
import org.foi.uzdiz.ivauzarev.dz2.observers.KonkretniObserverZaVozilaVrati;

/**
 *
 * @author Ivan
 */
public class Dispatcher {

    public List<String> commands = new ArrayList<>();
    private static volatile Dispatcher instance = null;
    VozniParkRepository vpr;
    int broj_cilusa_za_izvrsavanje;
    static int i = 0;
    static int kreni = 0;

    private Dispatcher() {

    }

    public static Dispatcher getInstance() {
        if (instance == null) {
            synchronized (EzoConfig.class) {
                if (instance == null) {
                    instance = new Dispatcher();
                }
            }
        }

        return instance;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public String[] parsirajKomandu(String command) {
        String data[] = command.split(";");
        return data;
    }

    public int parsirajKomanduKreni(String command) {
        String data[] = command.split(" ");
        if (data.length == 2) {
            return Integer.parseInt(data[1].replace(";", ""));
        } else {
            return -2;
        }
    }

    public void addVozniPark(VozniParkRepository vpr) {
        this.vpr = vpr;
    }

    public void pokreniVozniPark() {
        for (String s : commands) {
            if (s.contains("KRENI")) {
                int broj = parsirajKomanduKreni(s);
                kreni(broj);
            } else if (s.contains("STATUS")) {

            } else {
                String[] data = parsirajKomandu(s);
                switch (data[0]) {
                    case "PRIPREMI":
                        pripremi(data[1]);
                        break;
                    case "KVAR":
                        kvar(data[1]);
                        break;
                    case "KONTROLA":
                        kontrola(data[1]);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void setBroj_cilusa_za_izvrsavanje(int broj_cilusa_za_izvrsavanje) {
        this.broj_cilusa_za_izvrsavanje = broj_cilusa_za_izvrsavanje;
    }

    public void provjeriVozilaNaOdvozu() {
        IteratorVoziloNaOdvozu iv = vpr.getIteratorNaOdvozu();
        while (iv.hasNext()) {
            Vozilo v = iv.next();
            v.povecajInterniCiklus();
            v.provjeriStanjeCiklusa();
        }
    }

    public void resetirajInterneCikluse() {
        IteratorVoziloNaOdvozu iv = vpr.getIteratorNaOdvozu();
        while (iv.hasNext()) {
            Vozilo v = iv.next();
            v.povecajInterniCiklus();
            v.provjeriStanjeCiklusa();
        }
    }

    public void dodajObservere() {
        IteratorVoziloAktivni iv = vpr.getIteratorAktivni();
        while (iv.hasNextAktivni()) {
            if (kreni == 1) {
                Vozilo v = iv.nextAktivni();
                new KonkretniObserverZaVozila(v, vpr);
                new KonkretniObserverZaVozilaVrati(v, vpr);
            }
        }
    }

    /**
     * Vozilo moze prikuplati sav otpad
     */
    public void dekorirajUniverzalnoVozilo() {
        VoziloInterface v = vpr.getListaVozilaPrikupljaju().get(0);
        VoziloInterface novo = new UniversalVoziloDecorator(v);
        vpr.listaVozilaPrikupljaju.add(novo);
        vpr.listaVozilaPrikupljaju.remove(v);

    }

    public void vrti() {
        IteratorVoziloAktivni iv = vpr.getIteratorAktivni();
        dodajObservere();
        if (kreni == 1) {
            //dekorirajUniverzalnoVozilo();
        }
        for (Ulica u : vpr.getListaUlica()) {
            List<KonkretniSpremnik> spremnici = u.getListaSpremnika();
            for (KonkretniSpremnik s : spremnici) {
                while (iv.hasNextAktivni()) {
                    i++;
                    if (i == broj_cilusa_za_izvrsavanje + 1) {
                        return;
                    }
                    System.out.println("Ciklus: " + i);
                    provjeriVozilaNaOdvozu();
                    Vozilo v = iv.nextAktivni();
                    v.dohvatiOtpad(s);
                }
            }

        }
    }

    public void pripremi(String vozila) {
        System.out.println("->Priprema: " + vozila);
        IteratorVozilo iv = vpr.getIterator();
        String[] vozilaZaPripremu = vozila.split(",");
        iv.premjestiUAktivne(vozilaZaPripremu);
        System.out.println("->Gotov sa pripremi");
    }

    public void kvar(String vozila) {
        System.out.println("### Kvar: " + vozila + " ###");
        IteratorVozilo iv = vpr.getIterator();
        String[] vozilaZaPripremu = vozila.split(",");
        iv.premjestiUKvar(vozilaZaPripremu);
        System.out.println("### Gotov sa obradom kvara ###");
    }

    public void kreni(int broj_ciklusa) {
        i = 0;
        kreni++;
        setBroj_cilusa_za_izvrsavanje(broj_ciklusa);
        vrti();
    }

    private void kontrola(String vozila) {
        System.out.println("### Kontrola: " + vozila + " ###");
        IteratorVozilo iv = vpr.getIterator();
        String[] vozilaZaPripremu = vozila.split(",");
        iv.premjestiUKontrolu(vozilaZaPripremu);
        System.out.println("### Gotov sa obradom kontrole ###");
    }

    private void tablicaIspis(String vozila) {
        System.out.println("### Kontrola: " + vozila + " ###");
        IteratorVozilo iv = vpr.getIterator();
        String[] vozilaZaPripremu = vozila.split(",");
        iv.premjestiUKontrolu(vozilaZaPripremu);
        System.out.println("### Gotov sa obradom kontrole ###");
    }
}
