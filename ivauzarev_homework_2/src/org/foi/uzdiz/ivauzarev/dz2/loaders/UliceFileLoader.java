/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2.loaders;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.ivauzarev.dz2.Ulica;
import org.foi.uzdiz.ivauzarev.dz2.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class UliceFileLoader extends FileLoader {

    public void dataModifying() {
        String pattern1 = "^(\\w+){1};([\\w\\s]+){1};(\\d+){1};(\\d+){1};(\\d+){1};(\\d+){1}$";
        Pattern r = Pattern.compile(pattern1, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m;
        String file = EzoConfig.getInstance().getUlice();
        retrieveUniversalData(file);
        String cvsSplitBy = ";";
        for (Iterator<String> iterator = ar.iterator(); iterator.hasNext();) {
            String next = iterator.next().trim();
            m = r.matcher(next);
            String[] data = next.split(cvsSplitBy);
            if (m.find()) {
                Ulica u = new Ulica.Builder()
                        .addDataFromFile(data)
                        .addMaliUsers()
                        .addSrednjiUsers().addVelikiUsers().build();
                EzoConfig.getInstance().getListaUlica().add(u);
            }
        }
    }
}
