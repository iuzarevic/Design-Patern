/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1.loaders;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.ivauzarev.dz1.Spremnik;
import org.foi.uzdiz.ivauzarev.dz1.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class SpremniciFileLoader {

    String pattern1 = "^(\\w+){1};(\\d+){1};(\\d+){1};(\\d+){1};(\\d+){1};(\\d+){1}$";

    public void dataModifying(List<String> dataList) {
        Pattern r = Pattern.compile(pattern1,Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m;
        String cvsSplitBy = ";";
        for (Iterator<String> iterator = dataList.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            m = r.matcher(next);
            if (m.find()) {
                System.out.println("->Dodajem: " + next);
                String[] data = next.split(cvsSplitBy);
                Spremnik s = new Spremnik.Builder().addDataFromFile(data).build();
                EzoConfig.getInstance().getListaSpremnika().add(s);
            }
        }
    }
}
