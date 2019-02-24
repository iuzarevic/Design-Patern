/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.composite;

import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.helpers.GenerateNumber;
import org.foi.uzdiz.ivauzarev.dz3.loggers.CommandLineTable;

/**
 *
 * @author Ivan
 */
public class Podrucje extends PodrucjeComponent {

    public Podrucje(String id, String naziv, List<String> dijelovi) {
        this.id = id;
        this.naziv = naziv;
        this.dijelovi = dijelovi;
    }

    public Podrucje(String id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    @Override
    public PodrucjeComponent add(PodrucjeComponent p) {
        podrucjeComponents.add(p);
        return p;
    }

    @Override
    public PodrucjeComponent remove(PodrucjeComponent p) {
        podrucjeComponents.remove(p);
        return p;
    }

    @Override
    public void addRow(CommandLineTable st) {
        st.addRow(this.getId(), this.getNaziv(), printComp());
        for (PodrucjeComponent comp : podrucjeComponents) {
            comp.addRow(st);
        }

    }

    public String printComp() {
        String result = "";
        for (PodrucjeComponent comp : podrucjeComponents) {
            result += comp.getId() + ";";
        }
        return result;
    }

    @Override
    public float ukupnoOtpada() {
        GenerateNumber gn = GenerateNumber.getInstance();
        float ukupnoOtpad = 0;
        for (PodrucjeComponent comp : podrucjeComponents) {
            ukupnoOtpad += comp.ukupnoOtpada();
        }
        return gn.getFloatNumber(ukupnoOtpad);
    }

    @Override
    public int tablicniIspis(CommandLineTable st) {
        counterString += "*";
//        System.out.format("%10s %30s %15.2f %10.2f %10.2f %10.2f %10.2f %10.2f %1s",
//                id, naziv, ukupnoOtpada(), ukupnoStaklo(), ukupnoPapir(), ukupnoBio(), ukupnoMetal(), ukupnoMjesano(), String.join(",", dijelovi));
        st.addRow(counterString + id, naziv, Float.toString(ukupnoOtpada()), Float.toString(ukupnoStaklo()), Float.toString(ukupnoPapir()), Float.toString(ukupnoBio()), Float.toString(ukupnoMetal()), Float.toString(ukupnoMjesano()), String.join(",", dijelovi));
        for (PodrucjeComponent comp : podrucjeComponents) {
            comp.tablicniIspis(st);
        }
        counterString = counterString.substring(1);
        return 1;
    }

    @Override
    public float ukupnoStaklo() {
        GenerateNumber gn = GenerateNumber.getInstance();
        float ukupnoOtpad = 0;
        for (PodrucjeComponent comp : podrucjeComponents) {
            ukupnoOtpad += comp.ukupnoStaklo();
        }
        return gn.getFloatNumber(ukupnoOtpad);
    }

    @Override
    public float ukupnoPapir() {
        GenerateNumber gn = GenerateNumber.getInstance();
        float ukupnoOtpad = 0;
        for (PodrucjeComponent comp : podrucjeComponents) {
            ukupnoOtpad += comp.ukupnoPapir();
        }
        return gn.getFloatNumber(ukupnoOtpad);
    }

    @Override
    public float ukupnoMetal() {
        GenerateNumber gn = GenerateNumber.getInstance();
        float ukupnoOtpad = 0;
        for (PodrucjeComponent comp : podrucjeComponents) {
            ukupnoOtpad += comp.ukupnoMetal();
        }
        return gn.getFloatNumber(ukupnoOtpad);
    }

    @Override
    public float ukupnoMjesano() {
        GenerateNumber gn = GenerateNumber.getInstance();
        float ukupnoOtpad = 0;
        for (PodrucjeComponent comp : podrucjeComponents) {
            ukupnoOtpad += comp.ukupnoMjesano();
        }
        return gn.getFloatNumber(ukupnoOtpad);
    }

    @Override
    public float ukupnoBio() {
        GenerateNumber gn = GenerateNumber.getInstance();
        float ukupnoOtpad = 0;
        for (PodrucjeComponent comp : podrucjeComponents) {
            ukupnoOtpad += comp.ukupnoBio();
        }
        return gn.getFloatNumber(ukupnoOtpad);
    }

}
