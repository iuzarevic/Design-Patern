/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.command;

import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.composite.PodrucjeComponent;
import org.foi.uzdiz.ivauzarev.dz3.iterator.VozniParkRepository;
import org.foi.uzdiz.ivauzarev.dz3.loggers.CommandLineTable;
import org.foi.uzdiz.ivauzarev.dz3.state.Vozac;

/**
 *
 * @author Ivan
 */
public class DispatcherReceiver {

    private VozniParkRepository vpr;

    public DispatcherReceiver(VozniParkRepository vpr) {
        this.vpr = vpr;
    }

    public void novi(String string) {
        String[] splitArray = (string.replace(" ", "")).split(",");
        for (String v : splitArray) {
            Vozac noviVozac = new Vozac(v);
            vpr.getListaVozaca().add(noviVozac);
        }
    }

    public void godisnji(String string) {
        String[] splitArray = (string.replace(" ", "")).split(",");
        for (String v : splitArray) {
            for (Vozac vozac : vpr.getListaVozaca()) {
                if (vozac.getIme().equals(v)) {
                    vozac.postaviGodisnji();
                }
            }
        }
    }

    public void bolovanje(String string) {
        String[] splitArray = (string.replace(" ", "")).split(",");
        for (String v : splitArray) {
            for (Vozac vozac : vpr.getListaVozaca()) {
                if (vozac.getIme().equals(v)) {
                    vozac.postaviBolovanje();
                }
            }
        }
    }

    public void otkaz(String string) {
        String[] splitArray = (string.replace(" ", "")).split(",");
        for (String v : splitArray) {
            for (Vozac vozac : vpr.getListaVozaca()) {
                if (vozac.getIme().equals(v)) {
                    vozac.postaviOtkaz();
                }
            }
        }
    }

    public void vozaci() {
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("IME VOZAČA", "STATUS");
        for (Vozac v : vpr.getListaVozaca()) {
            st.addRow(v.getIme(), v.getCurrentState().toString());
        }
        st.print();
    }

    public void obradi(String podrucje, String vozila) {
        String[] splitVozila = (vozila.replace(" ", "")).split(",");
        for (String s : splitVozila) {
            for (Vozilo v : vpr.getListaVozila()) {
                if (v.getId().equals(s)) {
                    PodrucjeComponent pc = vpr.provjeriIshodista(podrucje);
                    if (pc != null) {
                        v.dodajPodrucjeVozilu(pc);
                        System.out.println("#Dodano područje: " + pc.getNaziv());
                    }
                }
            }
        }
    }

    void pripremi(String vozila) {
        String[] splitVozila = (vozila.replace(" ", "")).split(",");
        for (String s : splitVozila) {
            for (Vozilo v : vpr.getListaVozila()) {
                if (v.getId().equals(s)) {
                    v.postaviPreuzimanje();
                }
            }
        }
    }

    void kvar(String vozila) {
        String[] splitVozila = (vozila.replace(" ", "")).split(",");
        for (String s : splitVozila) {
            for (Vozilo v : vpr.getListaVozila()) {
                if (v.getId().equals(s)) {
                    v.postaviKvar();
                }
            }
        }
    }

    void kontrola(String vozila) {
        String[] splitVozila = (vozila.replace(" ", "")).split(",");
        for (String s : splitVozila) {
            for (Vozilo v : vpr.getListaVozila()) {
                if (v.getId().equals(s)) {
                    v.postaviKontrola();
                }
            }
        }
    }

    void status() {
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("ID","IME","TIP","VRSTA","NOSIVOST","POPUNJENOST","VOZAČ","STATUS");
        for (Vozilo v : vpr.getListaVozila()) {
            st.addRow(v.getId(),v.getNaziv(),Integer.toString(v.getTip()),Integer.toString(v.getVrsta()),
                    Integer.toString(v.getNosivost()),Float.toString(v.getPopunjenost()),v.getTrenutniVozac().getIme(),v.getCurrentState().toString());
        }
        st.print();
    }
}
