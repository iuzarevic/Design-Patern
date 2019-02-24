/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loaders;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.ivauzarev.dz3.Ulica;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class UliceFileLoader extends FileLoader {

    public void dataModifying() {
        //Pattern r = Pattern.compile(pattern1, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m;
        String file = EzoConfig.getInstance().getUlice();
        retrieveUniversalData(file);
        String cvsSplitBy = ";";
        for (Iterator<String> iterator = ar.iterator(); iterator.hasNext();) {
            String next = iterator.next().trim();
            if (!next.contains("naziv") && !next.contains(":")) {
                String[] data = next.split(cvsSplitBy);
                Ulica u = new Ulica.Builder()
                        .addDataFromFile(data)
                        .addMaliUsers()
                        .addSrednjiUsers().addVelikiUsers().build();
                EzoConfig.getInstance().getListaUlica().add(u);
            }
        }
    }
}
