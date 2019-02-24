/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.ivauzarev.dz3.composite.Podrucje;
import org.foi.uzdiz.ivauzarev.dz3.composite.PodrucjeComponent;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz3.loggers.PodrucjaLogger;

/**
 *
 * @author Ivan
 */
public class PodrucjaFileLoader extends FileLoader {

    Podrucje glavnoPodrucje = new Podrucje("id0", "korijen");
    List<PodrucjeComponent> listaZaBrisanje = new ArrayList<>();
    String pattern1 = "^(\\w+){1};([\\w\\s]+){1};([\\w,]+){1}$";

    public void dataModifying() {
        String file = EzoConfig.getInstance().getPodrucja();
        retrieveUniversalData(file);
        Pattern r = Pattern.compile(pattern1, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m;
        String cvsSplitBy = ";";
        String cvsSplitByDijelovi = ",";
        for (Iterator<String> iterator = ar.iterator(); iterator.hasNext();) {
            String next = iterator.next().trim();
            m = r.matcher(next);
            if (!next.contains("naziv") && !next.contains(":") && m.find()) {
                String[] data = next.split(cvsSplitBy);
                String id = data[0];
                String naziv = data[1];
                String[] dijelovi_string = data[2].split(cvsSplitByDijelovi);
                List<String> dijelovi = Arrays.asList(dijelovi_string);
                Podrucje p = new Podrucje(id, naziv, dijelovi);
                glavnoPodrucje.add(p);
            }
        }
        obradiPodrucja();
        EzoConfig.getInstance().setGlavnoPodrucje(glavnoPodrucje);
    }

    private void obradiPodrucja() {
        List<PodrucjeComponent> temp = new ArrayList<>(glavnoPodrucje.getPodrucjeComponents());
        for (PodrucjeComponent p : temp) {
            PodrucjeComponent glavno_pc = findPodrucje(p.getId());
            for (String dio : p.getDijelovi()) {
                if (dio.startsWith("u")) {
                    PodrucjeComponent u = EzoConfig.getInstance().getParticularUlica(dio);
                    if (u != null) {
                        p.add(u);
                    }
                } else {
                    PodrucjeComponent pc = findPodrucje(dio);
                    if (pc != null && glavno_pc != null) {
                        glavno_pc.add(pc);
                        listaZaBrisanje.add(pc);
                    }
                }
            }
            if (glavno_pc != null) {
                //glavno_pc.print();
            }
        }
        obrisiNepotrebnaPodrucja();
    }

    public void obrisiNepotrebnaPodrucja() {
        for (PodrucjeComponent brisi : listaZaBrisanje) {
            glavnoPodrucje.remove(brisi);
        }
        PodrucjaLogger.printPocetni(glavnoPodrucje.getPodrucjeComponents());
    }

    public PodrucjeComponent findPodrucje(String id) {
        for (PodrucjeComponent pc : glavnoPodrucje.getPodrucjeComponents()) {
            if (id.equals(pc.getId())) {
                return pc;
            }
        }
        return null;
    }
}
