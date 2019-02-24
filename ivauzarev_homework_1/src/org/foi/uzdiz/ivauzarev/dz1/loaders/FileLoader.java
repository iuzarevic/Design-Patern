/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1.loaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.ivauzarev.dz1.Ulica;
import org.foi.uzdiz.ivauzarev.dz1.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public abstract class FileLoader {

    ArrayList<String> ar = new ArrayList<>();

    protected ArrayList<String> retrieveUniversalData(String file) {
        try {
            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNext()) {
                ar.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EzoConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ar;
    }

    public abstract void dataModifying();
}
