/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loggers;

import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.composite.PodrucjeComponent;

/**
 *
 * @author Ivan
 */
public class PodrucjaLogger {

    public static void print(List<PodrucjeComponent> podrucja) {
//        System.out.println("--------------------------------------------------------------------------------------------------");
//        System.out.printf("%10s %30s %15s %10s %10s %10s %10s %10s %1s",
//                "Id", "Naziv", "Ukupno otpada", "Staklo", "Papir", "Bio", "Metal", "Mjesano", "Dijelovi");
//        System.out.println();
//        System.out.println("--------------------------------------------------------------------------------------------------");
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("ID", "Naziv", "Ukupno otpada", "Staklo", "Papir", "Bio","Metal","Mjesano","Dijelovi");
        for (PodrucjeComponent comp : podrucja) {
            comp.tablicniIspis(st);
            System.out.println();
        }
        st.print();
//        System.out.println();
//        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    public static void printPocetni(List<PodrucjeComponent> podrucja) {
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("ID", "Naziv", "Komponente");
        for (PodrucjeComponent comp : podrucja) {
            comp.addRow(st);
        }
        st.print();
    }
}
