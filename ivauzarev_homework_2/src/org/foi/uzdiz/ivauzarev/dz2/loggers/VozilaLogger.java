/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2.loggers;

import org.foi.uzdiz.ivauzarev.dz2.KonkretniSpremnik;
import org.foi.uzdiz.ivauzarev.dz2.Vozilo;
import org.foi.uzdiz.ivauzarev.dz2.composite.PodrucjeComponent;

/**
 *
 * @author Ivan
 */
public class VozilaLogger {
    public static void printRad(Vozilo v, KonkretniSpremnik ks) {
        System.out.println("->Vozilo " + v.getNaziv() + " ceka " + " kod spremnika " + ks.getId() + ": " + ks.getNaziv());
        System.out.print("-->");
        System.out.println(" |Vozilo: " + v.getId() + ";"+v.getNaziv()+
                " |Stanje: " + v.getPopunjenost()+ " |Slobodno: " + v.racunajSlobodno());
        System.out.println();
    }
}
