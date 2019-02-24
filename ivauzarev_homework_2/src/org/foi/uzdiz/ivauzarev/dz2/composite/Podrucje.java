/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2.composite;

import java.util.List;
import org.foi.uzdiz.ivauzarev.dz2.helpers.GenerateNumber;

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
    public void print() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %30s %20s", "ID", "PODRUCJE", "KOMPONENTE");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (PodrucjeComponent comp : podrucjeComponents) {
            System.out.format("%10s %30s %20s",
                    this.getId(), this.getNaziv(), comp.getId());
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
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
    public int tablicniIspis() {
        System.out.format("%10s %30s %15.2f %10.2f %10.2f %10.2f %10.2f %10.2f %1s",
                id, naziv,ukupnoOtpada(), ukupnoStaklo(),ukupnoPapir(),ukupnoBio(),ukupnoMetal(),ukupnoMjesano(),String.join(",", dijelovi));
        for (PodrucjeComponent comp : podrucjeComponents) {
            System.out.println();
            comp.tablicniIspis();
        }
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
