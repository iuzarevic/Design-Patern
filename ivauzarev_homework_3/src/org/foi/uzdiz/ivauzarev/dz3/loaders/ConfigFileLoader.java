/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class ConfigFileLoader implements LoaderStrategy {

    private String file;
    private Map<String, String> params = new HashMap<String, String>();

    public ConfigFileLoader(String file, Map<String, String> params) {
        this.file = file;
        this.params = params;
    }

    @Override
    public void solve() {
        saveParam();
    }
    
    public Map<String, String> saveParam() {
        Locale loc = new Locale("hr", "HR");
        try {
            Scanner scanner = new Scanner(new FileInputStream(file), "UTF-8");
            scanner.useLocale(loc);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String replacedstring = line.replaceFirst("\uFEFF", "").trim();
                String splitted[] = replacedstring.split(":");
                params.put(splitted[0], splitted[1]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EzoConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return params;
    }
}
