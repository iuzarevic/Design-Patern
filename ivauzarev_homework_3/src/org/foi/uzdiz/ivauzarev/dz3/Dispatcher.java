/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.command.BolovanjeCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.Command;
import org.foi.uzdiz.ivauzarev.dz3.command.DispatcherReceiver;
import org.foi.uzdiz.ivauzarev.dz3.command.GodisnjiOdmorCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.NoviCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.OtkazCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.VozaciCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.VoziloKontrolaCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.VoziloKvarCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.VoziloObradiCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.VoziloPripremiCommand;
import org.foi.uzdiz.ivauzarev.dz3.command.VoziloStatusCommand;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz3.iterator.IteratorVoziloAktivni;
import org.foi.uzdiz.ivauzarev.dz3.iterator.IteratorVoziloNaOdvozu;
import org.foi.uzdiz.ivauzarev.dz3.iterator.VozniParkRepository;
import org.foi.uzdiz.ivauzarev.dz3.observers.ObserverGorivo;
import org.foi.uzdiz.ivauzarev.dz3.observers.ObserverIsprazni;
import org.foi.uzdiz.ivauzarev.dz3.observers.ObserverRadniCiklus;
import org.foi.uzdiz.ivauzarev.dz3.state.VoziloIsprazni;

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
        System.out.println("->Komanda: " + command);
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
        dodajObservere();
    }

    public void pokreniVozniPark() {
        Command command = null;
        DispatcherReceiver dr = new DispatcherReceiver(vpr);
        for (String s : commands) {
            command = null;
            if (s.contains("KRENI")) {
                int broj = parsirajKomanduKreni(s);
                kreni(broj);
            } else if (s.contains("STATUS")) {
                command = new VoziloStatusCommand(dr);
            } else if (s.contains("VOZAČI")) {
                command = new VozaciCommand(dr);
            } else {
                String[] data = parsirajKomandu(s);
                switch (data[0]) {
                    case "PRIPREMI":
                        command = new VoziloPripremiCommand(dr, data[1]);
                        break;
                    case "KVAR":
                        command = new VoziloKvarCommand(dr, data[1]);
                        break;
                    case "KONTROLA":
                        command = new VoziloKontrolaCommand(dr, data[1]);
                        break;
                    case "GODIŠNJI ODMOR":
                        command = new GodisnjiOdmorCommand(dr, data[1]);
                        break;
                    case "BOLOVANJE":
                        command = new BolovanjeCommand(dr, data[1]);
                        break;
                    case "OTKAZ":
                        command = new OtkazCommand(dr, data[1]);
                        break;
                    case "NOVI":
                        command = new NoviCommand(dr, data[1]);
                        break;
                    case "OBRADI":
                        command = new VoziloObradiCommand(dr, data[1], data[2]);
                        break;
                    default:
                        break;
                }
            }
            if (command != null) {
                command.execute();
            }
        }
    }

    public void setBroj_cilusa_za_izvrsavanje(int broj_cilusa_za_izvrsavanje) {
        this.broj_cilusa_za_izvrsavanje = broj_cilusa_za_izvrsavanje;
    }

    public void provjeriVozilaNaOdvozu() {
        for(Vozilo v: vpr.getListaVozila()) {
            if(v.getCurrentState() instanceof VoziloIsprazni) {
                v.povecajInterniCiklus();
                v.provjeriStanja();
            }
        }
    }

    public void dodajObservere() {
//        IteratorVoziloAktivni iv = vpr.getIteratorAktivni();
//        while (iv.hasNextAktivni()) {
//            if (kreni == 1) {
//                Vozilo v = iv.nextAktivni();
//                new KonkretniObserverZaVozila(v, vpr);
//                new KonkretniObserverZaVozilaVrati(v, vpr);
//            }
//        }
//
        for (Vozilo v : vpr.getListaVozila()) {
            new ObserverRadniCiklus(v);
            new ObserverIsprazni(v);
            new ObserverGorivo(v);
        }
    }

    public void vrti() {
        IteratorVoziloAktivni iv = vpr.getIteratorAktivni();
        //dodajObservere();
        for (Ulica u : vpr.getListaUlica()) {
            List<KonkretniSpremnik> spremnici = u.getListaSpremnika();
            for (KonkretniSpremnik s : spremnici) {
                while (iv.hasNextAktivni()) {
                    Vozilo v = iv.nextAktivni();
                    if (v.dohvatiOtpad(s) == true) {
                        i++;
                        provjeriVozilaNaOdvozu();
                        System.out.println("Ciklus: " + i);
                        if (i == broj_cilusa_za_izvrsavanje) {
                            return;
                        }
                    };
                }
            }

        }
    }

    public void kreni(int broj_ciklusa) {
        i = 0;
        kreni++;
        setBroj_cilusa_za_izvrsavanje(broj_ciklusa);
        vrti();
    }
}
