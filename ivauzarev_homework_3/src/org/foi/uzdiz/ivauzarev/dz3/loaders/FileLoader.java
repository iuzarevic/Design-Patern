/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.ivauzarev.dz3.Ulica;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;

/**
 * Ovo je naš interface, odnosno apstraktna klasa algoritma za Stratety Pattern
 * @author Ivan
 */
public abstract class FileLoader implements LoaderStrategy{
    Locale loc = new Locale("hr", "HR");
    protected ArrayList<String> ar = new ArrayList<>();

    @Override
    public void solve() {
        dataModifying();
    };
    
    public void retrieveUniversalData(String file) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(file), "UTF-8");
            scanner.useLocale(loc);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String replacedstring = line.replaceFirst("\uFEFF", "").trim();
                ar.add(replacedstring);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EzoConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public abstract void dataModifying();
}
