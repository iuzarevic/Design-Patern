/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loaders;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class VozilaFileLoader extends FileLoader {

    String pattern1 = "^(\\w+){1};([\\w\\s]+){1};([0-1]+){1};([0-4]+){1};([\\w]+){1};([\\w\\s,]+){1}$";

    public void dataModifying() {
        String file = EzoConfig.getInstance().getVozila();
        retrieveUniversalData(file);
        Pattern r = Pattern.compile(pattern1, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m;
        String cvsSplitBy = ";";
        for (Iterator<String> iterator = ar.iterator(); iterator.hasNext();) {
            String next = iterator.next().trim();
            m = r.matcher(next);
            if (m.find()) {
                System.out.println("->Dodajem: " + next);
                String[] data = next.split(cvsSplitBy);
                Vozilo s = new Vozilo.Builder().addDataFromFile(data).build();
                EzoConfig.getInstance().getListaVozila().add(s);
            } else {
                System.out.println("->Neispravni redak: " + next);
            }
        }
    }
}
