/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loaders;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.ivauzarev.dz3.Dispatcher;
import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class DispatcherFileLoader extends FileLoader{
    String pattern1 = "^(PRIPREMI|KVAR|ISPRAZNI|OTKAZ|BOLOVANJE|GODIŠNJI ODMOR|NOVI){1};([\\w,]+){1};$";
    String pattern2 = "^(KRENI|STATUS|VOZAČI|IZLAZ){1};$";
    String pattern3 = "^(KRENI){1} (\\d+){1};$";
    String pattern4 = "^(OBRADI){1};(\\w+){1};([\\w,]+){1};$";
    
    @Override
    public void dataModifying() {
        String file = EzoConfig.getInstance().getDispatcher();
        retrieveUniversalData(file);
        Pattern r1 = Pattern.compile(pattern1, Pattern.UNICODE_CHARACTER_CLASS);
        Pattern r2 = Pattern.compile(pattern2, Pattern.UNICODE_CHARACTER_CLASS);
        Pattern r3 = Pattern.compile(pattern3, Pattern.UNICODE_CHARACTER_CLASS);
        Pattern r4 = Pattern.compile(pattern4, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m1,m2,m3,m4;
        String cvsSplitBy = ";";
        for (Iterator<String> iterator = ar.iterator(); iterator.hasNext();) {
            String next = iterator.next().trim();
            m1 = r1.matcher(next);
            m2 = r2.matcher(next);
            m3 = r3.matcher(next);
            m4 = r4.matcher(next);
            if (m1.find() || m2.find() || m3.find() || m4.find()) {
                System.out.println("->Dodajem: " + next);
                Dispatcher dp = Dispatcher.getInstance();
                dp.addCommand(next);
            } else {
                System.out.println("->Neispravni redak: " + next);
            }
        }
    }
}
