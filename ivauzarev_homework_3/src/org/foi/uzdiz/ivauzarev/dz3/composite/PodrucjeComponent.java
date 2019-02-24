/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.composite;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.loggers.CommandLineTable;

/**
 *
 * @author Ivan
 */
public abstract class PodrucjeComponent {

    String id;
    String naziv;
    List<String> dijelovi;
    protected List<PodrucjeComponent> podrucjeComponents = new ArrayList<>();
    public static int counter = 0;
    public static String counterString = "";

    public PodrucjeComponent add(PodrucjeComponent p) {
        throw new UnsupportedOperationException("Feature not implemented at this level");
    }

    public PodrucjeComponent remove(PodrucjeComponent p) {
        throw new UnsupportedOperationException("Feature not implemented at this level");
    }

    public String getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public List<String> getDijelovi() {
        return dijelovi;
    }

    public List<PodrucjeComponent> getPodrucjeComponents() {
        return podrucjeComponents;
    }

    public abstract void addRow(CommandLineTable st);

    public abstract float ukupnoOtpada();

    public abstract int tablicniIspis(CommandLineTable st);

    public abstract float ukupnoStaklo();

    public abstract float ukupnoPapir();

    public abstract float ukupnoMetal();

    public abstract float ukupnoMjesano();

    public abstract float ukupnoBio();
}
